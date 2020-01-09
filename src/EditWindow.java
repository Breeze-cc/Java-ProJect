import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EditWindow extends JFrame implements ActionListener{

    //编辑窗口包括一个文本域和四个按钮
    JTextArea showMessage;
    JButton add;
    JButton delete;
    JButton allDelete;
    JButton save;
    StackList PtrlS;
    JList list;
    Map<String, String> map = new HashMap<String, String>();

    EditWindow(StackList Ptrl, JList list){

        this.list = list;
        this.PtrlS = Ptrl;
        //设置窗口属性
        this.setTitle("编辑备忘录");
        this.setSize(350, 400);
        this.setResizable(false);
        this.setLayout(null);

        //配置四个按钮
        showMessage = new JTextArea();
        add = new JButton("添加");
        delete = new JButton("退格");
        allDelete = new JButton("清空");
        save = new JButton("保存");

        //配置文本框
        this.add(showMessage);
        showMessage.setBounds(10, 10, 330, 300);
        showMessage.setLineWrap(true);

        //配置 添加 按钮
        this.add(add);
        add.setBounds(10, 320, 70, 50);
        add.setBackground(new Color(0, 249, 255));
        add.addActionListener(this);

        //添加 退格 按钮
        this.add(delete);
        delete.setBounds(90, 320, 70, 50);
        delete.setBackground(new Color(255, 254, 74));
        delete.addActionListener(this);

        //配置 清空 按钮
        this.add(allDelete);
        allDelete.setBounds(170, 320, 70, 50);
        allDelete.setBackground(new Color(255, 0, 0));
        allDelete.addActionListener(this);

        //配置 保存 按钮
        this.add(save);
        save.setBounds(250, 320, 70, 50);
        save.setBackground(new Color(142, 251, 251));
        save.addActionListener(this);

        //重画
        this.setVisible(true);

    }

    //编辑窗口的触发器
    @Override
    public void actionPerformed(ActionEvent e) {
        //添加 按钮的触发器
        if (e.getActionCommand() == "添加"){
            String str = JOptionPane.showInputDialog(this, "对备忘录进行修改", "请输入要添加的内容", JOptionPane.PLAIN_MESSAGE);
            if (str != null) {
                for (int i = 0; i < str.length(); i++) {
                    PtrlS.Push(str.charAt(i));
                }
                showMessage.setText(PtrlS.getDatas());
            }
        }

        //退格 按钮的触发器
        if (e.getActionCommand() == "退格"){
            PtrlS.Pop();
            showMessage.setText(PtrlS.getDatas());
        }

        //清空 按钮的触发器
        if (e.getActionCommand() == "清空"){
            PtrlS = PtrlS.Clean();
            showMessage.setText(PtrlS.getDatas());
        }

        //保存 按钮的触发器
        if (e.getActionCommand() == "保存"){
            String filename = "F:\\note.cvs";
            File inMyPC = new File(filename);

            //读取备忘录标题
            String title = (String) list.getSelectedValue();

            //写入map中
            map.put(title, PtrlS.getDatas());

            //写入文件中
            try {
                //文件追加，使写入的不会覆盖原本的内容
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC, true)));
                out.write(title);
                out.write(",");
                out.write(PtrlS.getDatas());
                out.newLine();
                out.flush();
                out.close();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "保存成功！");
        }
    }
}
