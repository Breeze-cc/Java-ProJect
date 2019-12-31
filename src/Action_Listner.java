import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action_Listner extends JFrame implements ActionListener {
    ListAction list;
    DefaultListModel tmp = new DefaultListModel();
    public Action_Listner(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog(this, "请输入备忘录标题", "新建备忘录", JOptionPane.PLAIN_MESSAGE);
        if (str != null){
            tmp.addElement(str);
//            未完成部份
            this.list =
        }
    }
}
