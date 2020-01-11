import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Panel extends JPanel {
    private JButton newNote, delNote;
    private JList list;
    private Action_Listner listner;
    private DefaultListModel tmp = new DefaultListModel();
    Map<String, String> map = new HashMap<String, String>();
    ImageIcon image = new ImageIcon("H:\\Code_Java\\ProJect\\background.png");
    Font menu_font = new Font("汉仪铸字木头人W", Font.PLAIN, 20);
    Font article_font = new Font("汉仪铸字木头人W", Font.PLAIN, 16);
    public static String time;
    public Panel() throws IOException, FileNotFoundException {
        //设置Panel为边界布局
        this.setLayout(null);

        //设置大背景的背景色(天蓝色SkyBlue)
//        setBackground(new Color(135, 206, 235));

        //新建新的列表
        String[] test = new String[]{};
        list = new JList(test);

        //List的美化
        FlowLayout f = new FlowLayout();
        list.setFixedCellWidth(280);
        list.setFixedCellHeight(60);    //间距
        list.setFont(article_font);     //字体
        list.setOpaque(false);
        list.setSelectionBackground(new Color(125,122,83));     //设置选中条目的颜色
        //设置未选中条目的颜色以及边框
        list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    if (!list.isSelectedIndex(i)) {
                        setBackground(new Color(250, 244, 165));
                    }
                }
                Border line_border = BorderFactory.createLineBorder(new Color(54,64,66), 5, false);
                Border title_border = BorderFactory.createTitledBorder(line_border, Time.getTime(), TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.ITALIC, 12), new Color(0, 0, 0));
                Border raise_border = BorderFactory.createRaisedBevelBorder();
                Border b1 = BorderFactory.createCompoundBorder(line_border, title_border);
//                setBorder(BorderFactory.createCompoundBorder();
                setBorder(b1);
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;

            }

        });

        //注册监听器
        listner = new Action_Listner(list, tmp, map);

        //为list添加监听，事件响应
        list.addMouseListener(listner);

        //设置列表的显示方式：用一列显示
        list.setLayoutOrientation(JList.VERTICAL);

        //设置列表每次只能有一个选项被选中
        list.setSelectionMode(0);

        //打开之后先读取文件中已有的项并添加到list中
        String filename = "note.csv";
        File inMyPC = new File(filename);

        //如果不存在该文件，就新建一个
        if (!inMyPC.exists()) {
            try {
                inMyPC.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //从文件中读取txt的键
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("note.csv"), "UTF-8"));
        String line = null;
        while ((line = in.readLine()) != null) {
//            HashMap<String, String> item = new HashMap<String, String>();
            String[] itemArray = line.split(",");
            map.put(itemArray[0], itemArray[1]);
            tmp.addElement(itemArray[0]);
        }
        list.setModel(tmp);

//        设置新建按钮的属性
        newNote = new JButton("新建");
        newNote.setBounds(50, 20, 80, 33);
        newNote.setBackground(new Color(250, 244, 165));
        newNote.setFont(menu_font);
        newNote.addActionListener(listner);

//        设置删除按钮的属性
        delNote = new JButton("删除");
        delNote.setBackground(new Color(250, 128, 114));
        delNote.setBounds(170, 20, 80, 33);
        delNote.setFont(menu_font);
        delNote.addActionListener(listner);
//        this.add(newNote, BorderLayout.LINE_START);
//        this.add(delNote, BorderLayout.LINE_END);

        //将 “新建” 和 “删除” 添加到画板上
        this.add(newNote);
        this.add(delNote);

        //设置列表所占面积的位置和大小
        list.setBounds(8, 100, 278, 339);

        //将列表添加到画板上
        this.add(list);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
        image.paintIcon(list, g, 0, 0);
    }
}
