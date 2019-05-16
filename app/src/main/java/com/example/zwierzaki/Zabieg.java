package com.example.zwierzaki;

import java.util.Date;

public class Zabieg {
    private String Opis;
    private String numer_metryki;
    private String uidz;
    private Date datez;
    private String typ;

    public Zabieg() {
    }

    public Zabieg( String opis, Date datez,String uidy,String nrmerr,String typez) {
        this.numer_metryki=nrmerr;
        this.uidz=uidy;
        this.Opis = opis;
        this.datez = datez;
        this.typ=typez;
    }


    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public Date getDatez() {
        return datez;
    }

    public void setDatez(Date datez) {
        this.datez = datez;
    }

    public String getNrMetryki() {
        return numer_metryki;
    }

    public void setNrMetryki(String numer_metryki) {
        this.numer_metryki = numer_metryki;
    }

    public String getUidz() {
        return uidz;
    }

    public void setUidz(String uidz) {
        this.uidz = uidz;
    }

    public String getTypz() {
        return typ;
    }

    public void setTypz(String typ) {
        this.typ = typ;
    }
}
