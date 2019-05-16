package com.example.zwierzaki;

import java.util.Date;

public class Chip {
    private String uid;
    private Date datech;
    private String numer_metryki;
    private String typ;

    public Chip() {
        typ="Chip";
    }

    public Chip(String uid, Date datech, String numer_metryki,String type) {
        this.uid = uid;
        this.datech = datech;
        this.numer_metryki = numer_metryki;
        this.typ=type;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDatech() {
        return datech;
    }

    public void setDatech(Date datech) {
        this.datech = datech;
    }

    public String getNumer_metryki() {
        return numer_metryki;
    }

    public void setNumer_metryki(String numer_metryki) {
        this.numer_metryki = numer_metryki;
    }

    public String getTyp() {
        return typ;
    }


}
