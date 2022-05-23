package GUI;

import BLL.BLLKhoathi;
import BLL.BLLSobaodanh;
import ENTITY.Khoathi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class GUIbktbt extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton button;
    private JLabel NgayThiJLabel;

    public GUIbktbt(String cmnd){
        setContentPane(JPanel1);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        BLLKhoathi bllKhoathi = new BLLKhoathi();
        JFrameModel jFrameModel = new JFrameModel();

        comboBox1.setModel(jFrameModel.getKhoathiCombobox(bllKhoathi.getALLListOrderID()));
        loadcomboboxkhoathi();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadcomboboxkhoathi();
            }
        });

        comboBox2.addItem("A2");
        comboBox2.addItem("B1");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BLLSobaodanh bllSobaodanh = new BLLSobaodanh();
                String skhoathi = comboBox1.getModel().getSelectedItem().toString();
                StringTokenizer stringTokenizer = new StringTokenizer(skhoathi, "-");
                String sidkhoathi = stringTokenizer.nextToken();
                String tenkhoathi = stringTokenizer.nextToken();
                int idkhoathi = Integer.parseInt(sidkhoathi);
                String trinhdo=comboBox2.getSelectedItem().toString();
                int bool = bllSobaodanh.DangKyThi(cmnd, idkhoathi, trinhdo);
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==0){
                    jFrameMess.mess("Đăng ký thi "+trinhdo+" "+tenkhoathi+": ","Thành công");
                    setVisible(false);
                }
                else{
                    if(bool==1) jFrameMess.mess("Đăng ký thi "+trinhdo+" "+tenkhoathi+": ","Không thành công -> có thể thí sinh đã đăng ký khóa thi với trình độ này rồi !!!");
                    else{
                        if(bool==-1) jFrameMess.mess("Đăng ký thi "+trinhdo+" "+tenkhoathi+": ","Đã qua ngày đăng ký thi khóa này");
                        if(bool==-2) jFrameMess.mess("Đăng ký thi "+trinhdo+" "+tenkhoathi+": ","Phòng thi khóa này chưa được tạo");
                        if(bool==-3) jFrameMess.mess("Đăng ký thi "+trinhdo+" "+tenkhoathi+": ","Số lượng thí sinh đăng ký đã đầy");
                    }
                }
            }
        });
    }

    public void loadcomboboxkhoathi(){
        String skhoathi = comboBox1.getModel().getSelectedItem().toString();
        StringTokenizer stringTokenizer = new StringTokenizer(skhoathi,"-");
        String sidkhoathi = stringTokenizer.nextToken();
        String tenkhoathi = stringTokenizer.nextToken();

        int idkhoathi = Integer.parseInt(sidkhoathi);

        BLLKhoathi bllKhoathi = new BLLKhoathi();
        Khoathi khoathi = bllKhoathi.get1KhoaThi(idkhoathi);
        NgayThiJLabel.setText(khoathi.getNgaythi());

    }
}
