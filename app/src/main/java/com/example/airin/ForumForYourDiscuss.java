package com.example.airin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ForumForYourDiscuss extends AppCompatActivity implements TopikAdapter.TopikAdapterListener {

    private RecyclerView recyclerView;
    private TopikAdapter topikAdapter;
    private Button buttonbacktohometopik;
    private List<TopikModel> topikList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_for_your_discuss);

        // Check if ActionBar is not null before hiding it
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.recyclerViewTopik);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi topikList
        topikList = new ArrayList<>();

        // Back button
        buttonbacktohometopik = findViewById(R.id.buttonbacktohometopik);

        // Menambahkan listener klik untuk tombol back
        buttonbacktohometopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForumForYourDiscuss.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set up the adapter
        topikAdapter = new TopikAdapter(topikList, this);

        // Set the listener
        topikAdapter.setListener(this);

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(topikAdapter);

        // Panggil AsyncTask untuk mendapatkan data dari API
        new FetchDataTask().execute();
    }

    // Implementasi metode dari TopikAdapterListener
    @Override
    public void onReplyButtonClick(String idTopik, String idPengirim, String nama, String pesanTopik, String waktu) {
        // Implementasi aksi yang ingin Anda lakukan saat tombol "Reply" diklik
        // Contoh: membuka layar detil topik atau menanggapi topik
        // ...
    }

    private class FetchDataTask extends AsyncTask<Void, Void, List<TopikModel>> {

        @Override
        protected List<TopikModel> doInBackground(Void... voids) {
            List<TopikModel> topikList = new ArrayList<>();

            try {
                // URL endpoint API
                URL url = new URL("https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/getAllTopikbyIdPengirimMobile") ;

                // Buka koneksi HTTP
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    // Baca data dari InputStream
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON
                    topikList = parseJson(response.toString());
                } finally {
                    // Tutup koneksi setelah selesai
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                Log.e("TopikActivity", "Error in doInBackground: " + e.getMessage());
            }

            return topikList;
        }

        @Override
        protected void onPostExecute(List<TopikModel> topikList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Tampilkan data dalam RecyclerView setelah selesai
                    if (topikList != null && topikList.size() > 0) {
                        showDataInRecyclerView(topikList);
                    } else {
                        Log.e("TopikActivity", "No data received from API.");
                    }
                }
            });
        }

        private List<TopikModel> parseJson(String json) {
            List<TopikModel> topikList = new ArrayList<>();

            try {
                // Array JSON tidak memerlukan objek JSON
                JSONArray topikArray = new JSONArray(json);

                // Iterasi melalui array topik
                for (int i = 0; i < topikArray.length(); i++) {
                    JSONObject topikObject = topikArray.getJSONObject(i);

                    // Pastikan kunci JSON sesuai dengan respons API
                    String idTopik = topikObject.getString("_id");
                    String idPengirim = topikObject.getString("id_pengirim");
                    String nama = topikObject.getString("nama");
                    String pesanTopik = topikObject.getString("pesan_topik");
                    String waktu = topikObject.getString("waktu");

                    // Buat objek TopikModel dan tambahkan ke daftar
                    TopikModel topikModel = new TopikModel(idTopik, idPengirim, nama, pesanTopik, waktu);
                    topikList.add(topikModel);
                }
            } catch (JSONException e) {
                Log.e("TopikActivity", "Error parsing JSON: " + e.getMessage());
            }

            return topikList;
        }
    }

    private void showDataInRecyclerView(List<TopikModel> topikList) {
        if (topikAdapter == null) {
            // Inisialisasi dan atur adapter untuk RecyclerView hanya jika belum ada
            topikAdapter = new TopikAdapter(topikList, this);
            recyclerView.setAdapter(topikAdapter);
        } else {
            // Jika adapter sudah ada, cukup perbarui data di dalamnya
            topikAdapter.setTopikList(topikList);
            topikAdapter.notifyDataSetChanged();
        }
    }
}
