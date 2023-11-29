package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class KonfirmasiNewPassword extends AppCompatActivity {
    LinearLayout konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_new_password);
        konfirmasi = (LinearLayout) findViewById(R.id.konfirmasi);

        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukasuksespasswordbaru = new Intent(getApplicationContext(), SuksesGantiPassword.class);
                startActivity(bukasuksespasswordbaru);
            }
        });
    }
}