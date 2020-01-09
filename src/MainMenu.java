import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu(){
        //设置标题
        this.setTitle("简易备忘录 -- By:Breeze_cc");
        //设置大小
        this.setBounds(10, 10, 540, 720);
        //设置固定大小
        this.setResizable(false);
        //设置默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加画布JPanel
        this.add(new Panel());
        //重画，设置可见
        this.setVisible(true);

    }
}
