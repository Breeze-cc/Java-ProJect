import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Map;

public class Action_Listner implements ActionListener, ListSelectionListener, MouseListener {
    //ListAction list;
    private JList list;
    private DefaultListModel tmp;
    private Map<String, String> map;
    private StackList PtrlS;
    private MainMenu jf;
    public Action_Listner(StackList PtrlS, JList list, DefaultListModel tmp, Map<String, String> map, MainMenu jf) {
        this.list = list;
        this.tmp = tmp;
        this.map = map;
        this.PtrlS = PtrlS;
        this.jf = jf;
    }
    JLabel jump;
    @Override
    public void actionPerformed(ActionEvent e) {

        //如果触发响应的器件上的字符串是 “新建”
        if (e.getSource() == Panel.newNote) {
            //弹出新的窗口输入新备忘录的标题
//            String str = JOptionPane.showInputDialog(list, jump, "新建备忘录", JOptionPane.PLAIN_MESSAGE);
            new Dialog("      请输入新备忘录标题", PtrlS, 2);
        }

        //如果触发响应的器件上的字符串是 “删除”
        if (e.getSource() == Panel.delNote) {

            //如果列表的条目数量大于0
            if (tmp.getSize() > 0) {

                //如果有内容被选中
                if (list.getSelectedIndex() != -1) {


                    map.remove(list.getSelectedValue());

                    String filename = "..\\img\\note.txt";
                    File inMyPC = new File(filename);
                    try {
                        //文件追加，使写入的不会覆盖原本的内容
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                        for (String key : map.keySet()) {
                            String value = map.get(key);
                            out.write(key.trim());
                            out.write("€");
                            out.write(value.trim());
                            out.newLine();
                            out.flush();
                        }
                        out.newLine();
                        out.flush();
                        out.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //将选中内容移除
                    tmp.removeElementAt(list.getLeadSelectionIndex());

                    //将tmp中的内容同步至list
                    list.setModel(tmp);
                    JOptionPane.showMessageDialog(list, "删除成功！");
                }

                //如果没有内容被选中，则弹出提示窗口
                else {
                    JOptionPane.showMessageDialog(list, "请选择要删除的项");
                }
            }
        }
    }

    //重载方法,鼠标双击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == list) {
            if (list.getSelectedIndex() != -1) {
                if (e.getClickCount() == 2) {
                    String key = (String) list.getSelectedValue();
                        editwindow1 editwindow = new editwindow1(PtrlS, list, key, map, jf);
                        SwingUtilities.getWindowAncestor(Panel.delNote).dispose();
                        PtrlS = PtrlS.Clean();
//                    }
                }
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
