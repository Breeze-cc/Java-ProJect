import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private TrayIcon trayIcon;//托盘图标
    private SystemTray systemTray;//系统托盘
    private int mouseAtX = getX();
    private int mouseAtY = getY();


    public MainMenu() throws IOException, AWTException {

        //获得系统托盘的实例
        systemTray = SystemTray.getSystemTray();
        //设置大小
        this.setBounds(635, 300, 325, 485);
        //设置固定大小
        this.setResizable(false);
        //设置居中显示
        this.setLocationRelativeTo(null);
        //设置默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加画布JPanel
        this.add(new Panel(this));
        //隐藏窗体原生按钮
        this.setUndecorated(true);

        /*
        实现窗体拖动功能
         */
        //为主窗口添加鼠标监听
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //获取点击鼠标时的坐标
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
        });
        //获取拖拽的路径
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation((e.getXOnScreen() - mouseAtX), (e.getYOnScreen() - mouseAtY));//设置拖拽后，窗口的位置
            }
        });
        this.setVisible(true);

        /*
        实现窗口托盘化
         */
        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("..\\img\\note.png")));
            systemTray.add(trayIcon);       //设置托盘的图标

            //任务栏菜单栏
            PopupMenu kit_menu = new PopupMenu();
            //菜单组件
            MenuItem exit = new MenuItem("Exit");
            //添加事件响应
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
            MenuItem open = new MenuItem("Open Original Window");
            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    SwingUtilities.getWindowAncestor(Panel.delNote).setVisible(true);
                }
            });
            kit_menu.add(open);
            kit_menu.add(exit);
            //设置鼠标悬停时显示的信息
            trayIcon.setToolTip("备忘录");
            trayIcon.setPopupMenu(kit_menu);

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (AWTException e2) {
            e2.printStackTrace();
        }

        //添加鼠标响应
        trayIcon.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2)     //双击托盘窗口再现
                            setExtendedState(Frame.NORMAL);
                        setVisible(true);
                    }
                });

    }
}
