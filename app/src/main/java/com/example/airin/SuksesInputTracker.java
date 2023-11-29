package com.example.airin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuksesInputTracker extends AppCompatActivity {
    Button btnbackhomesukses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_input_tracker);
        btnbackhomesukses = (Button) findViewById(R.id.btnbackhomesukses);

        btnbackhomesukses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backhomeaftersukses = new Intent(SuksesInputTracker.this, MainActivity.class);
                UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
                backhomeaftersukses.putExtra("userModel", userModel);
                startActivity(backhomeaftersukses);
            }
        });
    }
}