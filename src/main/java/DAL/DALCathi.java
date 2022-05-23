package DAL;

import ENTITY.Cathi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALCathi extends DatabaseManager{
    String name = "cathi";

    public DALCathi(){
        ConnectionDB();
    }

    public ArrayList<Cathi> getALLCathi(String order){
        String where ="idcathi like '%%'";
        ArrayList<Cathi> list = new ArrayList<Cathi>();
        Cathi cathi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    cathi = new Cathi();
                    cathi.setIdcathi(resultset.getInt("idcathi"));
                    cathi.setGiothi(resultset.getString("giothi"));
                    list.add(cathi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALCathi -> getALLCathi -> "+e.getMessage());
            }
        }
        return null;
    }


    public ArrayList<Cathi> getListCathiWithSearchOROrder(String where, String order){
        ArrayList<Cathi> list = new ArrayList<Cathi>();
        Cathi cathi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    cathi = new Cathi();
                    cathi.setIdcathi(resultset.getInt("idcathi"));
                    cathi.setGiothi(resultset.getString("giothi"));
                    list.add(cathi);
                }
                if(list.size() > 0){
                    return list;
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALKhoathi -> getListCathiWithSearchOROrder -> "+e.getMessage());
            }
        }
        return null;
    }

    public boolean insertKhoathi(Cathi cathi){
        String column = "idcathi, giothi";
        String value = "'"+cathi.getIdcathi()+"', '"+cathi.getGiothi()+"'";
        return insertrow(name, column, value);
    }
}
