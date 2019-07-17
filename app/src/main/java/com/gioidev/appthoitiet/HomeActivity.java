package com.gioidev.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

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
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "729e7343363e8c0d477bf76416e89116";
    public static String lat = "35";
    public static String lon = "139";
    private EditText tvCountry;
    private TextView tvDoCountry;
    private TextView tvNhietdocaonhat,tvclear;
    private TextView tvNhietdothapnhat;
    private ImageView btFind;

    interface Callback {
        void getString(String str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        tvDoCountry = findViewById(R.id.tvDoCountry);
        tvNhietdocaonhat = findViewById(R.id.tvNhietdocaonhat);
        tvNhietdothapnhat = findViewById(R.id.tvNhietdothapnhat);
        btFind = findViewById(R.id.btFind);
        tvclear =findViewById(R.id.tvClear);


        tvDoCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
            }
        });
        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //find_weather();
                tvCountry = findViewById(R.id.tvCountry);

                String cName = tvCountry.getText().toString();

                String content;
                Myweather  myweather = new Myweather();
                try {
                    content =  myweather.execute("https://openweathermap.org/data/2.5/weather?q=" +
                            cName + "&appid=b6907d289e10d714a6e88b30761fae22").get();
                    Log.e("content", content);

                    //JSON
                    JSONObject jsonObject = new JSONObject(content);
                    String weather = jsonObject.getString("weather");
                    String mainTemperature = jsonObject.getString("main");
                    double visibility;

                    Log.e("Weather", weather );
                    //Weather Array
                    JSONArray jsonArray = new JSONArray(weather);

                    String main ="";
                    String city = "";
                    String temperature = "";
                    String tempMin = "";
                    String temp_Max="";

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        main = jsonObject1.getString("main");

                    }
                    JSONObject mainPart = new JSONObject(mainTemperature);
                    temperature = mainPart.getInt("temp") + "°";

                    JSONObject temp_Min = new JSONObject(mainTemperature);
                    tempMin = temp_Min.getDouble("temp_min") + "°C";

                    JSONObject temp_max = new JSONObject(mainTemperature);
                    temp_Max = temp_max.getDouble("temp_max") + "°C";

                    Log.e("Main: ", main );

                    tvclear.setText(main);
                    tvDoCountry.setText(temperature);
                    tvNhietdothapnhat.setText(tempMin);
                    tvNhietdocaonhat.setText(temp_Max);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class Myweather extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStreamReader streamReader = new
                        InputStreamReader(httpURLConnection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                String inputLine = "";
                String result = "";
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}

