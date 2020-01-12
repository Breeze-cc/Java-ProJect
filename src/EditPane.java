import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Map;

public class EditPane extends JPanel implements ActionListener {
    static JTextArea showMessage;
    JButton add, delete, allDelete, save, back, finish;
    StackList PtrlS;
    JList list;
    String key;
    Map<String, String> map;

    ImageIcon image_add = new ImageIcon("..\\img\\add.png");
    ImageIcon image_add1 = new ImageIcon("..\\img\\add1.png");
    ImageIcon image_bk = new ImageIcon("..\\img\\backspace.png");
    ImageIcon image_bk1 = new ImageIcon("..\\img\\backspace1.png");
    ImageIcon image_alldel = new ImageIcon("..\\img\\clear.png");
    ImageIcon image_alldel1 = new ImageIcon("..\\img\\clear1.png");
    ImageIcon image_save = new ImageIcon("..\\img\\save.png");
    ImageIcon image_save1 = new ImageIcon("..\\img\\save1.png");
    ImageIcon image_back = new ImageIcon("..\\img\\back.png");
    ImageIcon image_back1 = new ImageIcon("..\\img\\back1.png");
    ImageIcon image = new ImageIcon("..\\img\\background.png");
    ImageIcon image_finish = new ImageIcon("..\\img\\finish.png");
    ImageIcon image_finish1 = new ImageIcon("..\\img\\finish1.png");
    Font font1 = new Font("����������W5(P)", Font.PLAIN, 20);

    public EditPane(StackList PtrlS, JList list, String key, Map<String, String> map) {

        this.key = key;
        this.list = list;
        this.map = map;
        this.PtrlS = PtrlS;

        if (map.get(key) != null) {
            for (int i = 0; i < map.get(key).length();i++) {
                if (map.get(key).charAt(i) == '\u0000'){
                    break;
                }
                PtrlS.Push(map.get(key).charAt(i));
            }
        }

        //����������ť�����أ���ɣ���ӣ�ɾ�������棬���
        showMessage = new JTextArea();
        add = new JButton();
        delete = new JButton();
        allDelete = new JButton();
        save = new JButton();
        back = new JButton();
        finish = new JButton();

        this.setLayout(null);
        //�����ı���
        showMessage.setFont(font1);
        showMessage.setBounds(15, 50, 280, 200);
        showMessage.setOpaque(false);
        showMessage.setLineWrap(true);
        showMessage.setEditable(false);
        showMessage.setText(PtrlS.getDatas());
        this.add(showMessage);

        //���� ��� ��ť
        add.setIcon(image_add);
        add.setBounds(18, 460, 20, 20);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.addActionListener(this);
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                add.setIcon(image_add1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add.setIcon(image_add);
            }
        });
        this.add(add);

        //��� �˸� ��ť
        delete.setIcon(image_bk);
        delete.setBounds(299, 460, 20, 20);
        delete.addActionListener(this);
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delete.setIcon(image_bk1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setIcon(image_bk);
            }
        });
        this.add(delete);

        //���� ��� ��ť
        allDelete.setIcon(image_alldel);
        allDelete.setBounds(199, 460, 20, 20);
        allDelete.addActionListener(this);
        allDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                allDelete.setIcon(image_alldel1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                allDelete.setIcon(image_alldel);
            }
        });
        allDelete.setBorderPainted(false);
        allDelete.setContentAreaFilled(false);
        this.add(allDelete);

        //���� ���� ��ť
        save.setIcon(image_save);
        save.setBounds(107, 460, 20, 20);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        save.addActionListener(this);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setIcon(image_save1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                save.setIcon(image_save);
            }
        });
        this.add(save);

        //��� ���� ��ť
        back.setIcon(image_back);
        back.setBounds(10, 10, 90, 20);
        back.addActionListener(this);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(image_back1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(image_back);
            }
        });
        this.add(back);

        //���� ��� ��ť
        finish.setIcon(image_finish);
        finish.setBounds(270, 13, 50, 20);
        finish.setContentAreaFilled(false);
        finish.setBorderPainted(false);
        finish.addActionListener(this);
        finish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                finish.setIcon(image_finish1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                finish.setIcon(image_finish);
            }
        });
        this.add(finish);
        this.setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //��� ��ť�Ĵ�����
        if (e.getSource() == add) {
            new Dialog("      ��������ӵ�����", PtrlS, 1);
//            String str = DialogPanel.textStr;
        }

        //�˸� ��ť�Ĵ�����
        if (e.getSource() == delete) {
            PtrlS.Pop();
            showMessage.setText(PtrlS.getDatas());
        }

        //��� ��ť�Ĵ�����
        if (e.getSource() == allDelete) {
            PtrlS = PtrlS.Clean();
            showMessage.setText(PtrlS.getDatas());
        }

        //���� ��ť�Ĵ�����
        if (e.getSource() == save) {
            String filename = "..\\note.txt";
            File inMyPC = new File(filename);

            //��ȡ����¼����
            String title = (String) list.getSelectedValue();

            //д��map��
            map.put(title, PtrlS.getDatas());
            //System.out.println(map);

            //д���ļ���
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    out.write(key.trim());
                    out.write("��");
                    out.write(value.trim());
                    out.newLine();
                    out.flush();
                }
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "����ɹ���");
        }
        if (e.getSource() == back){

            SwingUtilities.getWindowAncestor(Panel.delNote).setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        }
        if (e.getSource() == finish){
            String filename = "..\\note.txt";
            File inMyPC = new File(filename);

            //��ȡ����¼����
            String title = (String) list.getSelectedValue();

            //д��map��
            map.put(title, PtrlS.getDatas());
            //System.out.println(map);

            //д���ļ���
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    out.write(key.trim());
                    out.write("��");
                    out.write(value.trim());
                    out.newLine();
                    out.flush();
                }
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            SwingUtilities.getWindowAncestor(this).dispose();
            SwingUtilities.getWindowAncestor(Panel.delNote).setVisible(true);
        }
    }
}
