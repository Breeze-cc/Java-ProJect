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
    static JTextArea user;
    private StackList PtrlS;
    private int index = 0;
//    private static String textStr;

    DialogPanel(String s, StackList PtrlS, int index) {

        this.index = index;
        this.PtrlS = PtrlS;
        this.setLayout(null);

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

                if (index == 1) {
                    String str = user.getText();

                    if (str != null) {
                        for (int i = 0; i < str.length(); i++) {
                            PtrlS.Push(str.charAt(i));
                        }
                        EditPane.showMessage.setText(PtrlS.getDatas());
                    }
                    SwingUtilities.getWindowAncestor(isOk).setVisible(false);
                }
                else{
                    String str = user.getText();
                    if (str != null) {
                        //����б���Ŀ
                        Panel.tmp.addElement(str);

                        //��tmp�е�����ͬ����list
                        Panel.list.setModel(Panel.tmp);
                    }
                    SwingUtilities.getWindowAncestor(user).setVisible(false);
                }
            }
        });
        this.add(isOk);

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

        system = new JTextArea();
        system.setOpaque(false);
        system.setBounds(30, 30, 250, 50);
        system.setFont(new Font("��������ľͷ��W", Font.PLAIN, 20));
        system.setLineWrap(true);
        system.setEditable(false);
        system.setText(s);
        this.add(system);

        this.setVisible(true);

        user = new JTextArea();
        user.setOpaque(false);
        user.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        user.setBounds(30, 130, 250, 30);
        user.setFont(new Font("����������W5", Font.PLAIN, 14));
        user.setLineWrap(true);
        if (index == 1) {
            user.setText("�ڴ�����Ҫ��ӵ��ַ���");
        }
        else {
            user.setText("�ڴ������±���¼����");
        }
        this.add(user);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.paintIcon(this, g, 0, 0);
    }
}
