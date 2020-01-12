import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Dialog extends JDialog {
    private String s;
    private int mouseAtX = 0;
    private int mouseAtY = 0;
    private StackList PtrlS;
    private int index = 0;
    public Dialog(String s, StackList PtrlS, int index) {

        this.index = index;
        this.PtrlS = PtrlS;
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.add(new DialogPanel(s, PtrlS, index));
        this.setLocationRelativeTo(null);
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
    }

}
