import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {
    private String s;

    public Dialog(String s) {
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.add(new DialogPanel(s));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
