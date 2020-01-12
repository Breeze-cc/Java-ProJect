import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private TrayIcon trayIcon;//托盘图标
    private SystemTray systemTray;//系统托盘

    int mouseAtX = getX();
    int mouseAtY = getY();

    public MainMenu() throws IOException, AWTException {

        systemTray = SystemTray.getSystemTray();//获得系统托盘的实例

        //设置标题
        this.setTitle("简易备忘录 -- By:Breeze_cc");
        //设置大小
        this.setBounds(635, 300, 325, 485);
        //设置固定大小
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        //设置默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加画布JPanel
        this.add(new Panel(this));
        //隐藏窗体
        this.setUndecorated(true);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                /*
                 * 获取点击鼠标时的坐标
                 */
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation((e.getXOnScreen() - mouseAtX), (e.getYOnScreen() - mouseAtY));//设置拖拽后，窗口的位置
            }
        });
        this.setVisible(true);

        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("..\\img\\备忘录.png")));
            systemTray.add(trayIcon);//设置托盘的图标
            PopupMenu kit_menu = new PopupMenu();
            MenuItem exit = new MenuItem("Exit");
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
            trayIcon.setToolTip("备忘录");
            trayIcon.setPopupMenu(kit_menu);

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (AWTException e2) {
            e2.printStackTrace();
        }

        trayIcon.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2)//双击托盘窗口再现
                            setExtendedState(Frame.NORMAL);
                        setVisible(true);
                    }
                });

    }
}
