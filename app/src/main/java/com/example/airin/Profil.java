package com.example.airin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profil extends AppCompatActivity {
    LinearLayout lihatprofile, btnLogout;
    TextView textvie21, textvie22;
    private UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        lihatprofile = (LinearLayout) findViewById(R.id.lihatprofile);
        textvie21 = findViewById(R.id.textvie21);
        textvie22 = findViewById(R.id.textvie22);
        btnLogout = findViewById(R.id.btnlogout);
        userModel = (UserModel) getIntent().getSerializableExtra("userModel");

        String nama= userModel.getNama();
        String username= userModel.getUsername();
        textvie21.setText(nama);
        textvie22.setText(username);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        });

        lihatprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukalihatprofile = new Intent(getApplicationContext(), LihatProfil.class);
                bukalihatprofile.putExtra("userModel",userModel);
                startActivity(bukalihatprofile);
            }
        });
    }
    public void logoutOnClick(View view) {
        // Tambahkan logika logout di sini
        // Misalnya, kembalikan ke halaman login atau lakukan sesuatu yang sesuai dengan kebutuhan aplikasi Anda
        Intent intent = new Intent(Profil.this, SignIn.class);
        startActivity(intent);
        finish(); // Tutup halaman profil setelah logout
    }

}