import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Map;

public class EditPane extends JPanel implements ActionListener {
    static JTextPane showMessage, timetext;
    private JButton add, delete, allDelete, save, back, finish, color_button, showtime;
    private StackList PtrlS;
    private JList list;
    private String key;
    private Map<String, String> map;

    ImageIcon image_add = new ImageIcon("..\\img\\add.png");
    ImageIcon image_add1 = new ImageIcon("..\\img\\add1.png");
    ImageIcon image_bk = new ImageIcon("..\\img\\backspace.png");
    ImageIcon image_bk1 = new ImageIcon("..\\img\\backspace1.png");
    ImageIcon image_alldel = new ImageIcon("..\\img\\clear.png");
    ImageIcon image_alldel1 = new ImageIcon("..\\img\\clear1.png");
    ImageIcon image_save = new ImageIcon("..\\img\\save.png");
    ImageIcon image_save1 = new ImageIcon("..\\img\\save1.png");
    ImageIcon image_back = new ImageIcon("..\\img\\back.png");
    ImageIcon image_back1 = new ImageIcon("..\\img\\back1.png");
    ImageIcon image = new ImageIcon("..\\img\\background.png");
    ImageIcon image_finish = new ImageIcon("..\\img\\finish.png");
    ImageIcon image_finish1 = new ImageIcon("..\\img\\finish1.png");
    ImageIcon image_color = new ImageIcon("..\\img\\color.png");
    ImageIcon image_color1 = new ImageIcon("..\\img\\color1.png");
    Font font1 = new Font("华康娃娃体W5(P)", Font.PLAIN, 16);

    public EditPane(StackList PtrlS, JList list, String key, Map<String, String> map) {

        this.key = key;
        this.list = list;
        this.map = map;
        this.PtrlS = PtrlS;

        //如果map的键值不为null，防止产生空指针异常
        if (map.get(key) != null) {
            for (int i = 0; i < map.get(key).length(); i++) {
                if (map.get(key).charAt(i) == '\u0000') {
                    break;
                }
                //每次打开程序初始化，将文件中的数据读入界面中显示
                PtrlS.Push(map.get(key).charAt(i));
            }
        }

        //初始化六个按钮：返回，完成，添加，删除，保存，清空
        showMessage = new JTextPane();
        add = new JButton();
        delete = new JButton();
        allDelete = new JButton();
        save = new JButton();
        back = new JButton();
        finish = new JButton();
        color_button = new JButton();
        showtime = new JButton();
        timetext = new JTextPane();

        //设置为绝对布局，方便美化
        this.setLayout(null);

        //配置文本框
        showMessage.setFont(font1);
        showMessage.setBounds(15, 50, 280, 200);
        showMessage.setOpaque(false);
        showMessage.setEditable(false);
        //showMessage的初始文本为栈中的元素
        showMessage.setText(PtrlS.getDatas());
        this.add(showMessage);

        //配置 添加 按钮
        add.setIcon(image_add);
        add.setBounds(18, 460, 20, 20);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.addActionListener(this);
        //设置悬停效果
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                add.setIcon(image_add1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add.setIcon(image_add);
            }
        });
        this.add(add);

        //添加 退格 按钮
        delete.setIcon(image_bk);
        delete.setBounds(287, 460, 20, 20);
        delete.addActionListener(this);
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delete.setIcon(image_bk1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setIcon(image_bk);
            }
        });
        this.add(delete);

        //配置 清空 按钮
        allDelete.setIcon(image_alldel);
        allDelete.setBounds(225, 460, 20, 20);
        allDelete.addActionListener(this);
        allDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                allDelete.setIcon(image_alldel1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                allDelete.setIcon(image_alldel);
            }
        });
        allDelete.setBorderPainted(false);
        allDelete.setContentAreaFilled(false);
        this.add(allDelete);

        //配置 保存 按钮
        save.setIcon(image_save);
        save.setBounds(90, 460, 20, 20);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        save.addActionListener(this);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setIcon(image_save1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                save.setIcon(image_save);
            }
        });
        this.add(save);

        //配置 色彩 按钮
        color_button.setIcon(image_color);
        color_button.setBounds(155, 460, 20, 20);
        color_button.setContentAreaFilled(false);
        color_button.setBorderPainted(false);
        color_button.addActionListener(this);
        color_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                color_button.setIcon(image_color1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                color_button.setIcon(image_color);
            }
        });
        this.add(color_button);

        //添加 返回 按钮
        back.setIcon(image_back);
        back.setBounds(10, 10, 90, 20);
        back.addActionListener(this);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(image_back1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(image_back);
            }
        });
        this.add(back);

        //配置 完成 按钮
        finish.setIcon(image_finish);
        finish.setBounds(270, 13, 50, 20);
        finish.setContentAreaFilled(false);
        finish.setBorderPainted(false);
        finish.addActionListener(this);
        finish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                finish.setIcon(image_finish1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                finish.setIcon(image_finish);
            }
        });
        this.add(finish);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //添加 按钮的触发器
        if (e.getSource() == add) {
            new Dialog("      请输入添加的内容", PtrlS, 1);
        }

        //退格 按钮的触发器
        if (e.getSource() == delete) {
            PtrlS.Pop();
            showMessage.setText(PtrlS.getDatas());
        }

        //清空 按钮的触发器
        if (e.getSource() == allDelete) {
            PtrlS = PtrlS.Clean();
            showMessage.setText(PtrlS.getDatas());
        }

        //保存 按钮的触发器
        if (e.getSource() == save) {
            String filename = "..\\note.txt";
            File inMyPC = new File(filename);
            //读取备忘录标题
            String title = (String) list.getSelectedValue();
            //写入map中
            map.put(title, PtrlS.getDatas());
            //写入文件中
            try {
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
            //提示窗口
            new notice("保存成功！");
        }

        //返回 按钮的触发器
        if (e.getSource() == back) {
            //关闭当前界面，打开主界面
            SwingUtilities.getWindowAncestor(Panel.delNote).setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        }

        //完成 按钮的触发器（保存加返回）
        if (e.getSource() == finish) {
            //前半部分为保存
            String filename = "..\\note.txt";
            File inMyPC = new File(filename);
            //读取备忘录标题
            String title = (String) list.getSelectedValue();
            //写入map中
            map.put(title, PtrlS.getDatas());
            //写入文件中
            try {
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
            //返回
            SwingUtilities.getWindowAncestor(this).dispose();
            SwingUtilities.getWindowAncestor(Panel.delNote).setVisible(true);
        }

        //颜色 按钮的触发器
        if (e.getSource() == color_button) {
            Color color1 = JColorChooser.showDialog(null, "color title", Color.BLACK);

            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setForeground(attr, color1);
            //设置颜色属性，参数为color类型。
            showMessage.setCharacterAttributes(attr, true);
        }
    }
}
