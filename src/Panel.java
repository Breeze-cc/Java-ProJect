import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Panel extends JPanel {
    private JButton newNote, delNote;
    private JList list;
    private ListAction l;
    private Action_Listner listner;

    public Panel(){
        //设置Panel为边界布局
        this.setLayout(null);

        //设置大背景的背景色(天蓝色SkyBlue)
        setBackground(new Color(135, 206, 235));

        //新建新的列表
        //ListAction list = new ListAction();
        String[] test = new String[]{"我裂开了","测试"};
        list = new JList(test);

        //设置列表的显示方式：用一列显示
        list.setLayoutOrientation(JList.VERTICAL);

        //设置列表每次只能有一个选项被选中
        list.setSelectionMode(0);

        //注册监听器
        listner = new Action_Listner(list);

//        设置新建按钮的属性
        newNote = new JButton("新建");
        newNote.setBounds(50, 20, 100, 33);
        newNote.setBackground(new Color(241, 173, 70));
        newNote.addActionListener(listner);

//        设置删除按钮的属性
        delNote = new JButton("删除");
        delNote.setBackground(new Color(255, 69, 0));
        delNote.setBounds(350, 20, 100, 33);
        delNote.addActionListener(listner);
//        this.add(newNote, BorderLayout.LINE_START);
//        this.add(delNote, BorderLayout.LINE_END);

        //将 “新建” 和 “删除” 添加到画板上
        this.add(newNote);
        this.add(delNote);

        //新建JList和JPanel的中介JScrollPane
        //JScrollPane Jsp = new JScrollPane();
        //Jsp.setBounds(80,100,350,300);
        //Jsp.setBackground(Color.WHITE);
        //Jsp.add(list);
        list.setBounds(80,100,350,300);
        this.add(list);

//        //设置列表的显示方式：用一列显示
//        list.setLayoutOrientation(JList.VERTICAL);
//        //设置列表每次只能有一个选项被选中
//        list.setSelectionMode(0);

        //为list添加事件响
        //list.addListSelectionListener(list);
        //l = list;


    }

//    未完成部分
//    public static ListAction getList(){
//        return list;
//    }
}
