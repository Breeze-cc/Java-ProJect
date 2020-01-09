import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action_Listner implements ActionListener , ListSelectionListener {
    //ListAction list;
    private JList list;
    private DefaultListModel tmp = new DefaultListModel();

    StackList PtrlS = new StackList();

    public Action_Listner(JList list){
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        //如果触发响应的器件上的字符串是 “新建”
        if(e.getActionCommand() == "新建"){

            //弹出新的窗口输入新备忘录的标题
            String str = JOptionPane.showInputDialog(list, "新建备忘录", JOptionPane.PLAIN_MESSAGE);

            //添加列表项目
            tmp.addElement(str);

            //将tmp中的内容同步到list
            list.setModel(tmp);
        }

        //如果触发响应的器件上的字符串是 “删除”
        if(e.getActionCommand() == "删除"){

            //如果列表的条目数量大于0
            if(tmp.getSize() > 0) {

                //如果有内容被选中
                if(list.getSelectedIndex() != -1) {

                    //将选中内容移除
                    tmp.removeElementAt(list.getLeadSelectionIndex());

                    //将tmp中的内容同步至list
                    list.setModel(tmp);

                }

                //如果没有内容被选中，则弹出提示窗口
                else {
                    JOptionPane.showMessageDialog(list ,"请选择要删除的项");
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        EditWindow editwindow = new EditWindow(PtrlS, list);
    }
}
