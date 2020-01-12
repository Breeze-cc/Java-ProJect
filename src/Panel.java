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
    static JList list;
    static DefaultListModel tmp = new DefaultListModel();
    private Map<String, String> map = new HashMap<String, String>();
    private StackList PtrlS = new StackList();
    private ImageIcon image = new ImageIcon("..\\img\\background.png");
    private ImageIcon close_image = new ImageIcon("..\\img\\close.png");
    private ImageIcon close_image1 = new ImageIcon("..\\img\\close1.png");
    private ImageIcon mini_image = new ImageIcon("..\\img\\mini.png");
    private ImageIcon mini_image1 = new ImageIcon("..\\img\\mini1.png");
    private ImageIcon new_image = new ImageIcon("..\\img\\new.png");
    private ImageIcon new_image1 = new ImageIcon("..\\img\\new1.png");
    private ImageIcon del_image = new ImageIcon("..\\img\\delete.png");
    private ImageIcon del_image1 = new ImageIcon("..\\img\\delete1.png");
    private Font article_font = new Font("����������W5", Font.PLAIN, 16);
    private JScrollPane jsp;
    private MainMenu jf;

    public Panel(MainMenu jf) throws IOException, FileNotFoundException {
        this.jf = jf;
        //����PanelΪ���Բ���
        this.setLayout(null);
        //�½��µ��б�
        String[] test = new String[]{};
        list = new JList();
        //�����µļ�����
        Action_Listner listener = new Action_Listner(PtrlS, list, tmp, map, jf);

        //������С����ť
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

        //���ùرհ�ť
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


//        �����½���ť������
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

//        ����ɾ����ť������
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

        //List������
        list.setLayout(null);
        list.setFixedCellWidth(309);
        list.setFixedCellHeight(40);    //����
        list.setFont(article_font);     //����
        list.setOpaque(false);
        list.setSelectionBackground(new Color(49, 213, 90, 78));     //����ѡ����Ŀ����ɫ
        //����δѡ����Ŀ����ɫ�Լ��߿�
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                for (int i = 0; i < list.getModel().getSize(); i++){
                    setOpaque(false);
                }
                Border border1 = BorderFactory.createEtchedBorder();
                Border line_border = BorderFactory.createLineBorder(new Color(241,240,240), 5, false);
                Border b1 = BorderFactory.createCompoundBorder(line_border, border1);
                setBorder(b1);
                return c;
            }
        });

        //Ϊlist��Ӽ������¼���Ӧ
        list.addMouseListener(listener);
        //�����б����ʾ��ʽ����һ����ʾ
        list.setLayoutOrientation(JList.VERTICAL);
        //�����б�ÿ��ֻ����һ��ѡ�ѡ��
        list.setSelectionMode(0);

        //��֮���ȶ�ȡ�ļ������е����ӵ�list��
        String filename = "..\\note.txt";
        File inMyPC = new File(filename);
        //��������ڸ��ļ������½�һ��
        if (!inMyPC.exists()) {
            try {
                inMyPC.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //���ļ��ж�ȡtxt�ļ�
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("..\\note.txt"), "UTF-8"));
        String line = null;
        while ((line = in.readLine()) != null) {
            try {
                String[] itemArray = line.split("��");
                if (itemArray != null)
                    this.map.put(itemArray[0], itemArray[1]);
                tmp.addElement(itemArray[0]);
            }
            catch (ArrayIndexOutOfBoundsException e){
                String item[] = line.split("��");
                this.map.put(item[0], "");
                tmp.addElement(item[0]);
            }
        }
        list.setModel(tmp);
        //���ı�����ڹ�������
        JScrollPane jsp = new JScrollPane(list);

        //�����б���ռ�����λ�úʹ�С
        jsp.setBounds(7, 50, 310, 480);
        jsp.setBorder(null);
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);


        //�� ���а�ť�ӵ���������
        this.add(newNote);
        this.add(delNote);
        this.add(button_close);
        this.add(button_mini);
        this.add(jsp);
    }

    //���ʻ�����
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
    }
}


/*
18, 107, 199, 290
 */