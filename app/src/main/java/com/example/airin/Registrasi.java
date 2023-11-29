package com.example.airin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registrasi extends AppCompatActivity {

    EditText etUsernameRegister, etPasswordRegister, etEmailRegister, etConfirmPassword;
    Button registerButton;
    TextView signInTextView;
    String username, email, password, cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        etEmailRegister = findViewById(R.id.etEmailRegister);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validasi konfirmasi password
                getUserMasuk();
                Intent intent = new Intent(Registrasi.this, InputDataUser1.class);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                intent.putExtra("password", password);

                Log.d("username regist", "onCreate: "+username);
                startActivity(intent);
            }
        });
    }

    private void getUserMasuk() {
        username = etUsernameRegister.getText().toString();
        email = etEmailRegister.getText().toString();
        password = etPasswordRegister.getText().toString();
        cpassword = etConfirmPassword.getText().toString();
        Log.d("usermasuK", "getUserMasuk: "+username);
    }

}
