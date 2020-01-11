import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SystemTrayDemo implements ActionListener, MouseListener, WindowListener {
    TrayIcon trayIcon = null;
    Image icon = Toolkit.getDefaultToolkit().getImage("..\\笔记本.png");
    JFrame jframe;

    public SystemTrayDemo(JFrame jf) throws AWTException {
        jframe = jf;
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            PopupMenu popup = new PopupMenu();
            MenuItem newItem = new MenuItem("新建");
            newItem.addActionListener(this);
            MenuItem exitItem = new MenuItem("退出");

            popup.add(newItem);
            trayIcon = new TrayIcon(icon, "备忘录", popup);
            trayIcon.addMouseListener(this);
            systemTray.add(trayIcon);
        }
        jframe.setIconImage(icon);
        jframe.addWindowListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
