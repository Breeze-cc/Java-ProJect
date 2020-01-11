import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;

public class EditWindow extends JFrame implements ActionListener {

    //编辑窗口包括一个文本域和四个按钮
    JTextArea showMessage;
    JButton add, delete, allDelete, save, back, finish;
    StackList PtrlS;
    JList list;
    String key;
    Map<String, String> map;

    ImageIcon image_add = new ImageIcon("..\\img\\添加.png");
    ImageIcon image_add1 = new ImageIcon("..\\img\\添加.png");
    ImageIcon image_bk = new ImageIcon("..\\img\\退格.png");
    ImageIcon image_bk1 = new ImageIcon("..\\img\\退格1.png");
    ImageIcon image_alldel = new ImageIcon("..\\img\\清空.png");
    ImageIcon image_alldel1 = new ImageIcon("..\\img\\清空1.png");
    ImageIcon image_save = new ImageIcon("..\\img\\保存");
    ImageIcon image_save1 = new ImageIcon("..\\img\\保存1");
    ImageIcon image_back = new ImageIcon("..\\img\\返回.png");
    ImageIcon image_back1 = new ImageIcon("..\\img\\返回1.png");

    int mouseAtX = 0;
    int mouseAtY = 0;

    EditWindow(StackList Ptrl, JList list, String key, Map<String, String> map) {
        //System.out.println(map);

        this.key = key;
        this.map = map;
        this.list = list;
        this.PtrlS = Ptrl;
        System.out.println(map.get(key));
        if (map.get(key) != null) {
            for (int i = 0; i < map.get(key).length(); i++) {
                Ptrl.Push(map.get(key).charAt(i));
            }
        }
        //设置大小
        this.setBounds(635, 300, 325, 485);
        //设置固定大小
        this.setResizable(false);
        //设置默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //隐藏窗体
        this.setUndecorated(true);
        //设置出现位置
        this.setLocationRelativeTo(Main.window);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                /*
                 * 获取点击鼠标时的坐标
                 */
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation((e.getXOnScreen() - mouseAtX), (e.getYOnScreen() - mouseAtY));//设置拖拽后，窗口的位置
            }
        });
        this.setLayout(null);

        //配置四个按钮
        showMessage = new JTextArea();
        add = new JButton(Panel.new_image);
        delete = new JButton(image_bk);
        allDelete = new JButton(image_alldel);
        save = new JButton(image_save);
        back = new JButton(image_back);
        finish = new JButton("完成");

        //配置文本框
        showMessage.setPreferredSize(new Dimension(getWidth(), getHeight()));
        showMessage.setOpaque(false);
        showMessage.setLineWrap(true);
        showMessage.setEditable(false);
        showMessage.setText(PtrlS.getDatas());
        this.add(showMessage);

        //配置 添加 按钮
        add.setBounds(10, 320, 70, 50);
        add.setBackground(new Color(0, 249, 255));
        add.addActionListener(this);
        this.add(add);

        //添加 退格 按钮
        delete.setBounds(90, 320, 70, 50);
        delete.setBackground(new Color(255, 254, 74));
        delete.addActionListener(this);
        this.add(delete);

        //配置 清空 按钮
        allDelete.setBounds(170, 320, 70, 50);
        allDelete.setBackground(new Color(255, 0, 0));
        allDelete.addActionListener(this);
        this.add(allDelete);

        //配置 保存 按钮
        save.setBounds(250, 320, 70, 50);
        save.setBackground(new Color(142, 251, 251));
        save.addActionListener(this);
        this.add(save);

        ImageIcon background = new ImageIcon("..\\img\\background.png");// 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        // 把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // 内容窗格默认的布局管理器为BorderLayout
        imagePanel.setLayout(new FlowLayout());

        this.getLayeredPane().setLayout(null);
        // 把背景图片添加到分层窗格的最底层作为背景
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);

        //重画
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        //添加 按钮的触发器
        if (e.getActionCommand() == "添加") {
            String str = JOptionPane.showInputDialog(this, "对备忘录进行修改", "请输入要添加的内容", JOptionPane.PLAIN_MESSAGE);
            if (str != null) {
                for (int i = 0; i < str.length(); i++) {
                    PtrlS.Push(str.charAt(i));
                }
                showMessage.setText(PtrlS.getDatas());
            }
        }

        //退格 按钮的触发器
        if (e.getActionCommand() == "退格") {
            PtrlS.Pop();
            showMessage.setText(PtrlS.getDatas());
        }

        //清空 按钮的触发器
        if (e.getActionCommand() == "清空") {
            PtrlS = PtrlS.Clean();
            showMessage.setText(PtrlS.getDatas());
        }

        //保存 按钮的触发器
        if (e.getActionCommand() == "保存") {
            String filename = "..\\img\\note.csv";
            File inMyPC = new File(filename);

            //读取备忘录标题
            String title = (String) list.getSelectedValue();

            //写入map中
            map.put(title, PtrlS.getDatas());
            //System.out.println(map);

            //写入文件中
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    out.write(key);
                    out.write(",");
                    out.write(value);
                    out.newLine();
                    out.flush();
                }
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "保存成功！");

        }
    }
}
