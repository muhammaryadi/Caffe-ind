package com.example.airin;

import java.io.Serializable;

public class UserModel implements Serializable {


    private String id;
    private String email;
    private String username;
    private String password;
    private String nama;
    private String jenis_kelamin;
    private String nomor_hp;
    private String profile_picture;
    private String riwayat_penyakit;
    private String berat_badan;
    private String tinggi_badan;
    private String status_kehamilan;
    private String usia;
    private String batas_konsumsi_kafein;
    private String batas_konsumsi_glukosa;


    public UserModel(String id, String username, String email, String password, String nama, String nomor_hp, String usia, String jenis_kelamin, String tinggi_badan, String berat_badan, String status_kehamilan, String riwayat_penyakit, String batas_konsumsi_kafein , String batas_konsumsi_glukosa) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.nomor_hp = nomor_hp;
        this.profile_picture = profile_picture;
        this.riwayat_penyakit = riwayat_penyakit;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.status_kehamilan= status_kehamilan;
        this.usia = usia;
        this.batas_konsumsi_kafein = batas_konsumsi_kafein;
        this.batas_konsumsi_glukosa = batas_konsumsi_glukosa;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getRiwayat_penyakit() {
        return riwayat_penyakit;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public String getTinggi_badan() {
        return tinggi_badan;
    }

    public String getStatus_kehamilan() {
        return status_kehamilan;
    }

    public String getUsia() {
        return usia;
    }

    public String getBatas_konsumsi_kafein() {
        return batas_konsumsi_kafein;
    }

    public String getBatas_konsumsi_glukosa() {
        return batas_konsumsi_glukosa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public void setRiwayat_penyakit(String riwayat_penyakit) {
        this.riwayat_penyakit = riwayat_penyakit;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public void setTinggi_badan(String tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public void setStatus_kehamilan(String status_kehamilan) {
        this.status_kehamilan = status_kehamilan;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public void setBatas_konsumsi_kafein(String batas_konsumsi_kafein) {
        this.batas_konsumsi_kafein = batas_konsumsi_kafein;
    }

    public void setBatas_konsumsi_glukosa(String batas_konsumsi_glukosa) {
        this.batas_konsumsi_glukosa = batas_konsumsi_glukosa;
    }
}
