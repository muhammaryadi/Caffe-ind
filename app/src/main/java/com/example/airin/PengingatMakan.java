package com.example.airin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PengingatMakan extends AppCompatActivity {
    Button btnsudahmakan, btnbelummakan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengingat_makan);
        btnsudahmakan = (Button) findViewById(R.id.btnsudahmakan);
        btnbelummakan = (Button) findViewById(R.id.btnbelummakan);

        btnsudahmakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukapilihdetailkopi = new Intent(getApplicationContext(), Kopi.class);
                UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
                bukapilihdetailkopi.putExtra("userModel", userModel);
                startActivity(bukapilihdetailkopi);
            }
        });

        btnbelummakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembalimainactivity = new Intent(getApplicationContext(), MainActivity.class);
                UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
                kembalimainactivity.putExtra("userModel", userModel);
                startActivity(kembalimainactivity);
            }
        });
    }
}