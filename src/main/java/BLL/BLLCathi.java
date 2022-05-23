package BLL;

import DAL.DALCathi;
import ENTITY.Cathi;

import java.util.ArrayList;

public class BLLCathi {
    DALCathi dalcathi;

    public BLLCathi(){
        dalcathi = new DALCathi();
    }

    //get all list
    public ArrayList<Cathi> getALLListOrderID(){
        String order = "idcathi ASC";
        return dalcathi.getALLCathi(order);
    }

    //get 1 cathi with id
    public Cathi get1cathiWithID(int id){
        String where = "idcathi ='"+id+"'";
        String order = "idcathi ASC";
        ArrayList<Cathi> list = dalcathi.getListCathiWithSearchOROrder(where, order);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    //get max id cathi
    public int getMaxIDCathi(){
        ArrayList<Cathi> list = getALLListOrderID();
        return list.get(list.size()-1).getIdcathi();
    }

    //create new id ca thi
    public int getnewidcathi(){
        ArrayList<Cathi> list = getALLListOrderID();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getIdcathi()+1;
        }
        return 1;
    }

    //check insert ca thi
    public boolean checkinsertcathi(String giothi){
        String where = "giothi = '"+giothi+"'";
        String order = "idcathi ASC";
        ArrayList<Cathi> list = dalcathi.getListCathiWithSearchOROrder(where, order);
        if(list==null || list.size()==0) return true;
        return false;
    }

    //insert cathi
    public int insertcathi(int gio, int phut){
        int idcathi = getnewidcathi();
        String giothi = gio+":";
        if(gio<10) giothi="0"+giothi;
        if(phut<10) giothi=giothi+"0"+phut+":00";
        else giothi=giothi+phut+":00";
        boolean bool = checkinsertcathi(giothi);
        if(!bool) return -1;
        Cathi cathi = new Cathi();
        cathi.setIdcathi(idcathi);
        cathi.setGiothi(giothi);
        bool = dalcathi.insertKhoathi(cathi);
        if(bool) return 1;
        return 0;
    }
}
