package com.example.airin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button btnSignIn;
    TextView tvdaftar;

    UserModel userModel;
    String username, password;
    UserDataListener listener;

    public interface UserDataListener {
        void onUserDataReceived(UserModel userModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        btnSignIn = findViewById(R.id.btnsignin);
        tvdaftar = findViewById(R.id.tvdaftar);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getuser();

            }
        });
        listener = new UserDataListener() {
            @Override
            public void onUserDataReceived(UserModel userModel) {
                // Callback saat data pengguna diterima
                SignIn.this.userModel = userModel;
            }
        };
//
        tvdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, Registrasi.class);
                startActivity(intent);
            }
        });
//
    }

    private void getuser() {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        cekUser();
    }

    private void cekUser() {
        String urlCekUser = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/verifLoginMobile?username=" +username+ "&password="+password;
        StringRequest sr = new StringRequest(Request.Method.GET, urlCekUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    JSONObject jsonResponse = jsonArray.getJSONObject(0);

                    String userId = jsonResponse.getString("_id");
                    String email = jsonResponse.getString("email");
                    String nama = jsonResponse.getString("nama");
                    String username = jsonResponse.getString("username");
                    String password = jsonResponse.getString("password");
                    String tb = jsonResponse.getString("tinggi_badan");
                    String bb = jsonResponse.getString("berat_badan");
                    String nomor = jsonResponse.getString("nomor_hp");
                    String usia = jsonResponse.getString("usia");
                    String rPenyakit = jsonResponse.getString("riwayat_penyakit");
                    String sKehamilan = jsonResponse.getString("status_kehamilan");
                    String jKelamin = jsonResponse.getString("jenis_kelamin");
                    String bataskafein = jsonResponse.getString("batas_konsumsi_glukosa");
                    String batasglukosa = jsonResponse.getString("batas_konsumsi_kafein");

                    userModel = new UserModel(userId,username,email,password,nama, nomor, usia, jKelamin,tb,bb, sKehamilan,rPenyakit, batasglukosa,bataskafein);

                    Log.d("User Model", "onResponse: "+ userModel);

                    listener.onUserDataReceived(userModel);

                    dataDiterima();
                    if (dataDiterima()) {
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        intent.putExtra("userModel", userModel);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignIn.this, "Akun belum terdaftar", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignIn.this, "Terdapat kesalahan server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }

    private boolean dataDiterima() {
        return userModel!= null;
    }
}
