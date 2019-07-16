package com.gioidev.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gioidev.appthoitiet.Retrofit.Client;
import com.gioidev.appthoitiet.Retrofit.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "2e65127e909e178d0af311a81f39948c";
    public static String lat = "35";
    public static String lon = "139";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Service service = Client.getClient("").create(Service.class);
        Call<WeatherRepone> call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback<WeatherRepone>() {
            @Override
            public void onResponse(Call<WeatherRepone> call, Response<WeatherRepone> response) {

            }

            @Override
            public void onFailure(Call<WeatherRepone> call, Throwable t) {

            }
        });
    }
}
