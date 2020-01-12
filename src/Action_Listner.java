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

    //��д������Ӧ
    @Override
    public void actionPerformed(ActionEvent e) {

        //�½���ť�Ĵ�����
        if (e.getSource() == Panel.newNote) {
            //�����µĴ��������±���¼�ı���
            //index�������½�����ӻ������Ѵ���
            new Dialog("      �������±���¼����", PtrlS, 2);
        }

        //ɾ����ť�Ĵ�����
        if (e.getSource() == Panel.delNote) {
            //����б����Ŀ��������0
            if (tmp.getSize() > 0) {
                //��������ݱ�ѡ��
                if (list.getSelectedIndex() != -1) {
                    //��map���Ƴ���ѡ�е���Ŀ��
                    map.remove(list.getSelectedValue());
                    //���ļ���
                    String filename = "..\\note.txt";
                    File inMyPC = new File(filename);
                    if (inMyPC.exists()){
                        boolean a = inMyPC.delete();
                        try {
                            inMyPC.createNewFile();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        //ʹд��ĻḲ��ԭ��������
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
                    //��ѡ�����ݴ�List�Ƴ�
                    tmp.removeElementAt(list.getLeadSelectionIndex());

                    //��tmp�е�����ͬ����list
                    list.setModel(tmp);
                    new notice("ɾ���ɹ���");
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
