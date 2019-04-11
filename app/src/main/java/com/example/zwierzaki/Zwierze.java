package com.example.zwierzaki;

import java.util.Date;

public class Zwierze {

    private String DatUr;
   // Date DatSm= null;
   private String Plec;
    private String NrMetryki;
    private String NrMetrykiMatki;
    private String NrMetrykiOjca;
    private String ImieZwierzecia;
    private String UID;

    public Zwierze() {
    }

    public Zwierze(String datUr, String plec, String nrMetryki, String nrMetrykiMatki, String nrMetrykiOjca, String imieZwierzecia, String uID) {
        DatUr = datUr;
        Plec = plec;
        NrMetryki = nrMetryki;
        NrMetrykiMatki = nrMetrykiMatki;
        NrMetrykiOjca = nrMetrykiOjca;
        ImieZwierzecia = imieZwierzecia;
        UID = uID;
    }

    public String getDatUr() {
        return DatUr;
    }

    public void setDatUr(String datUr) {
        DatUr = datUr;
    }

    public String getPlec() {
        return Plec;
    }

    public void setPlec(String plec) {
        Plec = plec;
    }

    public String getNrMetryki() {
        return NrMetryki;
    }

    public void setNrMetryki(String nrMetryki) {
        NrMetryki = nrMetryki;
    }

    public String getNrMetrykiMatki() {
        return NrMetrykiMatki;
    }

    public void setNrMetrykiMatki(String nrMetrykiMatki) {
        NrMetrykiMatki = nrMetrykiMatki;
    }

    public String getNrMetrykiOjca() {
        return NrMetrykiOjca;
    }

    public void setNrMetrykiOjca(String nrMetrykiOjca) {
        NrMetrykiOjca = nrMetrykiOjca;
    }

    public String getImieZwierzecia() {
        return ImieZwierzecia;
    }

    public void setImieZwierzecia(String imieZwierzecia) {
        ImieZwierzecia = imieZwierzecia;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    /*public Date getDatSm() {
        return DatSm;
    }

    public void setDatSm(Date datSm) {
        DatSm = datSm;
    }*/
}
