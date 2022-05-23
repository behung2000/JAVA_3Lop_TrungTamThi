package DAL;

import ENTITY.Phongthi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALPhongthi extends DatabaseManager{
    String name = "phongthi";

    public DALPhongthi(){
        ConnectionDB();
    }

    public ArrayList<Phongthi> getALLPhongthi(String order){
        String where ="idkhoathi like '%%'";
        ArrayList<Phongthi> list = new ArrayList<Phongthi>();
        Phongthi phongthi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    phongthi = new Phongthi();
                    phongthi.setIdphongthi(resultset.getInt("idphongthi"));
                    phongthi.setTenphongthi(resultset.getString("tenphongthi"));
                    phongthi.setIdcathi(resultset.getInt("idcathi"));
                    phongthi.setKhoathi(resultset.getInt("idkhoathi"));
                    phongthi.setTrinhdo(resultset.getString("trinhdo"));
                    list.add(phongthi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALPhongthi -> getALLPhongthi -> "+e.getMessage());
            }
        }
        return null;
    }


    public ArrayList<Phongthi> getALLPhongthiWithID(int idkhoathi, String trinhdo, String order){
        String where ="idkhoathi ='"+idkhoathi+"' AND trinhdo='"+trinhdo+"'";
        ArrayList<Phongthi> list = new ArrayList<Phongthi>();
        Phongthi phongthi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    phongthi = new Phongthi();
                    phongthi.setIdphongthi(resultset.getInt("idphongthi"));
                    phongthi.setTenphongthi(resultset.getString("tenphongthi"));
                    phongthi.setIdcathi(resultset.getInt("idcathi"));
                    phongthi.setKhoathi(resultset.getInt("idkhoathi"));
                    phongthi.setTrinhdo(resultset.getString("trinhdo"));
                    list.add(phongthi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALPhongthi -> getALLPhongthi -> "+e.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Phongthi> getListPhongthi(String where, String order){
        //String where ="idkhoathi ='"+idkhoathi+"' AND trinhdo='"+trinhdo+"'";
        ArrayList<Phongthi> list = new ArrayList<Phongthi>();
        Phongthi phongthi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    phongthi = new Phongthi();
                    phongthi.setIdphongthi(resultset.getInt("idphongthi"));
                    phongthi.setTenphongthi(resultset.getString("tenphongthi"));
                    phongthi.setIdcathi(resultset.getInt("idcathi"));
                    phongthi.setKhoathi(resultset.getInt("idkhoathi"));
                    phongthi.setTrinhdo(resultset.getString("trinhdo"));
                    list.add(phongthi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALPhongthi -> getALLPhongthi -> "+e.getMessage());
            }
        }
        return null;
    }

    public boolean insertPhongthi(Phongthi phongthi){
        String column = "idphongthi, tenphongthi, idcathi, idkhoathi, trinhdo";
        String value = "'"+phongthi.getIdphongthi()+"', '"+phongthi.getTenphongthi()+"', '"+phongthi.getIdcathi()+"', '"+phongthi.getKhoathi()+"', '"+phongthi.getTrinhdo()+"'";
        return insertrow(name, column, value);
    }
}
