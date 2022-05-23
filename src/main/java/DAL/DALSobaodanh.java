package DAL;

import ENTITY.Sobaodanh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALSobaodanh extends DatabaseManager{

    String name="sobaodanh";

    public DALSobaodanh(){
        ConnectionDB();
    }


    //read table sbd with where and order
    public ArrayList<Sobaodanh> getListSBD(String where, String order){
        ArrayList<Sobaodanh> list = new ArrayList<Sobaodanh>();
        Sobaodanh sobaodanh;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null) {
            try {
                while (resultset.next()) {
                    sobaodanh = new Sobaodanh();
                    sobaodanh.setCmnd(resultset.getString("cmnd"));
                    sobaodanh.setIdkhoathi(resultset.getInt("idkhoathi"));
                    sobaodanh.setIdphongthi(resultset.getInt("idphongthi"));
                    sobaodanh.setSbd(resultset.getString("sbd"));
                    sobaodanh.setNghe(resultset.getInt("nghe"));
                    sobaodanh.setNoi(resultset.getInt("noi"));
                    sobaodanh.setDoc(resultset.getInt("doc"));
                    sobaodanh.setViet(resultset.getInt("viet"));
                    list.add(sobaodanh);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALSobaodanh -> getListSBD -> "+e.getMessage());
            }
        }
        return null;
    }


    //Insert 1 thi sinh dang ky
    public boolean insert1sbd(Sobaodanh sobaodanh){
        String column="cmnd, idkhoathi, idphongthi, sbd, nghe, noi, doc, viet";
        String value="'"+sobaodanh.getCmnd()+"', '"
                +sobaodanh.getIdkhoathi()+"', '"
                +sobaodanh.getIdphongthi()+"', '"
                +sobaodanh.getSbd()+"', '"
                +sobaodanh.getNghe()+"', '"
                +sobaodanh.getNoi()+"', '"
                +sobaodanh.getDoc()+"', '"
                +sobaodanh.getViet()+"'";
        return insertrow(name, column, value);
    }

    //update 1 diem so thi sinh
    public boolean updateDiem1ThiSinh(Sobaodanh sobaodanh){
        String set="nghe='"+sobaodanh.getNghe()+"', "
                +"noi='"+sobaodanh.getNoi()+"', "
                +"doc='"+sobaodanh.getDoc()+"', "
                +"viet='"+sobaodanh.getViet()+"'";
        String where="cmnd='"+sobaodanh.getCmnd()+"' and idkhoathi='"+sobaodanh.getIdkhoathi()+"' and idphongthi='"+sobaodanh.getIdphongthi()+"' and sbd='"+sobaodanh.getSbd()+"'";
        return updaterow(name, set, where);
    }
}
