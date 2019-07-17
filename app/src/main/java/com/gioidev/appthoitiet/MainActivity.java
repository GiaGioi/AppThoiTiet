package com.gioidev.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.gioidev.appthoitiet.Recycleview.WeatherAdapter;
import com.gioidev.appthoitiet.Retrofit.Client;
import com.gioidev.appthoitiet.Retrofit.Service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public static String BaseUrl = "https://api.openweathermap.org/";
    public static String AppId = "729e7343363e8c0d477bf76416e89116";
    public static String lat = "51.51";
    public static String lon = "-0.13";
    private WeatherAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDataWeather();

    }

    public void init() {
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

    public void getDataWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        retrofit2.Call<WeatherResponse> call = service.getCurrentWeatherData(lon,lat, AppId);
        call.enqueue(new retrofit2.Callback<WeatherResponse>() {
            @Override
            public void onResponse(retrofit2.Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    int nhietdo = (int) Float.parseFloat(String.valueOf(weatherResponse.wind.deg));
                    tvnhietdo.setText(String.valueOf(nhietdo)+"°F");
                    generateDataList(nhietdo);

                    int gio = (int) Float.parseFloat(String.valueOf(weatherResponse.wind.speed));
                    tocdo.setText(String.valueOf(gio)+"m/s");

                    String doam = weatherResponse.main.humidity + "%";
                    Humidity.setText(String.valueOf(doam));

                    String tocdo = weatherResponse.main.temp + "in";
                    Pressure.setText(String.valueOf(tocdo));

                    String nuoc = (weatherResponse.sys.country);
                    Visilibity.setText(String.valueOf(nuoc));

                    int point = (int) Float.parseFloat(String.valueOf(weatherResponse.wind.deg));
                    Dewpoint.setText(String.valueOf(point)+"°F");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Không có dữ liệu trả về", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(int list) {
        recyclerView = findViewById(R.id.rvDetail);
        adapter = new WeatherAdapter(list,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
