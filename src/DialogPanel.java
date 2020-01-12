import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPanel extends JPanel {
    JButton isOk;
    JButton isNo;
    ImageIcon OK = new ImageIcon("..\\确定.png");
    ImageIcon OK1 = new ImageIcon("..\\确定1.png");
    ImageIcon NO = new ImageIcon("..\\取消.png");
    ImageIcon NO1 = new ImageIcon("..\\取消1.png");
    ImageIcon background = new ImageIcon("..\\img\\background.png");
    JTextArea system;
    JTextArea user;

    DialogPanel(String s){
        this.setLayout(null);

        isOk = new JButton();
        isOk.setIcon(OK);
        isOk.setBounds(40, 250, 20, 20);
        isOk.setContentAreaFilled(false);
        isOk.setBorderPainted(false);
        isOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        this.add(isOk);

        isNo = new JButton();
        isNo.setIcon(NO);
        isNo.setBounds(240, 250, 20, 20);
        isNo.setContentAreaFilled(false);
        isNo.setBorderPainted(false);
        isNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        this.add(isNo);

        system = new JTextArea();
        system.setOpaque(false);
        system.setBounds(30, 30, 250, 50);
        system.setFont(new Font("汉仪铸字木头人W", Font.BOLD, 20));
        system.setText(s);
        this.add(system);

        this.setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        background.paintIcon(this, g, 0, 0);
    }
}
