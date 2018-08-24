package com.example.half_bloodprince.trebble;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrieveFeedTask().execute();

    }
}




class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {

        String url = "https://apis.paralleldots.com/v3/sentiment";
        OkHttpClient client = new OkHttpClient();
        MediaType mediatype = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediatype, "");

        Request request = new Request.Builder()
                .url(url+"?api_key=e31pwRIFQk10DisnU39MF7xGMd4t4eUZXxMEaGhY2yQ&text=Come on, lets play together&lang_code=en")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            Log.d("tag", "onCreate: "+response.message());
            Log.d("tag", "onCreate: "+response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
