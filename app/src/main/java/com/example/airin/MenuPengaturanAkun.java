package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuPengaturanAkun extends AppCompatActivity {
    LinearLayout editprofil, lupapassword, gantipassword, hapusakun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pengaturan_akun);
        editprofil = (LinearLayout) findViewById(R.id.editprofil);
        lupapassword = (LinearLayout) findViewById(R.id.lupapassword);
        gantipassword = (LinearLayout) findViewById(R.id.gantipassword);
        hapusakun = (LinearLayout) findViewById(R.id.hapusakun);

        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukaeditprofil = new Intent(getApplicationContext(), EditProfil.class);
                startActivity(bukaeditprofil);
            }
        });

        gantipassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukagantipassword = new Intent(getApplicationContext(), KonfirmasiPreviousPassword.class);
                startActivity(bukagantipassword);
            }
        });

        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukalupapassword = new Intent(getApplicationContext(), InputVerifikasiLupaPassword.class);
                startActivity(bukalupapassword);
            }
        });

    }
}