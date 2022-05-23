package GUI;

import BLL.BLLSobaodanh;
import ENTITY.Sobaodanh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITraCuuSBD extends JFrame{
    private JPanel JPanel1;
    private JTextField TentextField;
    private JTextField SDTtextField;
    private JButton searchButton;
    private JTable table1;

    public GUITraCuuSBD(){
        setContentPane(JPanel1);
        setSize(1500,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        loadtable("","");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = TentextField.getText();
                String sdt = SDTtextField.getText();
                loadtable(name, sdt);
            }
        });

    }
    public void loadtable(String name, String sdt){
        BLLSobaodanh bllSobaodanh = new BLLSobaodanh();
        JFrameModel jFrameModel = new JFrameModel();
        table1.setModel(jFrameModel.tracuusbd(bllSobaodanh.getListSBDWithThiSinh(name, sdt)));
    }
}
