package com.example.airin;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LihatProfil extends AppCompatActivity {

    private UserModel userModel;

    TextView tvNama;
    TextView tvUsername;
    TextView tvNomorHp;
    TextView tvUsia;
    TextView tvJenisKelamin;
    TextView tvTinggiBadan;
    TextView tvBeratBadan;
    TextView tvStatusKehamilan;
    TextView tvRiwayatPenyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_profil);

        // Mendapatkan objek UserModel dari intent
        userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        Log.d("MMT", "onCreate: "+userModel);

        // Inisialisasi semua TextView sesuai dengan ID yang sesuai
        tvNama = findViewById(R.id.tvnama);
        tvUsername = findViewById(R.id.tvusername);
        tvNomorHp = findViewById(R.id.tvnomorhp);
        tvUsia = findViewById(R.id.tvusia);
        tvJenisKelamin = findViewById(R.id.tvjk);
        tvTinggiBadan = findViewById(R.id.tvtb);
        tvBeratBadan = findViewById(R.id.tvbb);
        tvStatusKehamilan = findViewById(R.id.tvsk);
        tvRiwayatPenyakit = findViewById(R.id.tvrk);

        // Menetapkan nilai ke TextView sesuai dengan objek UserModel
        tvNama.setText(userModel.getNama());
        tvUsername.setText(userModel.getUsername());
        tvNomorHp.setText(userModel.getNomor_hp());
        tvUsia.setText(userModel.getUsia() );
        tvJenisKelamin.setText(userModel.getJenis_kelamin());
        tvTinggiBadan.setText(userModel.getTinggi_badan() );
        tvBeratBadan.setText(userModel.getBerat_badan());
        tvStatusKehamilan.setText(userModel.getStatus_kehamilan());
        tvRiwayatPenyakit.setText(userModel.getRiwayat_penyakit());
    }
}
