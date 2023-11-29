package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class KonfirmasiPreviousPassword extends AppCompatActivity {
    LinearLayout konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_previous_password);
        konfirmasi = (LinearLayout) findViewById(R.id.konfirmasi);

        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukapasswordbaru = new Intent(getApplicationContext(), KonfirmasiNewPassword.class);
                startActivity(bukapasswordbaru);
            }
        });

    }
}