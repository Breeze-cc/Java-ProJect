import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private TrayIcon trayIcon;//托盘图标
    private SystemTray systemTray;//系统托盘

    public MainMenu() throws IOException, AWTException {

        systemTray = SystemTray.getSystemTray();//获得系统托盘的实例

        //设置标题
        this.setTitle("简易备忘录 -- By:Breeze_cc");
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
//        new SystemTrayDemo(this);
        //重画，设置可见
        this.setVisible(true);

        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("..\\笔记本.png")));
            systemTray.add(trayIcon);//设置托盘的图标，0.gif与该类文件同一目录
        }
        catch (IOException e1) {e1.printStackTrace();}
        catch (AWTException e2) {e2.printStackTrace();}
        this.addWindowListener(
                new WindowAdapter(){
                    public void windowIconified(WindowEvent e){
                        dispose();//窗口最小化时dispose该窗口
                    }
                });

        trayIcon.addMouseListener(
                new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        if(e.getClickCount() == 2)//双击托盘窗口再现
                            setExtendedState(Frame.NORMAL);
                        setVisible(true);
                    }
                });

    }
}
