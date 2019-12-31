import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Panel extends JPanel {
    JButton newNote, delNote;
    ListAction l;
    public Panel(){
        //设置Panel为边界布局
        this.setLayout(null);
        //设置大背景的背景色(天蓝色SkyBlue)
        setBackground(new Color(135, 206, 235));

//        设置新建按钮的属性
        newNote = new JButton("新建");
        newNote.setBounds(50, 20, 100, 33);
        newNote.setBackground(new Color(241, 173, 70));

//        设置删除按钮的属性
        delNote = new JButton("删除");
        delNote.setBackground(new Color(255, 69, 0));
        delNote.setBounds(350, 20, 100, 33);
//        this.add(newNote, BorderLayout.LINE_START);
//        this.add(delNote, BorderLayout.LINE_END);

        //将 “新建” 和 “删除” 添加到画板上
        this.add(newNote);
        this.add(delNote);

        //新建JList和JPanel的中介JScrollPane
        JScrollPane Jsp = new JScrollPane();
        //新建新的列表
        ListAction list = new ListAction();
        //设置列表的显示方式：用一列显示
        list.setLayoutOrientation(JList.VERTICAL);
        //设置列表每次只能有一个选项被选中
        list.setSelectionMode(0);
        //为list添加事件响应
        list.addListSelectionListener(list);
        l = list;


    }

//    未完成部分
    public static ListAction getList(){
        return list;
    }
}
