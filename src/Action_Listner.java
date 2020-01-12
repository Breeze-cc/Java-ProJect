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

    //重写各种相应
    @Override
    public void actionPerformed(ActionEvent e) {

        //新建按钮的触发器
        if (e.getSource() == Panel.newNote) {
            //弹出新的窗口输入新备忘录的标题
            //index决定是新建、添加还是提醒窗口
            new Dialog("      请输入新备忘录标题", PtrlS, 2);
        }

        //删除按钮的触发器
        if (e.getSource() == Panel.delNote) {
            //如果列表的条目数量大于0
            if (tmp.getSize() > 0) {
                //如果有内容被选中
                if (list.getSelectedIndex() != -1) {
                    //从map中移除被选中的条目先
                    map.remove(list.getSelectedValue());
                    //打开文件等
                    String filename = "..\\note.txt";
                    File inMyPC = new File(filename);
                    if (inMyPC.exists()){
                        boolean a = inMyPC.delete();
                        try {
                            inMyPC.createNewFile();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        //使写入的会覆盖原本的内容
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                        for (String key : map.keySet()) {
                            String value = map.get(key);
                            out.write(key.trim());
                            out.write("€");
                            out.write(value.trim());
                            out.newLine();
                            out.flush();
                        }
                        out.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //将选中内容从List移除
                    tmp.removeElementAt(list.getLeadSelectionIndex());

                    //将tmp中的内容同步至list
                    list.setModel(tmp);
                    new notice("删除成功！");
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
