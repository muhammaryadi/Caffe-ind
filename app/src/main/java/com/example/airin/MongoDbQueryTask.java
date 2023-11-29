package com.example.airin;
import android.os.AsyncTask;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MongoDbQueryTask extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        if (params.length < 2) {
            return false; // Invalid parameters
        }

        String endpoint = params[0];
        String query = params[1];

        try {
            OkHttpClient client = new OkHttpClient();

            // Ganti sesuai kebutuhan, ini contoh request ke MongoDB Realm HTTP Service
            String url = endpoint;
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, query);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            // Ganti ini sesuai dengan logika response dari MongoDB
            return response.isSuccessful();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

