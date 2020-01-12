import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Map;

public class Action_Listner implements ActionListener, ListSelectionListener, MouseListener {
    //ListAction list;
    private JList list;
    private DefaultListModel tmp;
    private Map<String, String> map;
    private StackList PtrlS;
    private MainMenu jf;
    public Action_Listner(StackList PtrlS, JList list, DefaultListModel tmp, Map<String, String> map, MainMenu jf) {
        this.list = list;
        this.tmp = tmp;
        this.map = map;
        this.PtrlS = PtrlS;
        this.jf = jf;
    }
    JLabel jump;
    @Override
    public void actionPerformed(ActionEvent e) {

        //���������Ӧ�������ϵ��ַ����� ���½���
        if (e.getSource() == Panel.newNote) {
            //�����µĴ��������±���¼�ı���
            String str = JOptionPane.showInputDialog(list, jump, "�½�����¼", JOptionPane.PLAIN_MESSAGE);

            if (str != null) {
                //����б���Ŀ
                tmp.addElement(str);

                //��tmp�е�����ͬ����list
                list.setModel(tmp);
            }
        }

        //���������Ӧ�������ϵ��ַ����� ��ɾ����
        if (e.getSource() == Panel.delNote) {

            //����б����Ŀ��������0
            if (tmp.getSize() > 0) {

                //��������ݱ�ѡ��
                if (list.getSelectedIndex() != -1) {


                    map.remove(list.getSelectedValue());

                    String filename = "..\\img\\note.txt";
                    File inMyPC = new File(filename);
                    try {
                        //�ļ�׷�ӣ�ʹд��Ĳ��Ḳ��ԭ��������
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inMyPC), "UTF-8"));
                        for (String key : map.keySet()) {
                            String value = map.get(key);
                            out.write(key);
                            out.write("��");
                            out.write(value);
                            out.newLine();
                            out.flush();
                        }
                        out.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //��ѡ�������Ƴ�
                    tmp.removeElementAt(list.getLeadSelectionIndex());

                    //��tmp�е�����ͬ����list
                    list.setModel(tmp);
                    JOptionPane.showMessageDialog(list, "ɾ���ɹ���");
                }

                //���û�����ݱ�ѡ�У��򵯳���ʾ����
                else {
                    JOptionPane.showMessageDialog(list, "��ѡ��Ҫɾ������");
                }
            }
        }
    }

    //���ط���,���˫���¼�
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == list) {
            if (list.getSelectedIndex() != -1) {
                if (e.getClickCount() == 2) {
                    String key = (String) list.getSelectedValue();
                        editwindow1 editwindow = new editwindow1(PtrlS, list, key, map, jf);
                        SwingUtilities.getWindowAncestor(Panel.delNote).dispose();
                        PtrlS = PtrlS.Clean();
//                    }
                }
            }
        }
     }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
