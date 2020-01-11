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
    private JButton newNote, delNote, button_close;
    private JList list;
    //    private Action_Listner listner;
    private DefaultListModel tmp = new DefaultListModel();
    Map<String, String> map;
    StackList PtrlS = new StackList();
    ImageIcon image = new ImageIcon("..\\background.png");
    Font menu_font = new Font("汉仪铸字木头人W", Font.PLAIN, 1);
    Font article_font = new Font("汉仪铸字木头人W", Font.PLAIN, 16);
    ImageIcon close_image = new ImageIcon("..\\关闭.png");
    ImageIcon close_image1 = new ImageIcon("..\\关闭1.png");
    ImageIcon mini_image = new ImageIcon("..\\缩小.png");
    ImageIcon mini_image1 = new ImageIcon("..\\缩小1.png");
    ImageIcon new_image = new ImageIcon("..\\新建.png");
    ImageIcon new_image1 = new ImageIcon("..\\新建1.png");
    ImageIcon del_image = new ImageIcon("..\\删除.png");
    ImageIcon del_image1 = new ImageIcon("..\\删除1.png");
    JScrollPane jsp;
    Point point;

    public Panel() throws IOException, FileNotFoundException {
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
                Main.window.dispose();
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
//                setback(image);
//                for (int i = 0; i < list.getModel().getSize(); i++) {
//                    if (!list.isSelectedIndex(i)) {
//                        setBackground(new Color(250, 244, 165));
//                    }
//                }
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

        list.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                list.setSelectedIndices(new int[]{});
            }
        });
        Action_Listner listener = new Action_Listner(list, tmp, map);
        //为list添加监听，事件响应
        list.addMouseListener(listener);
        //设置列表的显示方式：用一列显示
        list.setLayoutOrientation(JList.VERTICAL);

        //设置列表每次只能有一个选项被选中
        list.setSelectionMode(0);

        //打开之后先读取文件中已有的项并添加到list中
        String filename = "..\\note.csv";
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
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("..\\note.csv"), "UTF-8"));
        String line = null;
        while ((line = in.readLine()) != null) {
//            HashMap<String, String> item = new HashMap<String, String>();
            String[] itemArray = line.split(",");
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
        newNote.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newNote.setIcon(new_image1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newNote.setIcon(new_image);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                //弹出新的窗口输入新备忘录的标题
                String str = JOptionPane.showInputDialog(list, "新建备忘录", JOptionPane.PLAIN_MESSAGE);

                if (str != null) {
                    //添加列表项目
                    tmp.addElement(str);

                    //将tmp中的内容同步到list
                    list.setModel(tmp);
                }
            }
        });

//        设置删除按钮的属性
        delNote = new JButton();
        delNote.setIcon(del_image);
        delNote.setBounds(107, 18, 20, 20);
        delNote.setBorderPainted(false);
        delNote.setContentAreaFilled(false);
        delNote.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delNote.setIcon(del_image1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delNote.setIcon(del_image);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (tmp.getSize() > 0) {

                    //如果有内容被选中
                    if (list.getSelectedIndex() != -1) {


                        Panel.this.map.remove(list.getSelectedValue());

                        String filename = "..\\note.csv";
                        File inMyPC = new File(filename);
                        try {
                            //文件追加，使写入的不会覆盖原本的内容
                            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                            for (String key : Panel.this.map.keySet()) {
                                String value = Panel.this.map.get(key);
                                out.write(key);
                                out.write(",");
                                out.write(value);
                                out.newLine();
                                out.flush();
                            }
                            out.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        //将选中内容移除
                        tmp.removeElementAt(list.getLeadSelectionIndex());

                        //将tmp中的内容同步至list
                        list.setModel(tmp);
                        JOptionPane.showMessageDialog(list, "删除成功！");
                    }

                    //如果没有内容被选中，则弹出提示窗口
                    else {
                        JOptionPane.showMessageDialog(list, "请选择要删除的项");
                    }
                }

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
//        this.add(list);
//        this.add(jb);
        this.add(jsp);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
//        image.paintIcon(jsp, g, 0, 0);
//        image.paintIcon(list, g, 0, 0);
//        Image image2 = new ImageIcon("..\\backgrou")
//        g.drawImage(image, 0, 0, jsp.getWidth(), jsp.getHeight(), jsp);
    }
}


/*
18, 107, 199, 290
 */