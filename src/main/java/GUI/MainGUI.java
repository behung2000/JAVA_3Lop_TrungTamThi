package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    private JPanel JPanel1;
    private JButton btguikhoathi;
    private JButton btguicathi;
    private JButton btTaoPThi;
    private JButton btguithisinh;
    private JButton btGUItracuu;
    private JButton btxemdsthisinhtheopthi;

    public MainGUI() {
        setContentPane(JPanel1);
        setSize(900,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btguikhoathi.setText("Khóa thi");
        btguikhoathi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIKhoaThi guiKhoaThi = new GUIKhoaThi();
            }
        });

        btguicathi.setText("Ca thi");
        btguicathi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUICathi guiCathi = new GUICathi();
            }
        });

        btTaoPThi.setText("Quản lý phòng thi theo khóa thi (và nhập điểm)");
        btTaoPThi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUICreatePhongThi guiCreatePhongThi = new GUICreatePhongThi();
            }
        });

        btguithisinh.setText("Quản lý thí sinh trong trung tâm");
        btguithisinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIThiSinh guiThiSinh = new GUIThiSinh();
            }
        });

        btGUItracuu.setText("Tra cứu thông tin thí sinh thi");
        btGUItracuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUITraCuuSBD guiTraCuuSbd = new GUITraCuuSBD();
            }
        });

        btxemdsthisinhtheopthi.setText("Xem danh sách thí sinh theo từ khoa thi -> phòng thi -> danh sách thí sinh");
        btxemdsthisinhtheopthi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIXemDSThiSinhTheoPT guiXemDSThiSinhTheoPT = new GUIXemDSThiSinhTheoPT();
            }
        });


    }

    public static void main(String[] args){
        MainGUI mainGUI = new MainGUI();
    }
}
