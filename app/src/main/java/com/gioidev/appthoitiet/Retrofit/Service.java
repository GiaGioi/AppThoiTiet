package com.gioidev.appthoitiet.Retrofit;

import com.gioidev.appthoitiet.WeatherRepone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    //    get dữ liệu detail
    @GET("data/2.5/weather?")
    Call<WeatherRepone> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

}
