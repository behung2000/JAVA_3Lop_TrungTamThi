package GUI;

import BLL.BLLThisinh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class GUIThiSinh extends JFrame{
    private JPanel JPanel1;
    private JTextField CMNDtextField;
    private JTextField HotextField;
    private JTextField TentextField;
    private JComboBox NgSinhddcomboBox;
    private JComboBox NgSinhMMcomboBox;
    private JComboBox NgSinhyyyycomboBox;
    private JTextField NoiSinhtextField;
    private JComboBox NgCapddcomboBox;
    private JComboBox NgCapMMcomboBox;
    private JComboBox NgCapyyyycomboBox;
    private JTextField CMNDNoiCaptextField;
    private JTextField SDTtextField;
    private JTextField EmailtextField;
    private JButton addbt;
    private JTable table1;
    private JButton searchbt;
    private JButton dktbt;

    public GUIThiSinh(){
        setContentPane(JPanel1);
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        //set year -> month -> day
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy");
        String syear = sdf.format(date);
        //System.out.println(year);
        int year = Integer.parseInt(syear);
            //year
        for(int i=year-50; i<=year-17; i++){
            NgSinhyyyycomboBox.addItem(i);
        }
        for(int i=year-70; i<=year; i++)
        {
            NgCapyyyycomboBox.addItem(i);
        }
            //month
        for(int i=1; i<=12; i++){
            NgSinhMMcomboBox.addItem(i);
            NgCapMMcomboBox.addItem(i);
        }
            //Day
        for(int i=1; i<=31; i++){
            NgSinhddcomboBox.addItem(i);
            NgCapddcomboBox.addItem(i);
        }

        //Safe year mm dd
            //Ng sinh
        NgSinhyyyycomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeNgSinh();
            }
        });
        NgSinhMMcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeNgSinh();
            }
        });

            //Ng cap
        NgCapyyyycomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeNgCap();
            }
        });
        NgCapMMcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeNgCap();
            }
        });

        //Table
        loadtable("");

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CMNDtextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),0).toString());
                HotextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),1).toString());
                TentextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),2).toString());
                String ngaysinh = table1.getModel().getValueAt(table1.getSelectedRow(),3).toString();
                StringTokenizer stngaysinh = new StringTokenizer(ngaysinh,"/");
                String sddngaysinh = stngaysinh.nextToken();
                String sMMngaysinh = stngaysinh.nextToken();
                String sYYngaysinh = stngaysinh.nextToken();
                int ddngaysinh = Integer.parseInt(sddngaysinh);
                int MMngaysinh = Integer.parseInt(sMMngaysinh);
                int YYngaysinh = Integer.parseInt(sYYngaysinh);
                NgSinhyyyycomboBox.setSelectedItem(YYngaysinh);
                NgSinhMMcomboBox.setSelectedItem(MMngaysinh);
                NgSinhddcomboBox.setSelectedItem(ddngaysinh);
                NoiSinhtextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),4).toString());
                String ngaycap = table1.getModel().getValueAt(table1.getSelectedRow(),5).toString();
                StringTokenizer stngaycap = new StringTokenizer(ngaycap, "/");
                String sddngaycap = stngaycap.nextToken();
                String sMMngaycap = stngaycap.nextToken();
                String sYYngaycap = stngaycap.nextToken();
                int ddngaycap = Integer.parseInt(sddngaycap);
                int MMngaycap = Integer.parseInt(sMMngaycap);
                int YYngaycap = Integer.parseInt(sYYngaycap);
                NgCapyyyycomboBox.setSelectedItem(YYngaycap);
                NgCapMMcomboBox.setSelectedItem(MMngaycap);
                NgCapddcomboBox.setSelectedItem(ddngaycap);
                CMNDNoiCaptextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),6).toString());
                SDTtextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),7).toString());
                EmailtextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),8).toString());
            }
        });

        //Button
        addbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmnd = CMNDtextField.getText();
                String ho = HotextField.getText();
                String ten = TentextField.getText();
                int ddngaysinh = NgSinhddcomboBox.getSelectedItem().hashCode();
                int MMngaysinh = NgSinhMMcomboBox.getSelectedItem().hashCode();
                int YYngaysinh = NgSinhyyyycomboBox.getSelectedItem().hashCode();
                String ngaysinh=ddngaysinh+"/";
                if(ddngaysinh<10) ngaysinh="0"+ngaysinh;
                if(MMngaysinh<10) ngaysinh=ngaysinh+"0";
                ngaysinh=ngaysinh+MMngaysinh+"/"+YYngaysinh;
                String noisinh = NoiSinhtextField.getText();
                int ddngaycap = NgCapddcomboBox.getSelectedItem().hashCode();
                int MMngaycap = NgCapMMcomboBox.getSelectedItem().hashCode();
                int YYngaycap = NgCapyyyycomboBox.getSelectedItem().hashCode();
                String cmnd_ngaycap=ddngaycap+"/";
                if(ddngaycap<10) cmnd_ngaycap="0"+cmnd_ngaycap;
                if(MMngaycap<10) cmnd_ngaycap=cmnd_ngaycap+"0";
                cmnd_ngaycap=cmnd_ngaycap+MMngaycap+"/"+YYngaycap;
                String cmnd_noicap = CMNDNoiCaptextField.getText();
                String sdt = SDTtextField.getText();
                String email = EmailtextField.getText();

                BLLThisinh bllThisinh = new BLLThisinh();
                int bool = bllThisinh.insert1thiSinh(cmnd, ho, ten, ngaysinh, noisinh, cmnd_ngaycap, cmnd_noicap, sdt, email);
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==1) jFrameMess.mess("Thêm thí sinh:","Thành công");
                else{
                    if(bool==0) jFrameMess.mess("Thêm thí sinh:","Không thành công -> có thể đã có thông tin thí sinh này");
                    else{
                        if(bool==-1) jFrameMess.mess("Thêm thí sinh:","CMND không hợp lệ");
                        if(bool==-2) jFrameMess.mess("Thêm thí sinh:","Họ không hợp lệ (chỉ 1 từ)");
                        if(bool==-3) jFrameMess.mess("Thêm thí sinh:","Tên không hợp lệ");
                        if(bool==-4) jFrameMess.mess("Thêm thí sinh:","Nơi sinh không hợp lệ");
                        if(bool==-5) jFrameMess.mess("Thêm thí sinh:","CMND nơi cấp không hợp lệ");
                        if(bool==-6) jFrameMess.mess("Thêm thí sinh:","Số điện thoại không hợp lệ");
                        if(bool==-7) jFrameMess.mess("Thêm thí sinh:","Email không hợp lệ");
                        if(bool==-8) jFrameMess.mess("Thêm thí sinh:","Ngày cấp cmnd so với ngày sinh không hợp lệ");
                    }
                }
                loadtable("");
            }
        });

        searchbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmnd = CMNDtextField.getText();
                cmnd=cmnd.trim();
                if(cmnd==null || cmnd.equalsIgnoreCase("") || cmnd.equalsIgnoreCase(" "))
                {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Search", "Chưa điền cmnd để search");
                }
                else {
                    loadtable(cmnd);
                }
            }
        });

        dktbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                JFrameMess jFrameMess = new JFrameMess();
                if(row>=0)
                {
                    String cmnd = table1.getModel().getValueAt(row,0).toString();
                    GUIbktbt guibktbt = new GUIbktbt(cmnd);
                }
                else{

                    jFrameMess.mess("Đăng ký thi: ", "Chưa chọn thí sinh (ở table) để đăng ký thi !!!");
                }
            }
        });

    }

    public void SafeNgSinh(){
        int remove=0;
        int year = NgSinhyyyycomboBox.getSelectedItem().hashCode();
        int month = NgSinhMMcomboBox.getSelectedItem().hashCode();
        //System.out.println(month+"/"+year);
        if(month==4 || month==6 || month==9 || month==11){
            remove = 1;
        }
        if(month==2){
            if(year%4==0){
                remove = 2;
            }
            else{
                remove = 3;
            }
        }
        for(int i=31; remove>0; remove--,i--){
            NgSinhddcomboBox.removeItem(i);
        }
    }

    public void SafeNgCap(){
        int remove=0;
        int year = NgCapyyyycomboBox.getSelectedItem().hashCode();
        int month = NgCapMMcomboBox.getSelectedItem().hashCode();
        //System.out.println(month+"/"+year);
        if(month==4 || month==6 || month==9 || month==11){
            remove = 1;
        }
        if(month==2){
            if(year%4==0){
                remove = 2;
            }
            else{
                remove = 3;
            }
        }
        for(int i=31; remove>0; remove--,i--){
            NgCapddcomboBox.removeItem(i);
        }
    }

    public void loadtable(String cmnd){
        BLLThisinh bllThisinh = new BLLThisinh();
        JFrameModel jFrameModel = new JFrameModel();
        table1.setModel(jFrameModel.getThisinhTableModel(bllThisinh.getListThiSinhWithCMND(cmnd)));
    }
}
