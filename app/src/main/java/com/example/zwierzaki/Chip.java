package com.example.zwierzaki;

import java.util.Date;

public class Chip {
    private String uidch;
    private Date datech;
    private String nrMetrch;

    public Chip() {
    }

    public Chip(String uidch, Date datech, String nrMetrch) {
        this.uidch = uidch;
        this.datech = datech;
        this.nrMetrch = nrMetrch;
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
}
