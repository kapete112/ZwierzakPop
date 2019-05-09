package com.example.zwierzaki;

import java.util.Date;

public class Zabieg {
    private String Tytul;
    private String Opis;
    private Date datez;

    public Zabieg() {
    }

    public Zabieg(String tytul, String opis, Date datez) {
        Tytul = tytul;
        Opis = opis;
        this.datez = datez;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
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
}
