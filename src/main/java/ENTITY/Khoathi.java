package ENTITY;

public class Khoathi {
    int idkhoathi;
    String tenkhoathi;
    String ngaythi;

    public Khoathi() {
    }

    public Khoathi(int idkhoathi, String tenkhoathi, String ngaythi) {
        this.idkhoathi = idkhoathi;
        this.tenkhoathi = tenkhoathi;
        this.ngaythi = ngaythi;
    }

    public int getIdkhoathi() {
        return idkhoathi;
    }

    public void setIdkhoathi(int idkhoathi) {
        this.idkhoathi = idkhoathi;
    }

    public String getTenkhoathi() {
        return tenkhoathi;
    }

    public void setTenkhoathi(String tenkhoathi) {
        this.tenkhoathi = tenkhoathi;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }
}
