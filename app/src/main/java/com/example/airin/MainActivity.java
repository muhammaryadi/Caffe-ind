package com.example.airin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    LinearLayout catatkonsumsi, lihatmenu, summary, profile, forum;
    TextView textview1, textview2, textview3, textview4;

    private UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catatkonsumsi = (LinearLayout) findViewById(R.id.catatkonsumsi);
        lihatmenu = (LinearLayout) findViewById(R.id.lihatmenu);
        summary = (LinearLayout) findViewById(R.id.summary);
        profile = (LinearLayout) findViewById(R.id.profile);
        forum = (LinearLayout) findViewById(R.id.forum);

        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 =  (TextView) findViewById(R.id.textview3);
        textview4 = (TextView) findViewById(R.id.textview4);

        userModel = (UserModel) getIntent().getSerializableExtra("userModel");

        String nama= userModel.getNama();
        String batasKafein = userModel.getBatas_konsumsi_kafein();
        String batasGlukosa = userModel.getBatas_konsumsi_glukosa();
        textview1.setText(nama);
        textview2.setText("Hai "+ nama);
        textview3.setText("Batas Konsumsi Kafein: " + batasKafein + " mg");
        textview4.setText("Batas Konsumsi Glukosa: " + batasGlukosa + " g");


        catatkonsumsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukacatatkonsumsi = new Intent(getApplicationContext(), PengingatMakan.class);
                bukacatatkonsumsi.putExtra("userModel", userModel);
                startActivity(bukacatatkonsumsi);
            }
        });

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukaforum = new Intent(getApplicationContext(), ForumForYourDiscuss.class);
                bukaforum.putExtra("userModel", userModel);
                startActivity(bukaforum);
            }
        });


        lihatmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukalihatmenu = new Intent(getApplicationContext(), MenuKopintouch.class);
                bukalihatmenu.putExtra("userModel", userModel);
                startActivity(bukalihatmenu);
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukamenusummary = new Intent(getApplicationContext(), SummaryCaffeTracker.class);
                bukamenusummary.putExtra("userModel", userModel);
                startActivity(bukamenusummary);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukamenuprofile = new Intent(getApplicationContext(), Profil.class);
                bukamenuprofile.putExtra("userModel", userModel);
                startActivity(bukamenuprofile);
            }
        });
    }
    private class GetUserDataTask extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(String... params) {
            // Panggil endpoint MongoDB untuk mendapatkan data pengguna berdasarkan ID
            String userId = params[0];
            String endpoint = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/getKonsumenById?id=" + userId;

            // Implementasikan kode untuk koneksi ke endpoint dan mendapatkan data JSON
            try {
                URL url = new URL(endpoint);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Membaca respons dari API
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    // Parse respons JSON
                    return new JSONArray(response.toString());
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(JSONArray result) {
            if (result != null && result.length() > 0) {
                try {
                    // Ambil data dari JSON dan atur ke TextView sesuai kebutuhan
                    JSONObject userData = result.getJSONObject(0);
                    String nama = userData.getString("nama");

                    textview1.setText(nama);
                    textview2.setText("Hai "+ nama);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Tangani kesalahan jika respons null atau array kosong
                Log.e("GetUserDataTask", "Response is null or empty");
                // atau tampilkan pesan Toast
                // Toast.makeText(Profil.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}