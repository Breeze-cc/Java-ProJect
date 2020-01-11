import javax.swing.*;
import java.io.IOException;

public class MainMenu extends JFrame {

    public MainMenu() throws IOException {

        //设置大小
        this.setBounds(635, 300, 325, 485);
        //设置固定大小
        this.setResizable(false);
        //设置默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加画布JPanel
        this.add(new Panel());
        //隐藏窗体
        this.setUndecorated(true);
        //重画，设置可见
        this.setVisible(true);

    }
}
