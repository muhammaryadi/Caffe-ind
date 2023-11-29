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

public class Kopi extends AppCompatActivity implements KopiAdapter.KopiAdapterListener {

    private RecyclerView recyclerView;
    private KopiAdapter kopiAdapter;
    private Button buttonbackhome;
    private List<KopiModel> kopiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi);

        // Check if ActionBar is not null before hiding it
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi kopiList
        kopiList = new ArrayList<>();

        //back
        Button buttonbackhome = findViewById(R.id.buttonbacktohome);

        // Menambahkan listener klik untuk tombol back
        buttonbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kopi.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set up the adapter
        kopiAdapter = new KopiAdapter(kopiList, this);

        // Set the listener
        kopiAdapter.setListener(this);

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(kopiAdapter);

        // Panggil AsyncTask untuk mendapatkan data dari API
        new FetchDataTask().execute();
    }

    //PERGI KE DETAIL INPUT KOPI

    public void goToDetailKopi(String idkopi, String namaKopi, String lessSugar, String normalSugar, String extraSugar, String bahanTambahan, String kafein, String lessGlukosa, String normalGlukosa, String extraGlukosa, String image) {
        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        KopiModel kopiModel = (KopiModel) getIntent().getSerializableExtra("kopiModel");
        Intent intent = new Intent(Kopi.this, InputDetailKopi.class);
        intent.putExtra("userModel", userModel);
        intent.putExtra("id_kopi", idkopi);
        intent.putExtra("resImg", image);
        intent.putExtra("lessSugar", lessSugar);
        intent.putExtra("normalSugar", normalSugar);
        intent.putExtra("extraSugar", extraSugar);
        intent.putExtra("bahanTambahan", bahanTambahan);
        intent.putExtra("kafein", kafein);
        intent.putExtra("lessGlukosa", lessGlukosa);
        intent.putExtra("normalGlukosa", normalGlukosa);
        intent.putExtra("extraGlukosa", extraGlukosa);
        startActivity(intent);
    }

    private class FetchDataTask extends AsyncTask<Void, Void, List<KopiModel>> {

        @Override
        protected List<KopiModel> doInBackground(Void... voids) {
            List<KopiModel> kopiList = new ArrayList<>();

            try {
                // URL endpoint API
                URL url = new URL("https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/getAllKopi");

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
                    kopiList = parseJson(response.toString());
                } finally {
                    // Tutup koneksi setelah selesai
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                Log.e("KopiActivity", "Error in doInBackground: " + e.getMessage());
            }

            return kopiList;
        }

        @Override
        protected void onPostExecute(List<KopiModel> kopiList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Tampilkan data dalam RecyclerView setelah selesai
                    if (kopiList != null && kopiList.size() > 0) {
                        showDataInRecyclerView(kopiList);
                    } else {
                        Log.e("KopiActivity", "No data received from API.");
                    }
                }
            });
        }

        private List<KopiModel> parseJson(String json) {
            List<KopiModel> kopiList = new ArrayList<>();

            try {
                // Array JSON tidak memerlukan objek JSON
                JSONArray kopiArray = new JSONArray(json);

                // Iterasi melalui array kopi
                for (int i = 0; i < kopiArray.length(); i++) {
                    JSONObject kopiObject = kopiArray.getJSONObject(i);

                    // Pastikan kunci JSON sesuai dengan respons API
                    String idKopi = kopiObject.getString("_id"); // sesuaikan dengan respons API
                    Log.d("KopiActivity", "ID Kopi: " + idKopi);
                    String foto = kopiObject.getString("foto"); // sesuaikan dengan respons API
                    String namaKopi = kopiObject.getString("nama");
                    String deskripsi = kopiObject.getString("deskripsi");
                    String serve = kopiObject.getString("penyajian");
                    String bijiKopi = kopiObject.getString("biji_kopi");
                    String penyeduhanKopi = kopiObject.getString("penyeduhan_kopi");
                    String kopi = kopiObject.getString("kopi");
                    String lessSugar = kopiObject.getString("less_sugar");
                    String normalSugar = kopiObject.getString("normal_sugar");
                    String extraSugar = kopiObject.getString("extra_sugar");
                    String bahanTambahan = kopiObject.getString("bahan_tambahan");
                    String kafein = kopiObject.getString("kafein");
                    String lessGlukosa = kopiObject.getString("less_glukosa");
                    String normalGlukosa = kopiObject.getString("normal_glukosa");
                    String extraGlukosa = kopiObject.getString("extra_glukosa");
                    // Buat objek KopiModel dan tambahkan ke daftar
                    KopiModel kopiModel = new KopiModel(idKopi, foto, namaKopi, deskripsi,serve,bijiKopi,penyeduhanKopi,kopi,lessSugar,normalSugar,extraSugar,bahanTambahan,kafein, lessGlukosa,normalGlukosa,extraGlukosa);
//                    KopiModel kopiModel = new KopiModel(idKopi, imageUrl, title, deskripsi);
                    kopiModel.setIdKopi(idKopi);
                    kopiModel.setNamaKopi(namaKopi);
                    kopiModel.setDeskripsiKopi(serve);
                    kopiModel.setFoto(foto);


                    kopiList.add(kopiModel);
                }
            } catch (JSONException e) {
                Log.e("KopiActivity", "Error parsing JSON: " + e.getMessage());
            }

            return kopiList;
        }
    }

    private void showDataInRecyclerView(List<KopiModel> kopiList) {
        if (kopiAdapter == null) {
            // Inisialisasi dan atur adapter untuk RecyclerView hanya jika belum ada
            kopiAdapter = new KopiAdapter(kopiList, this);
            recyclerView.setAdapter(kopiAdapter);
        } else {
            // Jika adapter sudah ada, cukup perbarui data di dalamnya
            kopiAdapter.setKopiList(kopiList);
            kopiAdapter.notifyDataSetChanged();
        }
    }

    public void goToDetail (View view){
        Intent i = new Intent(Kopi.this, InputDetailKopi.class);
        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        KopiModel kopiModel = (KopiModel) getIntent().getSerializableExtra("kopiModel");
        i.putExtra("userModel", userModel);
        startActivity(i);
    }
}
