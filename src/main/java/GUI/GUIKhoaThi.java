package GUI;

import BLL.BLLKhoathi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class GUIKhoaThi extends JFrame{
    private JPanel JPanel1;
    private JLabel JLabelidkhoathi;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton addKhoathi;
    private JComboBox comboBoxDay;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxYear;

    public GUIKhoaThi(){
        setContentPane(JPanel1);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        //Set số khóa
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy");
        String syear = sdf.format(date);
        //System.out.println(year);
        int year = Integer.parseInt(syear);
        for(int i=0; i<10; i++){
            comboBox1.addItem(year);
            year--;
        }

        //Set year -> month -> day
        year++;
        for(int i=0; i<12; i++){
            comboBoxYear.addItem(year);
            year++;
        }
        for(int i=1; i<=12; i++){
            comboBoxMonth.addItem(i);
        }
        for(int i=1; i<=31; i++){
            comboBoxDay.addItem(i);
        }
        comboBoxYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeDDMMYYYY();
            }
        });
        comboBoxMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeDDMMYYYY();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JLabelidkhoathi.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                String tenkhoathi = table1.getModel().getValueAt(table1.getSelectedRow(),1).toString();
                StringTokenizer stringTokenizer = new StringTokenizer(tenkhoathi," ");
                String khoa = stringTokenizer.nextToken();
                String Ssokhoa = stringTokenizer.nextToken();
                String thang = stringTokenizer.nextToken();
                String Ssothang = stringTokenizer.nextToken();
                int sokhoa = Integer.parseInt(Ssokhoa);
                comboBox1.setSelectedItem(sokhoa);
                String ngaythi = table1.getModel().getValueAt(table1.getSelectedRow(),2).toString();
                stringTokenizer = new StringTokenizer(ngaythi,"/");
                String ngay = stringTokenizer.nextToken();
                String thag = stringTokenizer.nextToken();
                String nam = stringTokenizer.nextToken();
                int dd = Integer.parseInt(ngay);
                int mm = Integer.parseInt(thag);
                int yy = Integer.parseInt(nam);
                comboBoxYear.setSelectedItem(yy);
                comboBoxMonth.setSelectedItem(mm);
                comboBoxDay.setSelectedItem(dd);
            }
        });

        loadtable();

        addKhoathi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BLLKhoathi bllKhoathi = new BLLKhoathi();
                int sokhoa = comboBox1.getSelectedItem().hashCode();
                int dd = comboBoxDay.getSelectedItem().hashCode();
                int mm = comboBoxMonth.getSelectedItem().hashCode();
                int yy = comboBoxYear.getSelectedItem().hashCode();
                int bool = bllKhoathi.addkhoathi(sokhoa, dd, mm, yy);
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==1) jFrameMess.mess("Thêm khóa thi","Thành công");
                else{
                    if(bool==0) jFrameMess.mess("Thêm khóa thi","Không thành công");
                    else jFrameMess.mess("Thêm khóa thi","Số khóa này trong tháng và năm này đã có ngày thi");
                }
                loadtable();
            }
        });

    }

    public void SafeDDMMYYYY(){
        int remove=0;
        int year = comboBoxYear.getSelectedItem().hashCode();
        int month = comboBoxMonth.getSelectedItem().hashCode();
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
            comboBoxDay.removeItem(i);
        }
    }

    public void loadtable(){
        BLLKhoathi BLLKhoathi = new BLLKhoathi();
        JFrameModel jFrameModel = new JFrameModel();
        table1.setModel(jFrameModel.getKhoathiTableModel(BLLKhoathi.getALLListOrderID()));
    }
}
