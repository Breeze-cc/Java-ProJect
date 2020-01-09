import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class Panel extends JPanel {
    private JButton newNote, delNote;
    private JList list;
    private Action_Listner listner;
    private DefaultListModel tmp = new DefaultListModel();

    public Panel() throws IOException, FileNotFoundException{
        //设置Panel为边界布局
        this.setLayout(null);

        //设置大背景的背景色(天蓝色SkyBlue)
        setBackground(new Color(135, 206, 235));

        //新建新的列表
        String[] test = new String[]{};
        list = new JList(test);

        //注册监听器
        listner = new Action_Listner(list, tmp);

        //为list添加监听，事件响应
        list.addMouseListener(listner);

        //设置列表的显示方式：用一列显示
        list.setLayoutOrientation(JList.VERTICAL);

        //设置列表每次只能有一个选项被选中
        list.setSelectionMode(0);

        //打开之后先读取文件中已有的项并添加到list中
        String filename = "F:\\note.txt";
        File inMyPC = new File(filename);

        //如果不存在该文件，就新建一个
        if (! inMyPC.exists()){
            try {
                inMyPC.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //从文件中读取txt的键
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\note.txt"), "UTF-8"));
        String line = null;
        while((line = in.readLine()) != null){
            HashMap<String, String> item = new HashMap<String, String>();
            String[] itemArray = line.split(",");
            tmp.addElement(itemArray[0]);
            list.setModel(tmp);
        }

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

        //设置列表所占面积的位置和大小
        list.setBounds(8,100,510,600);

        //将列表添加到画板上
        this.add(list);
    }
}
