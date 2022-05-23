package GUI;

import BLL.BLLCathi;
import BLL.BLLKhoathi;
import BLL.BLLPhongthi;
import BLL.BLLThisinh;
import ENTITY.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class JFrameModel {
    DefaultTableModel defaultTableModel = null;
    DefaultComboBoxModel defaultComboBoxModel = null;

    public JFrameModel(){
    }

    //Khoa thi
        //Table
    public DefaultTableModel getKhoathiTableModel(ArrayList<Khoathi> list) {
        Vector header = new Vector();
        header.add("id khóa thi");
        header.add("Tên khóa thi");
        header.add("Ngày thi");
        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0)
        {
            for(Khoathi khoathi : list){
                Vector row = new Vector();
                row.add(khoathi.getIdkhoathi());
                row.add(khoathi.getTenkhoathi());
                row.add(khoathi.getNgaythi());
                defaultTableModel.addRow(row);
            }
        }

        return defaultTableModel;
    }
        //Combobox
    public DefaultComboBoxModel getKhoathiCombobox(ArrayList<Khoathi> list){
        defaultComboBoxModel = new DefaultComboBoxModel();
        if(list!=null && list.size()>0)
        {
            for(Khoathi khoathi : list){
                String row = khoathi.getIdkhoathi()+"-"+khoathi.getTenkhoathi();
                defaultComboBoxModel.addElement(row);
            }
        }
        return defaultComboBoxModel;
    }

    //Ca thi
        //table
    public DefaultTableModel getCathiTableModel(ArrayList<Cathi> list) {
        Vector header = new Vector();
        header.add("id ca thi");
        header.add("Giờ thi");
        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0)
        {
            for(Cathi cathi : list){
                Vector row = new Vector();
                row.add(cathi.getIdcathi());
                row.add(cathi.getGiothi());
                defaultTableModel.addRow(row);
            }
        }

        return defaultTableModel;
    }

    //Phong thi
        //table
    public DefaultTableModel getPhongThiTableModel(ArrayList<Phongthi> list){
        Vector header = new Vector();
        header.add("id phòng thi");
        header.add("Tên phòng thi");
        header.add("Giờ thi");
        header.add("Ngày thi");
        header.add("Trình độ");

        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0)
        {
            for(Phongthi phongthi: list){
                Vector row = new Vector();
                row.add(phongthi.getIdphongthi());
                row.add(phongthi.getTenphongthi());

                //Cathi
                BLLCathi bllCathi = new BLLCathi();
                Cathi cathi = bllCathi.get1cathiWithID(phongthi.getIdcathi());
                if(cathi!=null) row.add(cathi.getGiothi());
                else row.add("không tìm thấy tt ca thi");

                //Khoathi
                BLLKhoathi bllKhoathi = new BLLKhoathi();
                Khoathi khoathi = bllKhoathi.get1KhoaThi(phongthi.getKhoathi());
                if(khoathi!=null) row.add(khoathi.getNgaythi());
                else row.add("Không tìm thấy tt khoa thi");

                row.add(phongthi.getTrinhdo());
                defaultTableModel.addRow(row);
            }
        }

        return defaultTableModel;
    }

        //Combobox
    public DefaultComboBoxModel getcomboboxPhongthi(ArrayList<Phongthi> list){
        defaultComboBoxModel = new DefaultComboBoxModel();
        if(list!=null && list.size()>0)
        {
            for(Phongthi phongthi : list){
                String row = phongthi.getIdphongthi()+"-"+phongthi.getTenphongthi()+"-"+phongthi.getTrinhdo();
                defaultComboBoxModel.addElement(row);
            }
        }
        return defaultComboBoxModel;
    }

    //Thí sinh
        //table
    public DefaultTableModel getThisinhTableModel(ArrayList<Thisinh> list){
        Vector header = new Vector();
        header.add("CMND");
        header.add("Họ");
        header.add("Tên");
        header.add("Ngày sinh");
        header.add("Nơi sinh");
        header.add("CMND ngày cấp");
        header.add("CMND nơi cấp");
        header.add("Số điện thoại");
        header.add("Email");

        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0) {
            for (Thisinh thisinh : list) {
                Vector row = new Vector();
                row.add(thisinh.getCmnd());
                row.add(thisinh.getHo());
                row.add(thisinh.getTen());
                row.add(thisinh.getNgaysinh());
                row.add(thisinh.getNoisinh());
                row.add(thisinh.getCmnd_ngaycap());
                row.add(thisinh.getCmnd_noicap());
                row.add(thisinh.getSdt());
                row.add(thisinh.getEmail());
                defaultTableModel.addRow(row);
            }
        }
        return defaultTableModel;
    }

    //SBD
        //Table nhap diem
    public DefaultTableModel nhapdiem(ArrayList<Sobaodanh> list){
        Vector header = new Vector();
        header.add("SBD");
        header.add("CMND");
        header.add("Điểm nghe/25");
        header.add("Điểm nói/25");
        header.add("Điểm đọc/25");
        header.add("Điểm viết/25");

        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column>1 && column<6) return true;
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0) {
            for (Sobaodanh sobaodanh : list) {
                Vector row = new Vector();
                row.add(sobaodanh.getSbd());
                row.add(sobaodanh.getCmnd());
                row.add(sobaodanh.getNghe());
                row.add(sobaodanh.getNoi());
                row.add(sobaodanh.getDoc());
                row.add(sobaodanh.getViet());
                defaultTableModel.addRow(row);
            }
        }

        return defaultTableModel;
    }

    //So bao danh
    public DefaultTableModel getTTThiSinh(ArrayList<Sobaodanh> list){
        Vector header = new Vector();
        header.add("SBD");
        header.add("CMND");
        header.add("Họ");
        header.add("Tên");
        header.add("Ngày sinh");
        header.add("Nơi sinh");
        header.add("CMND ngày cấp");
        header.add("CMND nơi cấp");
        header.add("Số điện thoại");
        header.add("Email");

        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0) {
            for (Sobaodanh sobaodanh : list) {
                Vector row = new Vector();
                row.add(sobaodanh.getSbd());
                row.add(sobaodanh.getCmnd());
                BLLThisinh bllThisinh = new BLLThisinh();
                Thisinh thisinh = bllThisinh.get1ThiSinh(sobaodanh.getCmnd());
                if(thisinh!=null){
                    row.add(thisinh.getHo());
                    row.add(thisinh.getTen());
                    row.add(thisinh.getNgaysinh());
                    row.add(thisinh.getNoisinh());
                    row.add(thisinh.getCmnd_ngaycap());
                    row.add(thisinh.getCmnd_noicap());
                    row.add(thisinh.getSdt());
                    row.add(thisinh.getEmail());
                }
                else{
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                }
                defaultTableModel.addRow(row);
            }
        }

        return defaultTableModel;
    }


    //Tra cuu thi sinh (sbd)
        //table
    public DefaultTableModel tracuusbd(ArrayList<Sobaodanh> list){
        Vector header = new Vector();
        header.add("CMND");
        header.add("Họ");
        header.add("Tên");
        header.add("Số điện thoại");
        header.add("id khóa thi");
        header.add("Tên khóa thi");
        header.add("ngày thi");
        header.add("id phòng thi");
        header.add("Tên phòng thi");
        header.add("giờ thi");
        header.add("Trình độ");
        header.add("số báo danh");
        header.add("Điểm nghe/25");
        header.add("Điểm nói/25");
        header.add("Điểm đọc/25");
        header.add("Điểm viết/25");

        defaultTableModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        if(list!=null && list.size()>0) {
            for (Sobaodanh sobaodanh : list){
                Vector row = new Vector();
                row.add(sobaodanh.getCmnd());
                //search thi sinh -> cmnd
                    //begin
                BLLThisinh bllThisinh = new BLLThisinh();
                Thisinh thisinh = bllThisinh.get1ThiSinh(sobaodanh.getCmnd());
                if(thisinh!=null){
                    row.add(thisinh.getHo());
                    row.add(thisinh.getTen());
                    row.add(thisinh.getSdt());
                }
                else{
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                    row.add("Không tìm thấy TT thí sinh");
                }
                    //end
                row.add(sobaodanh.getIdkhoathi());
                //search khoathi -> idkhoathi
                    //begin
                BLLKhoathi bllKhoathi = new BLLKhoathi();
                Khoathi khoathi = bllKhoathi.get1KhoaThi(sobaodanh.getIdkhoathi());
                if(khoathi!=null){
                    row.add(khoathi.getTenkhoathi());
                    row.add(khoathi.getNgaythi());
                }
                else{
                    row.add("Không tìm thấy TT khóa thi");
                    row.add("Không tìm thấy TT khóa thi");
                }
                    //end
                row.add(sobaodanh.getIdphongthi());
                //search phongthi -> idphongthi
                    //begin
                BLLPhongthi bllPhongthi = new BLLPhongthi();
                Phongthi phongthi = bllPhongthi.get1Phongthi(sobaodanh.getIdphongthi());
                if(phongthi!=null){
                    row.add(phongthi.getTenphongthi());
                    //Search cathi -> idcathi
                        //begin
                    BLLCathi bllCathi = new BLLCathi();
                    Cathi cathi = bllCathi.get1cathiWithID(phongthi.getIdcathi());
                    if(cathi!=null) row.add(cathi.getGiothi());
                    else row.add("Không tìm thấy TT ca thi");
                        //end
                    row.add(phongthi.getTrinhdo());
                }
                else{
                    row.add("Không tìm thấy TT phòng thi");
                    row.add("Không tìm thấy TT phòng thi");
                    row.add("Không tìm thấy TT phòng thi");
                }
                    //end
                row.add(sobaodanh.getSbd());
                int nghe = sobaodanh.getNghe();
                int noi = sobaodanh.getNoi();
                int doc = sobaodanh.getDoc();
                int viet = sobaodanh.getViet();
                if(nghe==-1) row.add("Chưa thi");
                else{
                    if(nghe==-2) row.add("Bỏ thi");
                    else row.add(nghe);
                }
                if(noi==-1) row.add("Chưa thi");
                else{
                    if(noi==-2) row.add("Bỏ thi");
                    else row.add(noi);
                }
                if(doc==-1) row.add("Chưa thi");
                else{
                    if(doc==-2) row.add("Bỏ thi");
                    else row.add(doc);
                }
                if(viet==-1) row.add("Chưa thi");
                else{
                    if(viet==-2) row.add("Bỏ thi");
                    else row.add(viet);
                }

                defaultTableModel.addRow(row);
            }
        }
        return defaultTableModel;
    }



}
