import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action_Listner implements ActionListener {
    //ListAction list;
    private JList list;
    private DefaultListModel tmp = new DefaultListModel();

    public Action_Listner(JList list){
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == "新建"){
            String str = JOptionPane.showInputDialog(list, "新建备忘录", JOptionPane.PLAIN_MESSAGE);
            tmp.addElement(str);
            list.setModel(tmp);

        }


        if(e.getActionCommand() == "删除")
        {
            if(tmp.getSize() > 0)
            {
                if(list.getSelectedIndex() != -1)
                {
                    tmp.removeElementAt(list.getLeadSelectionIndex());
                    //tmp.removeElementAt(tmp.getSize() - 1);
                    list.setModel(tmp);

                }
                else
                {
                    JOptionPane.showMessageDialog(list ,"请选择要删除的项");
                }
            }
        }

//        String str = JOptionPane.showInputDialog(this, "请输入备忘录标题", "新建备忘录", JOptionPane.PLAIN_MESSAGE);
//        if (str != null){
//            tmp.addElement(str);
////            未完成部份
//            //this.list =
//        }
    }
}
