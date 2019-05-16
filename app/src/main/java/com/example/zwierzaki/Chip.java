package com.example.zwierzaki;

import java.util.Date;

public class Chip {
    private String uidch;
    private Date datech;
    private String numer_metryki;
    private String typ;

    public Chip() {
        typ="Chip";
    }

    public Chip(String uidch, Date datech, String numer_metryki,String type) {
        this.uidch = uidch;
        this.datech = datech;
        this.numer_metryki = numer_metryki;
        this.typ=type;

    }

    public String getUidch() {
        return uidch;
    }

    public void setUidch(String uidch) {
        this.uidch = uidch;
    }

    public Date getDatech() {
        return datech;
    }

    public void setDatech(Date datech) {
        this.datech = datech;
    }

    public String getNrMetrch() {
        return numer_metryki;
    }

    public void setNrMetrch(String numer_metryki) {
        this.numer_metryki = numer_metryki;
    }

    public String getTypCh() {
        return typ;
    }

    public void setTypCh(String typ) {
        this.typ = typ;
    }
}
