package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.BufferUnderflowException;

public class SuksesGantiPassword extends AppCompatActivity {
    Button btnbacksettingsuksesganti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_ganti_password);
        btnbacksettingsuksesganti = findViewById(R.id.btnbacksettingsuksesganti);

        btnbacksettingsuksesganti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtosetting = new Intent(getApplicationContext(), MenuPengaturanAkun.class);
                startActivity(backtosetting);
            }
        });
    }
}