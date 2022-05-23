package GUI;

import BLL.BLLCathi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

public class GUICathi extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBoxGio;
    private JComboBox comboBoxPhut;
    private JTable table1;
    private JButton addcathi;
    private JLabel JLabelidcathi;

    public GUICathi(){
        setContentPane(JPanel1);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        for(int i=1; i<=24; i++){
            comboBoxGio.addItem(i);
        }

        for(int i=0; i<=60; i++){
            comboBoxPhut.addItem(i);
        }

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JLabelidcathi.setText(table1.getModel().getValueAt(table1.getSelectedRow(),0).toString());
                String giothi = table1.getModel().getValueAt(table1.getSelectedRow(),1).toString();
                StringTokenizer stringTokenizer = new StringTokenizer(giothi,":");
                String sgio = stringTokenizer.nextToken();
                String sphut = stringTokenizer.nextToken();
                String ssec = stringTokenizer.nextToken();
                int gio = Integer.parseInt(sgio);
                int phut = Integer.parseInt(sphut);
                comboBoxGio.setSelectedItem(gio);
                comboBoxPhut.setSelectedItem(phut);
            }
        });

        loadtable();

        addcathi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gio = comboBoxGio.getSelectedItem().hashCode();
                int phut = comboBoxPhut.getSelectedItem().hashCode();
                BLLCathi bllCathi = new BLLCathi();
                int bool = bllCathi.insertcathi(gio, phut);
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==1) jFrameMess.mess("Thêm ca thi","Thành công");
                else{
                    if(bool==0) jFrameMess.mess("Thêm ca thi","Không thành công");
                    else jFrameMess.mess("Thêm ca thi","Giờ thi này đã có");
                }
                loadtable();
            }
        });

    }

    public void loadtable(){
        BLLCathi bllcathi = new BLLCathi();
        JFrameModel jFrameModel = new JFrameModel();
        table1.setModel(jFrameModel.getCathiTableModel(bllcathi.getALLListOrderID()));
    }

}
