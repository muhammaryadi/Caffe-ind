package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuksesEditProfil extends AppCompatActivity {
    Button btnbackprofilsukses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_edit_profil);
        btnbackprofilsukses = (Button) findViewById(R.id.btnbackprofilsukses);

        btnbackprofilsukses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backhomeprofilesukses = new Intent(getApplicationContext(), Profil.class);
                startActivity(backhomeprofilesukses);
            }
        });

    }
}