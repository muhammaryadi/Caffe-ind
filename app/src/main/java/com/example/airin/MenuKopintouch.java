package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuKopintouch extends AppCompatActivity {
    Button backhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kopintouch);
        backhome = (Button) findViewById(R.id.backhome);

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembalikehome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(kembalikehome);
            }
        });

    }
}