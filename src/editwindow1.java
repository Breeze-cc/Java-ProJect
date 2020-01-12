import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

public class editwindow1 extends JFrame {
    private int mouseAtX = 0;
    private int mouseAtY = 0;
    private StackList PtrlS;
    private Map<String, String> map = new HashMap<String, String>();
    private JList list;
    private String key;
    private MainMenu jf;

    public editwindow1(StackList Ptrl, JList list, String key, Map<String, String> map, MainMenu jf) {

        this.key = key;
        this.list = list;
        this.map = map;
        this.PtrlS = Ptrl;
        this.jf = jf;

        //设置大小
        this.setBounds(jf.mouseAtX, jf.mouseAtY, 325, 485);
        //设置相对位置
        this.setLocationRelativeTo(null);
        //设置固定大小
        this.setResizable(false);
        //设置默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加画布JPanel
        this.add(new EditPane(PtrlS, list, key, map));
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

    }
}
