package BLL;

import DAL.DALKhoathi;
import ENTITY.Khoathi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class BLLKhoathi {
    DALKhoathi dalkhoathi;

    public BLLKhoathi(){
        dalkhoathi = new DALKhoathi();
    }

    //Get ALL list
    public ArrayList<Khoathi> getALLListOrderID(){
        String order = "idkhoathi ASC";
        return dalkhoathi.getALLKhoathi(order);
    }

    //Get 1 khoa thi
    public Khoathi get1KhoaThi(int id){
        String where = "idkhoathi ='"+id+"'";
        String order = "idkhoathi ASC";
        ArrayList<Khoathi> list = dalkhoathi.getListKhoathiWithSearchOROrder(where, order);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }


    //Create new idkhoathi
    public int getnewidkhoathi(){
        ArrayList<Khoathi> list = getALLListOrderID();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getIdkhoathi()+1;
        }
        return 1;
    }

    //Kiem tra khoa thang nay da co ngay thi chua
    public boolean checkinsertkhoathi(String tenkhoathi, String mmyyyy){
        String where = "tenkhoathi ='"+tenkhoathi+"' and ngaythi like '%/"+mmyyyy+"'";
        String order = "idkhoathi ASC";
        ArrayList<Khoathi> list = dalkhoathi.getListKhoathiWithSearchOROrder(where, order);
        if(list==null || list.size()==0) return true;
        return false;
    }

    //Insert khoa thi
    public int addkhoathi(int sokhoa, int dd, int mm, int yyyy){
        int idkhoathi = getnewidkhoathi();
        String mmyyyy=mm+"/"+yyyy;
        if(mm<10) mmyyyy="0"+mmyyyy;
        String tenkhoathi = "Khóa "+sokhoa+" tháng "+mm;
        boolean bool=checkinsertkhoathi(tenkhoathi, mmyyyy);
        if(!bool) return -1;
        String ngaythi = dd+"/"+mmyyyy;
        if(dd<10) ngaythi="0"+ngaythi;
        Khoathi khoathi = new Khoathi();
        khoathi.setIdkhoathi(idkhoathi);
        khoathi.setTenkhoathi(tenkhoathi);
        khoathi.setNgaythi(ngaythi);
        bool = dalkhoathi.insertKhoathi(khoathi);
        if(bool) return 1;
        return 0;
    }

    //Check ngay thi truoc khi cho thi sinh dang ky
    public boolean checkNgthiDangky(int idkhoaid){
        Khoathi khoathi = get1KhoaThi(idkhoaid);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateDK = dateFormat.format(date);
        CheckInput checkInput = new CheckInput();
        long noday = checkInput.DemCK2Ngay(dateDK, khoathi.getNgaythi());
        if(noday<5) return false;
        return true;
    }

}
