import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class notice extends JFrame {
    private JTextPane notice1;

    public notice(String s){

        //����������Ϊ͸�������м���ʾ
        this.setLocationRelativeTo(null);
        this.setSize(150, 50);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));

        //�����ı����е�����Ϊ����
        JTextPane notice1 = new JTextPane();
        notice1.setText(s);

        //����֪ͨ�ı��ĸ�ʽ
        notice1.setFont(new Font("��������ľͷ��W", Font.ITALIC, 15));
        notice1.setOpaque(false);
        notice1.setBounds(90, 150, 0, 0);
        notice1.setForeground(Color.lightGray);
        this.add(notice1);
        this.setVisible(true);

        //ͨ���߳����ó���һ����Զ���ʧ
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVisible(false);
            }
        });
    }
}
