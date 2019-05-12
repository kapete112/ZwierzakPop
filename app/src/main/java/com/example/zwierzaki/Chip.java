package com.example.zwierzaki;

import java.util.Date;

public class Chip {
    private String uidch;
    private Date datech;
    private String nrMetrch;
    private String typ;

    public Chip() {
        typ="Chip";
    }

    public Chip(String uidch, Date datech, String nrMetrch,String type) {
        this.uidch = uidch;
        this.datech = datech;
        this.nrMetrch = nrMetrch;
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
        return nrMetrch;
    }

    public void setNrMetrch(String nrMetrch) {
        this.nrMetrch = nrMetrch;
    }

    public String getTypCh() {
        return typ;
    }

    public void setTypCh(String typ) {
        this.typ = typ;
    }
}
