package BLL;

import DAL.DALPhongthi;
import ENTITY.Khoathi;
import ENTITY.Phongthi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BLLPhongthi {
    DALPhongthi dalphongthi;

    public BLLPhongthi(){
        dalphongthi = new DALPhongthi();
    }


    //Get ALL list order khoaid
    public ArrayList<Phongthi> getALLListOrderID(){
        String order = "idphongthi ASC";
        return dalphongthi.getALLPhongthi(order);
    }

    //Get List pt with khoaid
    public ArrayList<Phongthi> getListWithidkhoathi(int khoaid){
        String where = "idkhoathi='"+khoaid+"'";
        String order = "idphongthi ASC";
        return dalphongthi.getListPhongthi(where, order);
    }


    //Get ALL list with khoaid and trinh do
    public ArrayList<Phongthi> getALLListOrderIDWithKhoaidAndTrinhdo(int idkhoaid, String trinhdo){
        String order = "idphongthi ASC";
        return dalphongthi.getALLPhongthiWithID(idkhoaid, trinhdo, order);
    }

    //Get 1 phong thi
    public Phongthi get1Phongthi(int idphongthi){
        String where = "idphongthi='"+idphongthi+"'";
        String order = "idphongthi ASC";
        ArrayList<Phongthi> list = dalphongthi.getListPhongthi(where, order);
        if(list!=null && list.size()>0) return list.get(0);
        return null;
    }

    //Create new id phong thi
    public int Createnewidphongthi(){
        ArrayList<Phongthi> list = getALLListOrderID();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getIdphongthi()+1;
        }
        return 1;
    }

    //Check create Phongthi bang khoathi
    public boolean CheckPhongthi(int idkhoaid){
        BLLKhoathi bllKhoathi = new BLLKhoathi();
        Khoathi khoathi = bllKhoathi.get1KhoaThi(idkhoaid);
        String dateNgaythi = khoathi.getNgaythi();
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String sdatenow = simpleDateFormat.format(new Date());
        //System.out.println(datenow);
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        Date datenow = null;
        try {
            date = sdf.parse(dateNgaythi);
            datenow = sdf.parse(sdatenow);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(sdatenow+"--"+khoathi.getNgaythi());
        //System.out.println(date.after(datenow));
        if(!date.after(datenow)) return false;
        return true;
    }

    //Check so luong phong thi da tao
    public int CheckSLPhongthi(int idkhoaid, String trinhdo){
        ArrayList<Phongthi> list = getALLListOrderIDWithKhoaidAndTrinhdo(idkhoaid, trinhdo);
        if(list!=null && list.size()>0){
            return list.size()+1;
        }
        return 1;
    }

    //Insert n phong thi
    public int insertNPhongthi(int idkhoathi, int sl, String trinhdo){
        int bool=0;
        int newid=Createnewidphongthi();
        int idcathidau=1;
        BLLCathi bllCathi = new BLLCathi();
        int idcathimax=bllCathi.getMaxIDCathi();

        //Kiểm tra
        boolean bool1 = CheckPhongthi(idkhoathi);
        if(bool1==false) return -1;

        int soluong = CheckSLPhongthi(idkhoathi, trinhdo);

        for( int i=1 ; i<=sl; i++){
            String tenphong = trinhdo+"P";
            if(soluong<10) tenphong = tenphong+"0"+soluong;
            else  tenphong = tenphong+soluong;

            Phongthi phongthi = new Phongthi();
            phongthi.setIdphongthi(newid);
            phongthi.setTenphongthi(tenphong);
            phongthi.setIdcathi(idcathidau);
            phongthi.setKhoathi(idkhoathi);
            phongthi.setTrinhdo(trinhdo);

            boolean bool2 = dalphongthi.insertPhongthi(phongthi);
            if(!bool2) bool++;

            if(idcathidau==idcathimax) idcathidau=0;
            newid++;
            soluong++;
            idcathidau++;
        }
        return bool;
    }

    //Check phong thi truoc khi thi sinh dang ky
    public ArrayList<Phongthi> checkPTBeforeDKT(int idkhoathi, String trinhdo){
        return getALLListOrderIDWithKhoaidAndTrinhdo(idkhoathi, trinhdo);
    }

}
