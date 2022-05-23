package ENTITY;

public class Thisinh {
    String cmnd;
    String ho;
    String ten;
    String ngaysinh;
    String noisinh;
    String cmnd_ngaycap;
    String cmnd_noicap;
    String sdt;
    String email;

    public Thisinh() {
    }

    public Thisinh(String cmnd, String ho, String ten, String ngaysinh, String noisinh, String cmnd_ngaycap, String cmnd_noicap, String sdt, String email) {
        this.cmnd = cmnd;
        this.ho = ho;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.cmnd_ngaycap = cmnd_ngaycap;
        this.cmnd_noicap = cmnd_noicap;
        this.sdt = sdt;
        this.email = email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getCmnd_ngaycap() {
        return cmnd_ngaycap;
    }

    public void setCmnd_ngaycap(String cmnd_ngaycap) {
        this.cmnd_ngaycap = cmnd_ngaycap;
    }

    public String getCmnd_noicap() {
        return cmnd_noicap;
    }

    public void setCmnd_noicap(String cmnd_noicap) {
        this.cmnd_noicap = cmnd_noicap;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
