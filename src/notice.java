import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class notice extends JFrame {
    private JTextPane notice1;

    public notice(String s){

        //将窗口设置为透明并从中间显示
        this.setLocationRelativeTo(null);
        this.setSize(150, 50);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));

        //设置文本框中的内容为给定
        JTextPane notice1 = new JTextPane();
        notice1.setText(s);

        //设置通知文本的格式
        notice1.setFont(new Font("汉仪铸字木头人W", Font.ITALIC, 15));
        notice1.setOpaque(false);
        notice1.setBounds(90, 150, 0, 0);
        notice1.setForeground(Color.lightGray);
        this.add(notice1);
        this.setVisible(true);

        //通过线程设置出现一秒后自动消失
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
