package com.example.airin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.airin.MainActivity;
import com.example.airin.R;
import com.example.airin.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class InputDataUser1 extends AppCompatActivity {
    EditText etNama, etNomorHp, etUsia, etTinggiBadan, etBeratBadan;
    Spinner spinnerJenisKelamin, spinnerStatusKehamilan, spinnerRiwayatPenyakit;
    Button submitButton;
    String uname, email, pass, nama, nomor, usia, tb, bb, jKelamin, sKehamilan, rPenyakit;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_user1);

        // Inisialisasi elemen UI
        etNama = findViewById(R.id.etNama);
        etNomorHp = findViewById(R.id.etNomorHp);
        etUsia = findViewById(R.id.etUsia);
        etTinggiBadan = findViewById(R.id.etTinggiBadan);
        etBeratBadan = findViewById(R.id.etBeratBadan);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        spinnerStatusKehamilan = findViewById(R.id.spinnerStatusKehamilan);
        spinnerRiwayatPenyakit = findViewById(R.id.spinnerRiwayatPenyakit);
        submitButton = findViewById(R.id.buttonSubmitInputData);

        // Ambil data
        Intent intent = getIntent();
        uname = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("password");

        // Adapter untuk spinner
        ArrayAdapter<CharSequence> adapterJenisKelamin = ArrayAdapter.createFromResource(this, R.array.jenisKelamin,android.R.layout.simple_spinner_item);
        adapterJenisKelamin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(adapterJenisKelamin);

        ArrayAdapter<CharSequence> adapterStatusKehamilan = ArrayAdapter.createFromResource(this,R.array.statusKehamilan, android.R.layout.simple_spinner_item);
        adapterStatusKehamilan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusKehamilan.setAdapter(adapterStatusKehamilan);

        ArrayAdapter<CharSequence> adapterRiwayatPenyakit = ArrayAdapter.createFromResource(this, R.array.riwayatPenyakit, android.R.layout.simple_spinner_item);
        adapterRiwayatPenyakit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRiwayatPenyakit.setAdapter(adapterRiwayatPenyakit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilData();
                Intent keSignIn = new Intent(InputDataUser1.this, SignIn.class);
                startActivity(keSignIn);
            }

        });
    }

    private void ambilData() {
        nama = etNama.getText().toString();
        nomor = etNomorHp.getText().toString();
        usia = etUsia.getText().toString();
        tb = etTinggiBadan.getText().toString();
        bb = etBeratBadan.getText().toString();
        jKelamin = spinnerJenisKelamin.getSelectedItem().toString();
        sKehamilan = spinnerStatusKehamilan.getSelectedItem().toString();
        rPenyakit = spinnerRiwayatPenyakit.getSelectedItem().toString();

        tambahKonsumen();
    }

    private void tambahKonsumen() {
        String urlEndpoint = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/registrasiKonsumenMobile";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlEndpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(InputDataUser1.this,"input konsumen berhasil", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(InputDataUser1.this,"input gagal", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                return super.getPostParams();
            Map map = new HashMap();
            map.put("username",uname);
            map.put("password",pass);
            map.put("email",email);
            map.put("nama",nama);
            map.put("nomor_hp",nomor);
            map.put("usia",usia);
            map.put("jenis_kelamin",jKelamin);
            map.put("tinggi_badan",tb);
            map.put("berat_badan",bb);
            map.put("status_kehamilan",sKehamilan);
            map.put("riwayat_penyakit",rPenyakit);
            return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
