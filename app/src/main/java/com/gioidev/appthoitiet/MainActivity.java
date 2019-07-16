package com.gioidev.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tvfeel;
    private TextView tvnhietdo;
    private TextView tvwind;
    private TextView tocdo;
    private TextView tvHumidity;
    private TextView Humidity;
    private TextView tvPressure;
    private TextView Pressure;
    private TextView tvVisilibity;
    private TextView Visilibity;
    private TextView tvDewpoint;
    private TextView Dewpoint;
    private RecyclerView rvDetail;

    interface Callback{
        void getString(String str);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Callback  callback = new Callback() {
            @Override
            public void getString(String str) {
                //TODO handle on the view
            }
        };
        new Myweather(callback).execute("link");

    }
    public void init(){
        tvfeel = findViewById(R.id.tvfeel);
        tvnhietdo = findViewById(R.id.tvnhietdo);
        tvwind = findViewById(R.id.tvwind);
        tocdo = findViewById(R.id.tocdo);
        tvHumidity = findViewById(R.id.tvHumidity);
        Humidity = findViewById(R.id.Humidity);
        tvPressure = findViewById(R.id.tvPressure);
        Pressure = findViewById(R.id.Pressure);
        tvVisilibity = findViewById(R.id.tvVisilibity);
        Visilibity = findViewById(R.id.Visilibity);
        tvDewpoint = findViewById(R.id.tvDewpoint);
        Dewpoint = findViewById(R.id.Dewpoint);
        rvDetail = findViewById(R.id.rvDetail);

    }
    public class Myweather extends AsyncTask<String,Void,Void>{
        Callback callback;
        public Myweather(Callback callback){
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder total = new StringBuilder();

                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }

                callback.getString(total.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
