package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class EditProfil extends AppCompatActivity {
    LinearLayout perbarui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        perbarui = (LinearLayout) findViewById(R.id.perbarui);

        perbarui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukasukseseditprofil = new Intent(getApplicationContext(), SuksesEditProfil.class);
                startActivity(bukasukseseditprofil);
            }
        });
    }
}