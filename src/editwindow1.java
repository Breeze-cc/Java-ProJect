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

        //���ô�С
        this.setBounds(jf.mouseAtX, jf.mouseAtY, 325, 485);
        //�������λ��
        this.setLocationRelativeTo(null);
        //���ù̶���С
        this.setResizable(false);
        //����Ĭ�Ϲرշ�ʽ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ӻ���JPanel
        this.add(new EditPane(PtrlS, list, key, map));
        //���ش���
        this.setUndecorated(true);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                /*
                 * ��ȡ������ʱ������
                 */
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation((e.getXOnScreen() - mouseAtX), (e.getYOnScreen() - mouseAtY));//������ק�󣬴��ڵ�λ��
            }
        });
        this.setVisible(true);

    }
}
