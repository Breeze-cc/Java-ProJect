import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Action_Listner implements ActionListener, ListSelectionListener, MouseListener {
    //ListAction list;
    private JList list;
    private DefaultListModel tmp;

    StackList PtrlS = new StackList();

    public Action_Listner(JList list, DefaultListModel tmp) {
        this.list = list;
        this.tmp = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //如果触发响应的器件上的字符串是 “新建”
        if (e.getActionCommand() == "新建") {

            //弹出新的窗口输入新备忘录的标题
            String str = JOptionPane.showInputDialog(list, "新建备忘录", JOptionPane.PLAIN_MESSAGE);

            //添加列表项目
            tmp.addElement(str);

            //将tmp中的内容同步到list
            list.setModel(tmp);
        }

        //如果触发响应的器件上的字符串是 “删除”
        if (e.getActionCommand() == "删除") {

            //如果列表的条目数量大于0
            if (tmp.getSize() > 0) {

                //如果有内容被选中
                if (list.getSelectedIndex() != -1) {

                    //将文件中的相应内容删除
                    //先遍历所有条目，然后找出标题对应的一项
                    File file_read = new File("F:\\note.txt");
                    File file_wrote = new File("F:\\note_new.txt");

                    BufferedReader Bread = null;
                    String line;
                    BufferedWriter Bwrite = null;
                    FileWriter Fwrite = null;
                    FileReader Fread = null;
                    try {
                        Fwrite = new FileWriter(file_wrote);
                        Bwrite = new BufferedWriter(Fwrite);
                        if (!file_wrote.exists()) {
                            file_wrote.createNewFile();
                        }
                        Fread = new FileReader(file_read);
                        Bread = new BufferedReader(Fread);
                        int index = 0;
                        while ((line = Bread.readLine()) != null) {
                            if (line.contains((String) list.getSelectedValue())) {
                                continue;
                            }
                            Bwrite.write(line + "\n");
                            if (index++ == 100) {
                                Bwrite.flush();
                                index = 0;
                            }
                        }
                        Bwrite.flush();
                        Fwrite.flush();
                        Fread.close();
                        Bwrite.close();
                        Fread.close();
                        Fwrite.close();
                    } catch (IOException ex) {
                        System.out.println("文件写入失败！");
                    }
                    boolean re2 = file_wrote.renameTo(file_read);
                    boolean re1 = file_read.delete();
                    System.out.println("删除：" + re1);
                    System.out.println("命名：" + re2);

                    //将选中内容移除
                    tmp.removeElementAt(list.getLeadSelectionIndex());

                    //将tmp中的内容同步至list
                    list.setModel(tmp);
                }

                //如果没有内容被选中，则弹出提示窗口
                else {
                    JOptionPane.showMessageDialog(list, "请选择要删除的项");
                }
            }
        }
    }

    //重载方法
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == list) {
            if (e.getClickCount() == 2) {
                EditWindow editWindow = new EditWindow(PtrlS, list);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
