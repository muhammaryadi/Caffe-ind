package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuksesVerifLupaPassword extends AppCompatActivity {
    Button btnbacksettingsukseslupa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_verif_lupa_password);
        btnbacksettingsukseslupa = (Button) findViewById(R.id.btnbacksettingsukseslupa);

        btnbacksettingsukseslupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtopengaturanakun = new Intent(getApplicationContext(), MenuPengaturanAkun.class);
                startActivity(backtopengaturanakun);
            }
        });
    }
}