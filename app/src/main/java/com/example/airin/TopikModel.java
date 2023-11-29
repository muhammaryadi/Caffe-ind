package com.example.airin;

public class TopikModel {
    private String idTopik;
    private String idPengirim;
    private String nama;
    private String pesanTopik;
    private String waktu;

    public TopikModel(String idTopik, String idPengirim, String nama, String pesanTopik, String waktu) {
        this.idTopik = idTopik;
        this.idPengirim = idPengirim;
        this.nama = nama;
        this.pesanTopik = pesanTopik;
        this.waktu = waktu;
    }

    public String getId() {
        return idTopik;
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public String getPesanTopik() {
        return pesanTopik;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setIdTopik(String idTopik) {
        this.idTopik = idTopik;
    }

    public void setIdPengirim(String idPengirim) {
        this.idPengirim = idPengirim;
    }

    public void setPesanTopik(String pesanTopik) {
        this.pesanTopik = pesanTopik;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}

