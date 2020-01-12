import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Panel extends JPanel {
    static JButton newNote, delNote;
    private JList list;
    //    private Action_Listner listner;
    private DefaultListModel tmp = new DefaultListModel();
    Map<String, String> map = new HashMap<String, String>();
    StackList PtrlS = new StackList();
    ImageIcon image = new ImageIcon("..\\img\\background.png");
    Font article_font = new Font("汉仪铸字木头人W", Font.PLAIN, 16);
    static ImageIcon close_image = new ImageIcon("..\\img\\关闭.png");
    static ImageIcon close_image1 = new ImageIcon("..\\img\\关闭1.png");
    static ImageIcon mini_image = new ImageIcon("..\\img\\缩小.png");
    static ImageIcon mini_image1 = new ImageIcon("..\\img\\缩小1.png");
    static ImageIcon new_image = new ImageIcon("..\\img\\新建.png");
    static ImageIcon new_image1 = new ImageIcon("..\\img\\新建1.png");
    static ImageIcon del_image = new ImageIcon("..\\img\\删除.png");
    static ImageIcon del_image1 = new ImageIcon("..\\img\\删除1.png");
    JScrollPane jsp;
    MainMenu jf;

    public Panel(MainMenu jf) throws IOException, FileNotFoundException {
        this.jf = jf;

        //设置Panel为绝对布局
        this.setLayout(null);

        //新建新的列表
        String[] test = new String[]{};
        list = new JList();

        //设置最小化按钮
        JButton button_mini = new JButton();
        button_mini.setIcon(mini_image);
        button_mini.setBounds(199, 18, 20, 20);
        button_mini.setBorderPainted(false);
        button_mini.setContentAreaFilled(false);

        button_mini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_mini.setIcon(mini_image1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_mini.setIcon(mini_image);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.getWindowAncestor(Panel.delNote).dispose();
            }
        });
        //设置关闭按钮
        JButton button_close = new JButton();
        button_close.setIcon(close_image);
        button_close.setBounds(290, 18, 20, 20);
        button_close.setBorderPainted(false);
        button_close.setContentAreaFilled(false);
        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        button_close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_close.setIcon(close_image1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_close.setIcon(close_image);
            }
        });
        //List的美化
        list.setLayout(null);
        list.setFixedCellWidth(309);
        list.setFixedCellHeight(40);    //间距
        list.setFont(article_font);     //字体
        list.setOpaque(false);
        list.setSelectionBackground(new Color(196, 194, 213, 78));     //设置选中条目的颜色
        //设置未选中条目的颜色以及边框
        list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setBackground(Color.ORANGE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                    }
                });
                Border line_border = BorderFactory.createLineBorder(new Color(153, 153, 153), 1, false);
//                Border raise_border = BorderFactory.createRaisedBevelBorder();
                Border title_border = BorderFactory.createTitledBorder(null, Time.getTime(), TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.ITALIC, 12), new Color(0, 0, 0));
                Border b1 = BorderFactory.createCompoundBorder(line_border, title_border);
//                setBorder(BorderFactory.createCompoundBorder();
                setBorder(b1);
                setHorizontalAlignment(SwingConstants.CENTER);

                return c;

            }

        });

        Action_Listner listener = new Action_Listner(PtrlS, list, tmp, map, jf);
        //为list添加监听，事件响应
        list.addMouseListener(listener);
        //设置列表的显示方式：用一列显示
        list.setLayoutOrientation(JList.VERTICAL);

        //设置列表每次只能有一个选项被选中
        list.setSelectionMode(0);

        //打开之后先读取文件中已有的项并添加到list中
        String filename = "..\\note.txt";
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
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("..\\note.txt"), "UTF-8"));
        String line = null;
        while ((line = in.readLine()) != null) {
            String[] itemArray = line.split("€");
            this.map.put(itemArray[0], itemArray[1]);
            tmp.addElement(itemArray[0]);
        }
        list.setModel(tmp);

//        设置新建按钮的属性
        newNote = new JButton();
        newNote.setIcon(new_image);
        newNote.setBounds(18, 18, 20, 20);
        newNote.setBorderPainted(false);
        newNote.setContentAreaFilled(false);
        newNote.addActionListener(listener);
        newNote.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newNote.setIcon(new_image1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newNote.setIcon(new_image);
            }
        });

//        设置删除按钮的属性
        delNote = new JButton();
        delNote.setIcon(del_image);
        delNote.setBounds(107, 18, 20, 20);
        delNote.setBorderPainted(false);
        delNote.setContentAreaFilled(false);
        delNote.addActionListener(listener);
        delNote.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delNote.setIcon(del_image1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delNote.setIcon(del_image);
            }
        });

        JScrollPane jsp = new JScrollPane(list);

        //设置列表所占面积的位置和大小
        jsp.setBounds(7, 50, 310, 480);
        jsp.setBorder(null);
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);

        this.add(button_close);
        this.add(button_mini);

        //将 “新建” 和 “删除” 添加到画板上
        this.add(newNote);
        this.add(delNote);

        this.add(button_close);
        this.add(button_mini);
        this.add(jsp);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
    }
}


/*
18, 107, 199, 290
 */