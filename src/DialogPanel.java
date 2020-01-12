import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogPanel extends JPanel {
    private JButton isOk;
    private JButton isNo;
    private ImageIcon OK = new ImageIcon("..\\img\\OK.png");
    private ImageIcon OK1 = new ImageIcon("..\\img\\OK1.png");
    private ImageIcon NO = new ImageIcon("..\\img\\cancle.png");
    private ImageIcon NO1 = new ImageIcon("..\\img\\canle1.png");
    private ImageIcon background = new ImageIcon("..\\img\\background.png");
    private JTextArea system;
    private StackList PtrlS;
    private int index = 0;
    static JTextArea user;

    DialogPanel(String s, StackList PtrlS, int index) {

        this.index = index;
        this.PtrlS = PtrlS;
        this.setLayout(null);


        //确定按钮的属性
        isOk = new JButton();
        isOk.setIcon(OK);
        isOk.setBounds(30, 200, 20, 20);
        isOk.setContentAreaFilled(false);
        isOk.setBorderPainted(false);
        isOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isOk.setIcon(OK1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isOk.setIcon(OK);
            }
        });
        isOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //分类显示不同的信息
                if (index == 1) {
                    String str = user.getText();
                    if (str != null) {
                        for (int i = 0; i < str.length(); i++) {
                            PtrlS.Push(str.charAt(i));
                        }
                        EditPane.showMessage.setText(PtrlS.getDatas());
                    }
                    SwingUtilities.getWindowAncestor(isOk).setVisible(false);
                } else {
                    String str = user.getText();
                    if (str != null) {
                        //将标题和时间都存入列表中
                        Panel.tmp.addElement(str + "    " + Time.getTime());
                        //将tmp中的内容同步到list
                        Panel.list.setModel(Panel.tmp);
                    }
                    SwingUtilities.getWindowAncestor(user).setVisible(false);
                }
            }
        });
        this.add(isOk);

        //设置取消按钮的属性
        isNo = new JButton();
        isNo.setIcon(NO);
        isNo.setBounds(240, 200, 20, 20);
        isNo.setContentAreaFilled(false);
        isNo.setBorderPainted(false);
        isNo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isNo.setIcon(NO1);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isNo.setIcon(NO);
            }
        });
        isNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.getWindowAncestor(isOk).setVisible(false);
            }
        });
        this.add(isNo);

        //设置系统显示的文本
        system = new JTextArea();
        system.setOpaque(false);
        system.setBounds(30, 30, 250, 50);
        system.setFont(new Font("汉仪铸字木头人W", Font.PLAIN, 20));
        system.setLineWrap(true);
        system.setEditable(false);
        system.setText(s);
        this.add(system);

        //设置用户输入的文本信息
        user = new JTextArea();
        user.setOpaque(false);
        user.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        user.setBounds(30, 130, 250, 30);
        user.setFont(new Font("华康娃娃体W5", Font.PLAIN, 14));
        user.setLineWrap(true);
        this.add(user);
        if (index == 1) {
            user.setText("输入字符串");
        } else if (index == 2) {
            user.setText("输入标题");
        }
        this.setVisible(true);
    }

    //用画笔将图片画到Panel上面
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.paintIcon(this, g, 0, 0);
    }
}
