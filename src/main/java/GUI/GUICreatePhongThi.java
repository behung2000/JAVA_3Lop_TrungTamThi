package GUI;

import BLL.BLLKhoathi;
import BLL.BLLPhongthi;
import ENTITY.Khoathi;
import ENTITY.Phongthi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

public class GUICreatePhongThi extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBoxKhoathi;
    private JComboBox comboBoxSL;
    private JButton button;
    private JTable table1;
    private JComboBox comboBoxTD;
    private JLabel NgayThiJLabel;

    public GUICreatePhongThi(){
        setContentPane(JPanel1);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        BLLKhoathi bllKhoathi = new BLLKhoathi();
        JFrameModel jFrameModel = new JFrameModel();
        comboBoxKhoathi.setModel(jFrameModel.getKhoathiCombobox(bllKhoathi.getALLListOrderID()));

        comboBoxTD.addItem("A2");
        comboBoxTD.addItem("B1");

        for(int i=1; i<=30; i++){
            comboBoxSL.addItem(i);
        }

        comboBoxKhoathi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(comboBoxKhoathi.getModel().getSelectedItem().toString());
                //System.out.println(comboBoxTD.getSelectedItem().toString());
                loadcomboboxkhoathi();
            }
        });

        comboBoxTD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadcomboboxkhoathi();
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String khoathi = comboBoxKhoathi.getModel().getSelectedItem().toString();
                StringTokenizer stringTokenizer = new StringTokenizer(khoathi,"-");
                String sidkhoathi = stringTokenizer.nextToken();
                String tenkhoathi = stringTokenizer.nextToken();
                int idkhoathi = Integer.parseInt(sidkhoathi);
                String trinhdo = comboBoxTD.getSelectedItem().toString();
                int sl = comboBoxSL.getSelectedItem().hashCode();

                //System.out.println(idkhoathi+"--"+trinhdo+"--"+sl);

                BLLPhongthi bllphongthi = new BLLPhongthi();
                int bool = bllphongthi.insertNPhongthi(idkhoathi, sl, trinhdo);
                JFrameMess jFrameMess = new JFrameMess();
                if(bool == 0){
                    jFrameMess.mess("Tạo phòng thi: ","Thành công");
                }
                else{
                    if(bool>0) {
                        jFrameMess.mess("Tạo phòng thi: ", "Không thành công -> " + bool + " phòng thi");
                    }
                    else{
                        if(bool==-1){
                            jFrameMess.mess("Tạo phòng thi: ", "Không thành công -> Khóa thi đã qua");
                        }
                    }
                }

                loadcomboboxkhoathi();
            }
        });

        loadcomboboxkhoathi();

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int idphongthi = table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode();
                //System.out.println("idphongthi = "+idphongthi);
                BLLPhongthi bllPhongthi = new BLLPhongthi();
                Phongthi phongthi = bllPhongthi.get1Phongthi(idphongthi);
                //System.out.println("idphongthi = "+phongthi.getIdphongthi()+"--"+phongthi.getTenphongthi()+"--"+phongthi.getTrinhdo());
                GUIPhongThiThiSinh guiPhongThiThiSinh = new GUIPhongThiThiSinh(phongthi);
            }
        });
    }

    public void loadcomboboxkhoathi(){
        String skhoathi = comboBoxKhoathi.getModel().getSelectedItem().toString();
        StringTokenizer stringTokenizer = new StringTokenizer(skhoathi,"-");
        String sidkhoathi = stringTokenizer.nextToken();
        String tenkhoathi = stringTokenizer.nextToken();

        int idkhoathi = Integer.parseInt(sidkhoathi);
        String trinhdo = comboBoxTD.getSelectedItem().toString();

        BLLKhoathi bllKhoathi = new BLLKhoathi();
        Khoathi khoathi = bllKhoathi.get1KhoaThi(idkhoathi);
        NgayThiJLabel.setText(khoathi.getNgaythi());

        loadtable(idkhoathi, trinhdo);
    }

    public void loadtable(int id, String trinhdo){
        BLLPhongthi bllphongthi = new BLLPhongthi();
        JFrameModel jFrameModel = new JFrameModel();
        table1.setModel(jFrameModel.getPhongThiTableModel(bllphongthi.getALLListOrderIDWithKhoaidAndTrinhdo(id, trinhdo)));
    }

}
