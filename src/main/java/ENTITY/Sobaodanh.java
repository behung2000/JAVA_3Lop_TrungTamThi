package ENTITY;

public class Sobaodanh {
    String cmnd;
    int idkhoathi;
    int idphongthi;
    String sbd;
    int nghe;
    int noi;
    int doc;
    int viet;

    public Sobaodanh() {
    }

    public Sobaodanh(String cmnd, int idkhoathi, int idphongthi, String sbd, int nghe, int noi, int doc, int viet) {
        this.cmnd = cmnd;
        this.idkhoathi = idkhoathi;
        this.idphongthi = idphongthi;
        this.sbd = sbd;
        this.nghe = nghe;
        this.noi = noi;
        this.doc = doc;
        this.viet = viet;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getIdkhoathi() {
        return idkhoathi;
    }

    public void setIdkhoathi(int idkhoathi) {
        this.idkhoathi = idkhoathi;
    }

    public int getIdphongthi() {
        return idphongthi;
    }

    public void setIdphongthi(int idphongthi) {
        this.idphongthi = idphongthi;
    }

    public String getSbd() {
        return sbd;
    }

    public void setSbd(String sbd) {
        this.sbd = sbd;
    }

    public int getNghe() {
        return nghe;
    }

    public void setNghe(int nghe) {
        this.nghe = nghe;
    }

    public int getNoi() {
        return noi;
    }

    public void setNoi(int noi) {
        this.noi = noi;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public int getViet() {
        return viet;
    }

    public void setViet(int viet) {
        this.viet = viet;
    }
}
