package BLL;

import DAL.DALThisinh;
import ENTITY.Thisinh;

import java.util.ArrayList;

public class BLLThisinh {
    DALThisinh dalthisinh;

    public BLLThisinh(){
        dalthisinh = new DALThisinh();
    }

    //Get ALL list thí sinh
    public ArrayList<Thisinh> getALLListThiSinh(){
        String where = "cmnd like '%%'";
        String order = "cmnd ASC";
        return dalthisinh.getALLThiSinh(where,order);
    }

    //Get list thí sinh with cmnd
    public ArrayList<Thisinh> getListThiSinhWithCMND(String cmnd){
        String where = "cmnd like '%"+cmnd+"%'";
        String order = "cmnd ASC";
        return dalthisinh.getALLThiSinh(where,order);
    }

    //Get 1 thi sinh with cmnd
    public Thisinh get1ThiSinh(String cmnd){
        ArrayList<Thisinh> list = getListThiSinhWithCMND(cmnd);
        if(list!=null && list.size()>0) return list.get(0);
        return null;
    }

    //Insert 1 thi sinh
    public int insert1thiSinh(String cmnd, String ho, String ten, String ngaysinh, String noisinh, String cmnd_ngaycap, String cmnd_noicap, String sdt, String email){
        CheckInput checkinput = new CheckInput();
        if(!checkinput.CheckCMND(cmnd)) return -1;
        if(!checkinput.CheckHo(ho)) return -2;
        if(!checkinput.CheckName(ten)) return -3;
        if(!checkinput.CheckName(noisinh)) return -4;
        if(!checkinput.CheckName(cmnd_noicap)) return -5;
        if(!checkinput.CheckSDT(sdt)) return -6;
        if(!checkinput.CheckEmail(email)) return -7;
        if(!checkinput.CheckNgSinhVSNgCap(ngaysinh, cmnd_ngaycap)) return -8;

        Thisinh thisinh = new Thisinh(cmnd, ho, ten, ngaysinh, noisinh, cmnd_ngaycap, cmnd_noicap, sdt, email);

        boolean bool = dalthisinh.insert1Thisinh(thisinh);
        if(bool) return 1;
        return 0;
    }
}
