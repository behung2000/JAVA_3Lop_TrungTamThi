package GUI;

import BLL.BLLKhoathi;
import BLL.BLLCathi;
import BLL.CheckInput;
import BLL.BLLSobaodanh;
import ENTITY.Cathi;
import ENTITY.Khoathi;
import ENTITY.Phongthi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIPhongThiThiSinh extends JFrame{
    private JPanel JPanel1;
    private JLabel idKhoathiJLabel;
    private JLabel NgaythiJLabel;
    private JLabel TenKhoaThiJLabel;
    private JLabel IDPhongThiJLabel;
    private JLabel TenPhongThiJLabel;
    private JLabel TDJLabel;
    private JLabel IDCathiJLabel;
    private JLabel GThiJLabel;
    private JTable table1;
    private JButton updatesbd;


    public GUIPhongThiThiSinh(Phongthi phongthi){
        setContentPane(JPanel1);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        idKhoathiJLabel.setText(phongthi.getKhoathi()+"");
        BLLKhoathi bllKhoathi = new BLLKhoathi();
        Khoathi khoathi = bllKhoathi.get1KhoaThi(phongthi.getKhoathi());
        if(khoathi!=null){
            TenKhoaThiJLabel.setText(khoathi.getTenkhoathi());
            NgaythiJLabel.setText(khoathi.getNgaythi());
        }
        else{
            TenKhoaThiJLabel.setText("Không tìm thấy TT khóa thi");
            NgaythiJLabel.setText("Không tìm thấy TT khóa thi");
        }
        IDPhongThiJLabel.setText(phongthi.getIdphongthi()+"");
        TenPhongThiJLabel.setText(phongthi.getTenphongthi());
        TDJLabel.setText(phongthi.getTrinhdo());
        IDCathiJLabel.setText(phongthi.getIdcathi()+"");
        BLLCathi bllCathi = new BLLCathi();
        Cathi cathi = bllCathi.get1cathiWithID(phongthi.getIdcathi());
        GThiJLabel.setText(cathi.getGiothi());

        loadtable(phongthi.getKhoathi(), phongthi.getIdphongthi());

        /*
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("GUIPhongThiThiSinh -> GUIPhongThiThiSinh -> hello table click");
            }
        });
         */

        updatesbd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dem=0;
                JFrameMess jFrameMess = new JFrameMess();
                for(int i=0; i<table1.getModel().getRowCount(); i++) {
                    String sbd = table1.getModel().getValueAt(i,0).toString();
                    String cmnd = table1.getModel().getValueAt(i,1).toString();
                    String snghe = table1.getModel().getValueAt(i,2).toString();
                    String snoi = table1.getModel().getValueAt(i,3).toString();
                    String sdoc = table1.getModel().getValueAt(i,4).toString();
                    String sviet = table1.getModel().getValueAt(i,5).toString();
                    //System.out.println(sbd+"--"+snghe+"--"+snoi+"--"+sdoc+"--"+sviet);
                    CheckInput checkInput = new CheckInput();
                    if(checkInput.CheckDiem(snghe) && checkInput.CheckDiem(snoi) && checkInput.CheckDiem(sdoc) && checkInput.CheckDiem(sviet)){
                        int nghe = Integer.parseInt(snghe);
                        int noi = Integer.parseInt(snoi);
                        int doc = Integer.parseInt(sdoc);
                        int viet = Integer.parseInt(sviet);
                        //System.out.println(sbd+"--"+nghe+"--"+noi+"--"+doc+"--"+viet);
                        BLLSobaodanh bllSobaodanh = new BLLSobaodanh();
                        boolean bool = bllSobaodanh.updateDiem1Thisinh(cmnd, phongthi.getKhoathi(), phongthi.getIdphongthi(), sbd, nghe, noi, doc, viet);
                        if(!bool) jFrameMess.mess("Cập nhật điểm","Không thành công -> có điểm nhập sai ở -> sbd ="+sbd);
                        else dem++;
                    }
                    else{
                        jFrameMess.mess("Cập nhật điểm","Không thành công -> có điểm nhập sai ở -> sbd ="+sbd);
                    }
                }
                if(dem==table1.getModel().getRowCount()) jFrameMess.mess("Cập nhật điểm","Thành công");
                loadtable(phongthi.getKhoathi(), phongthi.getIdphongthi());
            }
        });

    }

    public void loadtable(int idkhoathi, int idphongthi){
        BLLSobaodanh bllSobaodanh = new BLLSobaodanh();
        JFrameModel jFrameModel = new JFrameModel();
        table1.setModel(jFrameModel.nhapdiem(bllSobaodanh.getListSBDWithidkhoathiandidphongthi(idkhoathi, idphongthi, "sbd")));
    }

}
