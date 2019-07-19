package com.gioidev.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gioidev.appthoitiet.Model.Temp;
import com.gioidev.appthoitiet.Model.ThoiTiet;
import com.gioidev.appthoitiet.Recycleview.WeatherAdapter;
import com.gioidev.appthoitiet.Retrofit.Client;
import com.gioidev.appthoitiet.Retrofit.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
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
    ArrayList<ThoiTiet> thoiTiets;
    //    public static String url = "";
    public static String AppId = "b6907d289e10d714a6e88b30761fae22";
    public static String q = "London";
    public static String id = "524901";
    private WeatherAdapter adapter;
    public String TAG = "dulieu";
    String city2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Intent intent = getIntent();
        String city = intent.getStringExtra("name");
        Log.e(TAG, "Du lieu truyen qua: " + city);
        if (city2.equals("")) {
            city2 = "London";
            getDataWeather(city2);
        } else {
            city2 = city;
        }
        thoiTiets = new ArrayList<>();
        adapter = new WeatherAdapter(MainActivity.this,thoiTiets);
        rvDetail.setHasFixedSize(true);
        rvDetail.setAdapter(adapter);
        rvDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

    }
    public void init() {
        tvfeel = findViewById(R.id.tvfeel);
        tvnhietdo = findViewById(R.id.tvnhietdo);
        tvwind = findViewById(R.id.tvwind);
        tocdo = findViewById(R.id.tocdo);
        Humidity = findViewById(R.id.Humidity);
        Pressure = findViewById(R.id.Pressure);
        tvVisilibity = findViewById(R.id.tvVisilibity);
        Visilibity = findViewById(R.id.Visilibity);
        tvDewpoint = findViewById(R.id.tvDewpoint);
        Dewpoint = findViewById(R.id.Dewpoint);
        rvDetail = findViewById(R.id.rvDetail);

    }

    public void getDataWeather(String data) {
        String url = "https://openweathermap.org/data/2.5/forecast/daily?q="+data+"&appid=b6907d289e10d714a6e88b30761fae22";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response );
                try {
                    JSONObject jsonObject  = new JSONObject(response);
                    JSONObject jsonObjectCity = jsonObject.getJSONObject("city");


                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObjectList = jsonArray.getJSONObject(i);

                        String ngay = jsonObjectList.getString("dt");
                        long l = Long.valueOf(ngay);
                        Date date = new Date(l*1000L);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                        String Day = simpleDateFormat.format(date);

                        JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("temp");
                        String max = jsonObjectTemp.getString("max");
                        String min = jsonObjectTemp.getString("min");
                        String humidity = jsonObjectList.getString("humidity");
                        String speed = jsonObjectList.getString("speed");
                        String pressure = jsonObjectList.getString("pressure");
                        String vibision = jsonObjectCity.getString("country");
                        Log.e(TAG, "onResponse: " + max );

                        Double a = Double.valueOf(max);
                        Double b = Double.valueOf(min);
                        String nhietdoMax = String.valueOf(a.intValue() + "°C");
                        String humidity2 = String.valueOf(b.intValue()+ "%");
                        String speed2 = String.valueOf(b.intValue()+ "m/s");
                        String pressure2 = String.valueOf(b.intValue()+ "in");
                        String nhietdoMin = String.valueOf(b.intValue()+ "°C");


                        JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                        String icon = jsonObjectWeather.getString("icon");

                        Humidity.setText(humidity2);
                        tvnhietdo.setText(nhietdoMax);
                        Dewpoint.setText(nhietdoMin);
                        tocdo.setText(speed2);
                        Pressure.setText(pressure2);
                        Visilibity.setText(vibision);

                        thoiTiets.add(new ThoiTiet(Day,humidity2,icon,nhietdoMax,nhietdoMin));
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
    public void retrofitList(String data){
        String url = "https://openweathermap.org";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        retrofit2.Call<String> call = service.getCurrentWeatherData(q,AppId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                try {
                    Log.e(TAG, "onResponse: " +response);
                    JSONObject jsonObject  = new JSONObject(String.valueOf(response));
                    JSONObject jsonObjectCity = jsonObject.getJSONObject("city");


                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObjectList = jsonArray.getJSONObject(i);

                        String ngay = jsonObjectList.getString("dt");
                        long l = Long.valueOf(ngay);
                        Date date = new Date((1*1000L)+1);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                        String Day = simpleDateFormat.format(date);

                        JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("temp");
                        String max = jsonObjectTemp.getString("max");
                        String min = jsonObjectTemp.getString("min");
                        String humidity = jsonObjectList.getString("humidity");
                        String speed = jsonObjectList.getString("speed");
                        String pressure = jsonObjectList.getString("pressure");
                        String vibision = jsonObjectCity.getString("country");
                        Log.e(TAG, "onResponse: " + max );

                        Double a = Double.valueOf(max);
                        Double b = Double.valueOf(min);
                        String nhietdoMax = String.valueOf(a.intValue() + "°C");
                        String humidity2 = String.valueOf(b.intValue()+ "%");
                        String speed2 = String.valueOf(b.intValue()+ "m/s");
                        String pressure2 = String.valueOf(b.intValue()+ "in");
                        String nhietdoMin = String.valueOf(b.intValue()+ "°C");


                        JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                        String icon = jsonObjectWeather.getString("icon");

                        Humidity.setText(humidity2);
                        tvnhietdo.setText(nhietdoMax);
                        Dewpoint.setText(nhietdoMin);
                        tocdo.setText(speed2);
                        Pressure.setText(pressure2);
                        Visilibity.setText(vibision);

                        thoiTiets.add(new ThoiTiet(Day,icon,humidity,nhietdoMax,nhietdoMin));
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Không có dữ liệu trả về", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
