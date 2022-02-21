package com.example.myapp;

public class Model {
    String kasus, kasushariini, meninggal, meninggalhariini, sembuh, sembuhhariini, aktif, negara;

    public Model(String kasus, String kasushariini, String meninggal, String meninggalhariini, String sembuh, String sembuhhariini, String aktif, String negara) {
        this.kasus = kasus;
        this.kasushariini = kasushariini;
        this.meninggal = meninggal;
        this.meninggalhariini = meninggalhariini;
        this.sembuh = sembuh;
        this.sembuhhariini = sembuhhariini;
        this.aktif = aktif;
        this.negara = negara;
    }

    public String getKasus() {
        return kasus;
    }

    public void setKasus(String kasus) {
        this.kasus = kasus;
    }

    public String getKasushariini() {
        return kasushariini;
    }

    public void setKasushariini(String kasushariini) {
        this.kasushariini = kasushariini;
    }

    public String getMeninggal() {
        return meninggal;
    }

    public void setMeninggal(String meninggal) {
        this.meninggal = meninggal;
    }

    public String getMeninggalhariini() {
        return meninggalhariini;
    }

    public void setMeninggalhariini(String meninggalhariini) {
        this.meninggalhariini = meninggalhariini;
    }

    public String getSembuh() {
        return sembuh;
    }

    public void setSembuh(String sembuh) {
        this.sembuh = sembuh;
    }

    public String getSembuhhariini() {
        return sembuhhariini;
    }

    public void setSembuhhariini(String sembuhhariini) {
        this.sembuhhariini = sembuhhariini;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }
}
