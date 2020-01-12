import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private TrayIcon trayIcon;//����ͼ��
    private SystemTray systemTray;//ϵͳ����
    private int mouseAtX = getX();
    private int mouseAtY = getY();


    public MainMenu() throws IOException, AWTException {

        //���ϵͳ���̵�ʵ��
        systemTray = SystemTray.getSystemTray();
        //���ô�С
        this.setBounds(635, 300, 325, 485);
        //���ù̶���С
        this.setResizable(false);
        //���þ�����ʾ
        this.setLocationRelativeTo(null);
        //����Ĭ�Ϲرշ�ʽ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ӻ���JPanel
        this.add(new Panel(this));
        //���ش���ԭ����ť
        this.setUndecorated(true);

        /*
        ʵ�ִ����϶�����
         */
        //Ϊ���������������
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //��ȡ������ʱ������
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
        });
        //��ȡ��ק��·��
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation((e.getXOnScreen() - mouseAtX), (e.getYOnScreen() - mouseAtY));//������ק�󣬴��ڵ�λ��
            }
        });
        this.setVisible(true);

        /*
        ʵ�ִ������̻�
         */
        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("..\\img\\note.png")));
            systemTray.add(trayIcon);       //�������̵�ͼ��

            //�������˵���
            PopupMenu kit_menu = new PopupMenu();
            //�˵����
            MenuItem exit = new MenuItem("Exit");
            //����¼���Ӧ
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
            //���������ͣʱ��ʾ����Ϣ
            trayIcon.setToolTip("����¼");
            trayIcon.setPopupMenu(kit_menu);

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (AWTException e2) {
            e2.printStackTrace();
        }

        //��������Ӧ
        trayIcon.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2)     //˫�����̴�������
                            setExtendedState(Frame.NORMAL);
                        setVisible(true);
                    }
                });

    }
}
