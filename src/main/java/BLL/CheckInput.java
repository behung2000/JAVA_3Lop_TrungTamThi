package BLL;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.StringTokenizer;

public class CheckInput {
    public CheckInput(){

    }

    public boolean CheckInputisNumber(String input) {
        try{
            int i = Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean CheckInputisDouble(String input) {
        try{
            Double i = Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean CheckName(String name){
        if(name == null) return false;
        if(name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) return false;
        return true;
    }

    public boolean CheckHo(String ho){
        if(ho == null) return false;
        if(ho.equalsIgnoreCase("") || ho.equalsIgnoreCase("null")) return false;
        StringTokenizer stringTokenizer = new StringTokenizer(ho," ");
        if(stringTokenizer.countTokens()>1) return false;
        return true;
    }

    public boolean CheckSDT(String sdt){
        if(sdt == null) return false;
        if(sdt.length()>=10 && sdt.length()<=11){
            return CheckInputisNumber(sdt);
        }
        return false;
    }

    public boolean CheckEmail(String email){
        if(email == null) return false;
        String bool = email.replace("@","");
        if((bool.length()+1)==email.length()) return true;
        return false;
    }

    public boolean CheckCMND(String cmnd){
        if(cmnd == null) return false;
        if(cmnd.length()==9 || cmnd.length()==12){
            //System.out.println(CheckInputisDouble(cmnd));
            return CheckInputisDouble(cmnd);
        }
        return false;
    }

    public boolean CheckNgSinhVSNgCap(String Ngaysinh, String NgayCap){
        StringTokenizer stngaysinh = new StringTokenizer(Ngaysinh, "/");
        String ddns = stngaysinh.nextToken();
        String mmns = stngaysinh.nextToken();
        String yyns = stngaysinh.nextToken();
        StringTokenizer stngaycap = new StringTokenizer(NgayCap,"/");
        String ddnc = stngaycap.nextToken();
        String mmnc = stngaycap.nextToken();
        String yync = stngaycap.nextToken();
        int yearns = Integer.parseInt(yyns);
        int yearnc = Integer.parseInt(yync);
        if(yearnc-yearns<17) return false;
        return true;
    }

    public long DemCK2Ngay(String dateDK, String dateNgthi)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        //dateDK = dateDK.replace("/","-");
        //dateNgthi = dateNgthi.replace("/","-");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // Định nghĩa 2 mốc thời gian ban đầu
        //Date date1 = Date.valueOf("2011-06-15");
        //Date date2 = Date.valueOf("2011-07-30");

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(dateDK);
            date2 = sdf.parse(dateNgthi);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

        return noDay;
    }

    public boolean CheckDiem(String sdiem){
        if(!CheckInputisNumber(sdiem)) return false;
        int diem = Integer.parseInt(sdiem);
        if(diem<-2 || diem>25) return false;
        return true;
    }

    /*
    public static void main(String[] strings){
        CheckInput checkInput = new CheckInput();
        System.out.println("ck="+checkInput.DemCK2Ngay("31/12/2021","10/1/2022"));
    }

     */
}
