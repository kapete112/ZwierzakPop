package com.example.zwierzaki;

import java.util.Date;

public class Szczepienie {
    private String uid;
    private Date date;
    private boolean wscieklizna;
    private boolean parwowiroza;
    private boolean nosowka;
    private boolean leptospiroza;
    private boolean rubarth;
    private String inne;

    public Szczepienie() {
        wscieklizna=false;
        parwowiroza=false;
        nosowka=false;
        leptospiroza=false;
        rubarth=false;


    }

    public Szczepienie(String uid, Date date, boolean wscieklizna, boolean parwowiroza, boolean nosowka, boolean leptospiroza, boolean rubarth, String inne) {
        this.uid = uid;
        this.date = date;
        this.wscieklizna = wscieklizna;
        this.parwowiroza = parwowiroza;
        this.nosowka = nosowka;
        this.leptospiroza = leptospiroza;
        this.rubarth = rubarth;
        this.inne = inne;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isWscieklizna() {
        return wscieklizna;
    }

    public void setWscieklizna(boolean wscieklizna) {
        this.wscieklizna = wscieklizna;
    }

    public boolean isParwowiroza() {
        return parwowiroza;
    }

    public void setParwowiroza(boolean parwowiroza) {
        this.parwowiroza = parwowiroza;
    }

    public boolean isNosowka() {
        return nosowka;
    }

    public void setNosowka(boolean nosowka) {
        this.nosowka = nosowka;
    }

    public boolean isLeptospiroza() {
        return leptospiroza;
    }

    public void setLeptospiroza(boolean leptospiroza) {
        this.leptospiroza = leptospiroza;
    }

    public boolean isRubarth() {
        return rubarth;
    }

    public void setRubarth(boolean rubarth) {
        this.rubarth = rubarth;
    }

    public String getInne() {
        return inne;
    }

    public void setInne(String inne) {
        this.inne = inne;
    }
}
