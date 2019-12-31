import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    JButton newNote, delNote;
    public Panel(){}

    //画笔
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //设置背景色
        setBackground(Color.GREEN);
        newNote = new JButton("新建");
        newNote = new JButton("删除");
        this.add(newNote);
        this.add(delNote);
    }
}
