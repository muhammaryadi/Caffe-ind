package com.example.airin;

public class RiwayatModel {
    private String id_konsumsi;
    private String id_konsumen;
    private String id_kopi;
    private String quantity;
    private String kafein;
    private String glukosa;
    private String sugar;
    private String waktu;
    private String bahan_tambahan;

    // Konstruktor
    public RiwayatModel(String id_konsumsi, String id_konsumen, String id_kopi, String quantity,
                        String kafein, String glukosa, String sugar, String waktu,
                        String bahan_tambahan) {
        this.id_konsumsi = id_konsumsi;
        this.id_konsumen = id_konsumen;
        this.id_kopi = id_kopi;
        this.quantity = quantity;
        this.kafein = kafein;
        this.glukosa = glukosa;
        this.sugar = sugar;
        this.waktu = waktu;
        this.bahan_tambahan = bahan_tambahan;
    }

    // Getter dan setter sesuai kebutuhan
    public String get_id() {
        return id_konsumsi;
    }

    public void set_id(String _id) {
        this.id_konsumsi = id_konsumsi;
    }

    public String getId_konsumen() {
        return id_konsumen;
    }

    public void setId_konsumen(String id_konsumen) {
        this.id_konsumen = id_konsumen;
    }

    public String getId_kopi() {
        return id_kopi;
    }

    public void setId_kopi(String id_kopi) {
        this.id_kopi = id_kopi;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getKafein() {
        return kafein;
    }

    public void setKafein(String kafein) {
        this.kafein = kafein;
    }

    public String getGlukosa() {
        return glukosa;
    }

    public void setGlukosa(String glukosa) {
        this.glukosa = glukosa;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getBahan_tambahan() {
        return bahan_tambahan;
    }

    public void setBahan_tambahan(String bahan_tambahan) {
        this.bahan_tambahan = bahan_tambahan;
    }
}

