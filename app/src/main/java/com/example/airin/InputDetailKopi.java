package com.example.airin;

//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import com.squareup.picasso.Picasso;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class InputDetailKopi extends AppCompatActivity {
//    Spinner spinnerKuantiti1, spinnerGula, spinnerBahan;
//    Button btnsubmitdatakopi;
//    String kuantiti, gula, bahan;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_input_detail_kopi);
//
//        btnsubmitdatakopi = findViewById(R.id.btnsubmitdatakopi);
//
//        spinnerKuantiti1 = findViewById(R.id.spinnerKuantiti1);
//        ArrayAdapter<CharSequence> adapterKuantiti = ArrayAdapter.createFromResource(this, R.array.kuantiti, android.R.layout.simple_spinner_item);
//        adapterKuantiti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerKuantiti1.setAdapter(adapterKuantiti);
//
//        spinnerGula = findViewById(R.id.spinnerGula);
//        ArrayAdapter<CharSequence> adapterGula = ArrayAdapter.createFromResource(this, R.array.sugar, android.R.layout.simple_spinner_item);
//        adapterGula.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerGula.setAdapter(adapterGula);
//
//        spinnerBahan = findViewById(R.id.spinnerBahan);
//        ArrayAdapter<CharSequence> adapterBahan = ArrayAdapter.createFromResource(this, R.array.bahan, android.R.layout.simple_spinner_item);
//        adapterBahan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerBahan.setAdapter(adapterBahan);
//
//        Intent intent = getIntent();
//        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
//
//        String idUser = userModel.getId();
//        String idKopi = intent.getStringExtra("id_kopi");
//        String namaKopi = intent.getStringExtra("namaKopi");
//
//        TextView nmKopi = findViewById(R.id.namaKopi);
//        ImageView imgKopi = findViewById(R.id.imgKopiDetail);
//        nmKopi.setText(namaKopi);
//        Picasso.get().load(intent.getStringExtra("resImg")).into(imgKopi);
//
//        Log.d("usr id", "onCreate: " + idUser);
//
//        btnsubmitdatakopi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getInput();
//            }
//        });
//    }
//
//    private void getInput() {
//        // Mendapatkan item yang dipilih dari spinner
//        Object selectedKuantiti = spinnerKuantiti1.getSelectedItem();
//        Object selectedGula = spinnerGula.getSelectedItem();
//        Object selectedBahan = spinnerBahan.getSelectedItem();
//
//        // Memeriksa apakah item yang dipilih tidak null sebelum mengonversi menjadi string
//        if (selectedKuantiti != null && selectedGula != null && selectedBahan != null) {
//            // Mengonversi item yang dipilih menjadi string
//            kuantiti = selectedKuantiti.toString();
//            gula = selectedGula.toString();
//            bahan = selectedBahan.toString();
//
//            // Melanjutkan dengan pemrosesan data
//            addKonsumsi();
//        } else {
//            // Menangani kasus jika nilai null ditemukan
//            Toast.makeText(this, "Mohon pilih nilai yang valid", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    private void addKonsumsi() {
//        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
//        String idKonsumen = userModel.getId();
//        String idKopi = getIntent().getStringExtra("id_kopi");
//        String quantity = spinnerKuantiti1.getSelectedItem().toString();
//        String sugar = spinnerGula.getSelectedItem().toString();
//        String bahanTambahan = spinnerBahan.getSelectedItem().toString();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String waktu = sdf.format(new Date());
//
//        new AddKonsumsiTask().execute(idKonsumen, idKopi, quantity, sugar, waktu, bahanTambahan);
//    }
//
//    private class AddKonsumsiTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            String idKonsumen = params[0];
//            String idKopi = params[1];
//            String quantity = params[2];
//            String sugar = params[3];
//            String waktu = params[4];
//            String bahanTambahan = params[5];
//
//            try {
//                URL url = new URL("https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/postKonsumsiKopi");
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                urlConnection.setDoOutput(true);
//
//                // Format data yang akan dikirim
//                String data = "id_konsumen=" + idKonsumen +
//                        "&id_kopi=" + idKopi +
//                        "&quantity=" + quantity +
//                        "&sugar=" + sugar +
//                        "&waktu=" + waktu +
//                        "&bahan_tambahan=" + bahanTambahan;
//
//                OutputStream outputStream = urlConnection.getOutputStream();
//                outputStream.write(data.getBytes());
//                outputStream.flush();
//
//                // Baca respons dari server
//                int responseCode = urlConnection.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    // Baca InputStream jika sukses
//                    // ...
//
//                    return "Success";  // Ubah ini sesuai respons yang diharapkan
//                } else {
//                    return "Error " + responseCode;
//                }
//            } catch (Exception e) {
//                return "Exception: " + e.getMessage();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            // Handle respons dari server sesuai kebutuhan Anda
//            Log.d("POST Response", result);
//
//            // Redirect ke halaman sukses jika diperlukan
//            Intent bukasuksesinputtracker = new Intent(getApplicationContext(), SuksesInputTracker.class);
//            startActivity(bukasuksesinputtracker);
//        }
//    }
//}

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputDetailKopi extends AppCompatActivity {
    Spinner spinnerKuantiti1, spinnerGula, spinnerBahan;
    Button btnsubmitdatakopi;
    String kuantiti, gula,bahan, lessGlukosa,normalGlukosa, extraGlukosa, kafein;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_detail_kopi);

        btnsubmitdatakopi = findViewById(R.id.btnsubmitdatakopi);

        spinnerKuantiti1 = findViewById(R.id.spinnerKuantiti1);
        ArrayAdapter<CharSequence> adapterKuantiti = ArrayAdapter.createFromResource(this, R.array.kuantiti, android.R.layout.simple_spinner_item);
        adapterKuantiti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKuantiti1.setAdapter(adapterKuantiti);

        spinnerGula = findViewById(R.id.spinnerGula);
        ArrayAdapter<CharSequence> adapterGula = ArrayAdapter.createFromResource(this, R.array.sugar, android.R.layout.simple_spinner_item);
        adapterGula.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGula.setAdapter(adapterGula);

        spinnerBahan = findViewById(R.id.spinnerBahan);
        ArrayAdapter<CharSequence> adapterBahan = ArrayAdapter.createFromResource(this, R.array.bahan, android.R.layout.simple_spinner_item);
        adapterBahan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBahan.setAdapter(adapterBahan);

        Intent intent = getIntent();
        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        KopiModel kopiModel = (KopiModel) getIntent().getSerializableExtra("kopiModel");
        String idUser = userModel.getId();
        String namaKopi = intent.getStringExtra("namaKopi");
        lessGlukosa = intent.getStringExtra("lessGlukosa");
        normalGlukosa = intent.getStringExtra("normalGlukosa");
        extraGlukosa = intent.getStringExtra("extraGlukosa");
        kafein = intent.getStringExtra("kafein");
        Log.d("MMT", "onCreate: "+lessGlukosa);
        Log.d("MMT", "onCreate: "+normalGlukosa);
        Log.d("MMT", "onCreate: "+kafein);
        Log.d("MMT", "onCreate: "+lessGlukosa);

        TextView nmKopi = findViewById(R.id.namaKopi);
        ImageView imgKopi = findViewById(R.id.imgKopiDetail);
        nmKopi.setText(namaKopi);
        Picasso.get().load(intent.getStringExtra("resImg")).into(imgKopi);

        Log.d("usr id", "onCreate: " + idUser);

        btnsubmitdatakopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
            }
        });
    }

    private void getInput() {
        // Mendapatkan item yang dipilih dari spinner
        Object selectedKuantiti = spinnerKuantiti1.getSelectedItem();
        Object selectedGula = spinnerGula.getSelectedItem();
        Object selectedBahan = spinnerBahan.getSelectedItem();

        // Memeriksa apakah item yang dipilih tidak null sebelum mengonversi menjadi string
        if (selectedKuantiti != null && selectedGula != null && selectedBahan != null) {
            // Mengonversi item yang dipilih menjadi string
            kuantiti = selectedKuantiti.toString();
            gula = selectedGula.toString();
            bahan = selectedBahan.toString();

            // Melanjutkan dengan pemrosesan data
            addKonsumsi();
        } else {
            // Menangani kasus jika nilai null ditemukan
            Toast.makeText(this, "Mohon pilih nilai yang valid", Toast.LENGTH_SHORT).show();
        }
    }


    private void addKonsumsi() {
        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        KopiModel kopiModel = (KopiModel) getIntent().getSerializableExtra("kopiModel");
        String idKonsumen = userModel.getId();
        String idKopi = getIntent().getStringExtra("id_kopi");
        String quantity = spinnerKuantiti1.getSelectedItem().toString();
        String sugar = spinnerGula.getSelectedItem().toString();
        String bahanTambahan = spinnerBahan.getSelectedItem().toString();

        // Logika untuk mendapatkan nilai awal kafein dan glukosa
        int jumlahkafein = 0;
        int glukosa = 0;

        switch (sugar) {
            case "Less Sugar":
                glukosa = Integer.parseInt(lessGlukosa);
                break;
            case "Normal Sugar":
                glukosa = Integer.parseInt(normalGlukosa);
                break;
            case "Extra Sugar":
                glukosa = Integer.parseInt(extraGlukosa);
                break;
            default:
                glukosa = 0;
                break;
        }

        jumlahkafein = Integer.parseInt(kafein);


        String[] optionalIngredients = bahanTambahan.split(", ");
        for (String ingredient : optionalIngredients) {
            switch (ingredient) {
                case "Cokelat":
                    // Adjust kafein for Cokelat
                    jumlahkafein += 20;
                    break;
                case "Matcha":
                    // Adjust kafein for Matcha
                    jumlahkafein += 60;
                    break;
                case "Ice Cream":
                    // Adjust glukosa for Ice Cream
                    glukosa += 10;
                    break;
            }
        }

        jumlahkafein *= Integer.parseInt(quantity);
        glukosa *= Integer.parseInt(quantity);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String waktu = sdf.format(new Date());

        new AddKonsumsiTask().execute(idKonsumen, idKopi, quantity, sugar, waktu, bahanTambahan, String.valueOf(jumlahkafein), String.valueOf(glukosa));
    }

    private class AddKonsumsiTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String idKonsumen = params[0];
            String idKopi = params[1];
            String quantity = params[2];
            String sugar = params[3];
            String waktu = params[4];
            String bahanTambahan = params[5];
            String jumlahkafein = params[6];
            String glukosa = params[7];

            try {
                URL url = new URL("https://us-east-1.aws.data.mongodb-api.com/app/application-0-yuxyg/endpoint/postKonsumsiKopi");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setDoOutput(true);

                // Format data yang akan dikirim
                String data = "id_konsumen=" + URLEncoder.encode(idKonsumen, "UTF-8") +
                        "&id_kopi=" + URLEncoder.encode(idKopi, "UTF-8") +
                        "&quantity=" + URLEncoder.encode(quantity, "UTF-8") +
                        "&sugar=" + URLEncoder.encode(sugar, "UTF-8") +
                        "&waktu=" + URLEncoder.encode(waktu, "UTF-8") +
                        "&bahan_tambahan=" + URLEncoder.encode(bahanTambahan, "UTF-8") +
                        "&kafein=" + URLEncoder.encode(jumlahkafein, "UTF-8") +
                        "&glukosa=" + URLEncoder.encode(glukosa, "UTF-8");

                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(data.getBytes());
                outputStream.flush();

                // Baca respons dari server
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Baca InputStream jika sukses
                    // ...

                    return "Success";  // Ubah ini sesuai respons yang diharapkan
                } else {
                    return "Error " + responseCode;
                }
            } catch (Exception e) {
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Handle respons dari server sesuai kebutuhan Anda
            Log.d("POST Response", result);

            // Redirect ke halaman sukses jika diperlukan
            Intent bukasuksesinputtracker = new Intent(InputDetailKopi.this, SuksesInputTracker.class);
            UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
            bukasuksesinputtracker.putExtra("userModel", userModel);
            startActivity(bukasuksesinputtracker);
        }
    }
}


