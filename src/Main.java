import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
//    private static MainMenu window;
//    private MainMenu window;
    static MainMenu window;

    static {
        try {
            window = new MainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, AWTException {
        //新建窗口

    }
//    public static MainMenu getwindow(){
//        return window;
//    }
}
