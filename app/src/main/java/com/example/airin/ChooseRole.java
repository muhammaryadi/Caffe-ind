package com.example.airin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        Button chooseKonsumenButton = findViewById(R.id.choosekonsumen);
        chooseKonsumenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click for Konsumen
                Intent signInIntent = new Intent(ChooseRole.this, SignIn.class);
                startActivity(signInIntent);
            }
        });
    }
}
