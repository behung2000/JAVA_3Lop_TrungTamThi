package DAL;

import ENTITY.Thisinh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALThisinh extends DatabaseManager{
    String name = "thisinh";

    public DALThisinh(){
        ConnectionDB();
    }

    //get 1 table
    public ArrayList<Thisinh> getALLThiSinh(String where, String order){
        //String where ="cmnd like '%%'";
        ArrayList<Thisinh> list = new ArrayList<Thisinh>();
        Thisinh thisinh;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    thisinh = new Thisinh();
                    thisinh.setCmnd(resultset.getString("cmnd"));
                    thisinh.setHo(resultset.getString("ho"));
                    thisinh.setTen(resultset.getString("ten"));
                    thisinh.setNgaysinh(resultset.getString("ngaysinh"));
                    thisinh.setNoisinh(resultset.getString("noisinh"));
                    thisinh.setCmnd_ngaycap(resultset.getString("cmnd_ngaycap"));
                    thisinh.setCmnd_noicap(resultset.getString("cmnd_noicap"));
                    thisinh.setSdt(resultset.getString("sdt"));
                    thisinh.setEmail(resultset.getString("email"));
                    list.add(thisinh);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALThisinh -> DALThisinh -> "+e.getMessage());
            }
        }
        return null;
    }

    // insert 1 thi sinh
    public boolean insert1Thisinh(Thisinh thisinh){
        String column = "cmnd, ho, ten, ngaysinh, noisinh, cmnd_ngaycap, cmnd_noicap, sdt, email";
        String value = "'"+thisinh.getCmnd()+"', '"
                +thisinh.getHo()+"', '"
                +thisinh.getTen()+"', '"
                +thisinh.getNgaysinh()+"', '"
                +thisinh.getNoisinh()+"', '"
                +thisinh.getCmnd_ngaycap()+"', '"
                +thisinh.getCmnd_noicap()+"', '"
                +thisinh.getSdt()+"', '"
                +thisinh.getEmail()+"'";
        return insertrow(name, column, value);
    }
}
