package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class InputVerifikasiLupaPassword extends AppCompatActivity {
    LinearLayout konfirmasilupapw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_verifikasi_lupa_password);
        konfirmasilupapw = (LinearLayout) findViewById(R.id.konfirmasilupapw);

        konfirmasilupapw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukasuksesverifikasilupapw = new Intent(getApplicationContext(),SuksesVerifLupaPassword.class);
                startActivity(bukasuksesverifikasilupapw);
            }
        });
    }
}