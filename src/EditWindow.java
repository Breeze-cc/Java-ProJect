import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWindow extends JFrame implements ActionListener{

    //编辑窗口包括一个文本域和三个按钮
    JTextArea showMessage;
    JButton add;
    JButton delete;
    JButton allDelete;
    StackList PtrlS;

    EditWindow(StackList Ptrl){

        this.PtrlS = Ptrl;
        //设置窗口属性
        this.setTitle("编辑备忘录");
        this.setSize(350, 400);
        this.setResizable(false);
        this.setLayout(null);

        //配置三个按钮
        showMessage = new JTextArea();
        add = new JButton("添加");
        delete = new JButton("退格");
        allDelete = new JButton("清空");


        //配置文本框
        this.add(showMessage);
        showMessage.setBounds(10, 10, 330, 300);
        showMessage.setLineWrap(true);

        //配置 添加 按钮
        this.add(add);
        add.setBounds(10, 320, 100, 50);
        add.setBackground(new Color(0, 249, 255));
        add.addActionListener(this);

        //添加 退格 按钮
        this.add(delete);
        delete.setBounds(120, 320, 100, 50);
        delete.setBackground(new Color(255, 254, 74));
        delete.addActionListener(this);

        //配置 清空 按钮
        this.add(allDelete);
        allDelete.setBounds(230, 320, 100, 50);
        allDelete.setBackground(new Color(255, 0, 0));
        allDelete.addActionListener(this);

        //重画
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "添加"){
            String str = JOptionPane.showInputDialog(this, "对备忘录进行修改", "请输入要添加的内容", JOptionPane.PLAIN_MESSAGE);
            if (str != null) {
                for (int i = 0; i < str.length(); i++) {
                    PtrlS.Push(str.charAt(i));
                }
                showMessage.setText(PtrlS.getDatas());
            }
        }

        if (e.getActionCommand() == "退格"){
            PtrlS.Pop();
            showMessage.setText(PtrlS.getDatas());
        }

        if (e.getActionCommand() == "清空"){
            PtrlS = PtrlS.Clean();
            showMessage.setText(PtrlS.getDatas());
        }
    }
}
