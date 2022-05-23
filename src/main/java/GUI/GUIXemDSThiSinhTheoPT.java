package GUI;

import BLL.BLLKhoathi;
import BLL.BLLPhongthi;
import BLL.BLLSobaodanh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class GUIXemDSThiSinhTheoPT extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable table1;

    public GUIXemDSThiSinhTheoPT(){
        setContentPane(JPanel1);
        setSize(1000,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        BLLKhoathi bllKhoathi = new BLLKhoathi();
        JFrameModel jFrameModel = new JFrameModel();
        comboBox1.setModel(jFrameModel.getKhoathiCombobox(bllKhoathi.getALLListOrderID()));

        changeCombobox1SagCombobox2();

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCombobox1SagCombobox2();
                loadtable();
            }
        });

        loadtable();
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadtable();
            }
        });

    }

    public void changeCombobox1SagCombobox2(){
        String skhoathi = comboBox1.getModel().getSelectedItem().toString();
        StringTokenizer stringTokenizer = new StringTokenizer(skhoathi,"-");
        String sidkhoathi = stringTokenizer.nextToken();
        String tenkhoathi = stringTokenizer.nextToken();

        int idkhoathi = Integer.parseInt(sidkhoathi);
        BLLPhongthi bllPhongthi = new BLLPhongthi();
        JFrameModel jFrameModel = new JFrameModel();
        comboBox2.setModel(jFrameModel.getcomboboxPhongthi(bllPhongthi.getListWithidkhoathi(idkhoathi)));
    }

    public void loadtable(){
        String skhoathi = comboBox1.getModel().getSelectedItem().toString();
        StringTokenizer stringTokenizer = new StringTokenizer(skhoathi,"-");
        String sidkhoathi = stringTokenizer.nextToken();
        String tenkhoathi = stringTokenizer.nextToken();

        int idkhoathi = Integer.parseInt(sidkhoathi);

        if(comboBox2.getModel().getSelectedItem() !=null ) {
            String sphongthi = comboBox2.getModel().getSelectedItem().toString();
            stringTokenizer = new StringTokenizer(sphongthi, "-");
            String sidphongthi = stringTokenizer.nextToken();
            String tenphongthi = stringTokenizer.nextToken();
            String trinhdo = stringTokenizer.nextToken();

            int idphongthi = Integer.parseInt(sidphongthi);

            System.out.println(idphongthi + "--" + idkhoathi);
            BLLSobaodanh bllSobaodanh = new BLLSobaodanh();
            JFrameModel jFrameModel = new JFrameModel();
            table1.setModel(jFrameModel.getTTThiSinh(bllSobaodanh.getListSBDWithidkhoathiandidphongthi(idkhoathi, idphongthi, "sbd")));
        }
    }
}
