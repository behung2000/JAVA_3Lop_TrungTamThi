package ENTITY;

public class Phongthi {
    int idphongthi;
    String tenphongthi;
    int idcathi;
    int khoathi;
    String trinhdo;

    public Phongthi() {
    }

    public Phongthi(int idphongthi, String tenphongthi, int idcathi, int khoathi, String trinhdo) {
        this.idphongthi = idphongthi;
        this.tenphongthi = tenphongthi;
        this.idcathi = idcathi;
        this.khoathi = khoathi;
        this.trinhdo = trinhdo;
    }

    public int getIdphongthi() {
        return idphongthi;
    }

    public void setIdphongthi(int idphongthi) {
        this.idphongthi = idphongthi;
    }

    public String getTenphongthi() {
        return tenphongthi;
    }

    public void setTenphongthi(String tenphongthi) {
        this.tenphongthi = tenphongthi;
    }

    public int getIdcathi() {
        return idcathi;
    }

    public void setIdcathi(int idcathi) {
        this.idcathi = idcathi;
    }

    public int getKhoathi() {
        return khoathi;
    }

    public void setKhoathi(int khoathi) {
        this.khoathi = khoathi;
    }

    public String getTrinhdo() {
        return trinhdo;
    }

    public void setTrinhdo(String trinhdo) {
        this.trinhdo = trinhdo;
    }
}
