package BLL;

import DAL.DALSobaodanh;
import ENTITY.Phongthi;
import ENTITY.Sobaodanh;

import java.util.ArrayList;

public class BLLSobaodanh {
    DALSobaodanh dalsobaodanh;

    public BLLSobaodanh(){
        dalsobaodanh = new DALSobaodanh();
    }


    //search table thi sinh -> get list so bao danh
    public ArrayList<Sobaodanh> getListSBDWithThiSinh(String name, String sdt){
        String where="EXISTS(SELECT cmnd FROM thisinh WHERE thisinh.cmnd=sobaodanh.cmnd AND CONCAT(thisinh.ho, thisinh.ten) LIKE '%"+name+"%' AND thisinh.sdt LIKE '%"+sdt+"%' ORDER BY thisinh.cmnd ASC)";
        String order = "cmnd ASC";
        return dalsobaodanh.getListSBD(where, order);
    }

    //get list sobaodanh with khoaid vs phongthiid
    public ArrayList<Sobaodanh> getListSBDWithidkhoathiandidphongthi(int idkhoaid, int idphongthi, String nameorder){
        String where = "idkhoathi='"+idkhoaid+"' and idphongthi='"+idphongthi+"'";
        String order = nameorder+" ASC";
        return dalsobaodanh.getListSBD(where, order);
    }

    //get list sobaodanh with khoaid vs trinhdo
    public ArrayList<Sobaodanh> getListSBDWithidkhoathiandTrinhdo(int idkhoaid, String trinhdo, String nameorder){
        String where = "idkhoathi='"+idkhoaid+"' and sbd like'"+trinhdo+"%'";
        String order = nameorder+" ASC";
        return dalsobaodanh.getListSBD(where, order);
    }

    //Check so luong thi sinh trong phong thi
    public boolean checksltsofpt(int idkhoathi, int idphongthi){
        ArrayList<Sobaodanh> list = getListSBDWithidkhoathiandidphongthi(idkhoathi, idphongthi, "cmnd");
        if(list!=null && list.size()>30) return false;
        return true;
    }

    //Create so bao danh
    public String createnewsbd(int idkhoathi, int idphongthi, String trinhdo){
        ArrayList<Sobaodanh> list = getListSBDWithidkhoathiandTrinhdo(idkhoathi, trinhdo, "sbd");
        if(list==null || list.size()==0){
            return trinhdo+"001";
        }
        else{
            String sbdcuoi=list.get(list.size()-1).getSbd();
            String sottsbdcuoi=sbdcuoi.substring(2);
            int sottcuoi=Integer.parseInt(sottsbdcuoi);
            sottcuoi++;
            if(sottcuoi<10) return trinhdo+"00"+sottcuoi;
            if(sottcuoi<99) return trinhdo+"0"+sbdcuoi;
            return trinhdo+sbdcuoi;
        }
    }

    //Insert 1 so bao danh
    public int DangKyThi(String cmnd, int idkhoathi, String trinhdo){
        BLLKhoathi bllKhoathi = new BLLKhoathi();
        if(!bllKhoathi.checkNgthiDangky(idkhoathi)) return -1;
        BLLPhongthi bllPhongthi = new BLLPhongthi();
        ArrayList<Phongthi> listpt = bllPhongthi.checkPTBeforeDKT(idkhoathi, trinhdo);
        if(listpt==null || listpt.size()==0) return -2;
        int idphongthi=0;
        for(Phongthi phongthi : listpt){
            if(checksltsofpt(idkhoathi, phongthi.getIdphongthi())){
                idphongthi=phongthi.getIdphongthi();
                break;
            }
        }
        if(idphongthi==0) return -3;
        String sbd = createnewsbd(idkhoathi, idphongthi, trinhdo);
        Sobaodanh sobaodanh = new Sobaodanh();
        sobaodanh.setCmnd(cmnd);
        sobaodanh.setIdkhoathi(idkhoathi);
        sobaodanh.setIdphongthi(idphongthi);
        sobaodanh.setSbd(sbd);
        sobaodanh.setNghe(-1);
        sobaodanh.setNoi(-1);
        sobaodanh.setDoc(-1);
        sobaodanh.setViet(-1);

        boolean bool = dalsobaodanh.insert1sbd(sobaodanh);
        if(bool) return 0;

        return 1;
    }

    //Update diem 1 thi sinh voi sbd idkhoathi idphongthi
    public boolean updateDiem1Thisinh(String cmnd, int idkhoathi, int idphongthi, String sbd, int nghe, int noi, int doc, int viet){
        Sobaodanh sobaodanh = new Sobaodanh(cmnd,idkhoathi, idphongthi, sbd, nghe, noi, doc, viet);
        return dalsobaodanh.updateDiem1ThiSinh(sobaodanh);
    }
}
