package DAL;

import ENTITY.Khoathi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALKhoathi extends DatabaseManager{
    String name = "khoathi";

    public DALKhoathi(){
        ConnectionDB();
    }

    public ArrayList<Khoathi> getALLKhoathi(String order){
        String where = "idkhoathi like '%%'";
        //String order = "idkhoathi ASC";
        ArrayList<Khoathi> list = new ArrayList<Khoathi>();
        Khoathi khoathi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    khoathi = new Khoathi();
                    khoathi.setIdkhoathi(resultset.getInt("idkhoathi"));
                    khoathi.setTenkhoathi(resultset.getString("tenkhoathi"));
                    khoathi.setNgaythi(resultset.getString("ngaythi"));
                    list.add(khoathi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALKhoathi -> getALLKhoathi -> "+e.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Khoathi> getListKhoathiWithSearchOROrder(String where, String order){
        ArrayList<Khoathi> list = new ArrayList<Khoathi>();
        Khoathi khoathi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    khoathi = new Khoathi();
                    khoathi.setIdkhoathi(resultset.getInt("idkhoathi"));
                    khoathi.setTenkhoathi(resultset.getString("tenkhoathi"));
                    khoathi.setNgaythi(resultset.getString("ngaythi"));
                    list.add(khoathi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALKhoathi -> getListKhoathiWithSearchOROrder -> "+e.getMessage());
            }
        }
        return null;
    }

    public boolean insertKhoathi(Khoathi khoathi){
        String column = "idkhoathi, tenkhoathi, ngaythi";
        String value = "'"+khoathi.getIdkhoathi()+"', '"+khoathi.getTenkhoathi()+"', '"+khoathi.getNgaythi()+"'";
        return insertrow(name, column, value);
    }


}
