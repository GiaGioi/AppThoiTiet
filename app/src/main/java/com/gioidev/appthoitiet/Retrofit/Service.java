package com.gioidev.appthoitiet.Retrofit;

import com.gioidev.appthoitiet.Model.Temp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    //    get dữ liệu detail
    @GET("/data/2.5/forecast/daily")
    Call<String> getCurrentWeatherData(@Query("q") String q, @Query("APPID") String app_id);


//    @GET("data/2.5/forecast/daily?")
//    Call<Temp> getTamp(@Query("id") String id, @Query("APPID") String app_id);

    @GET("data/2.5/forecast/daily?")
    Call<Temp> getAllWeather(@Query("id") String id, @Query("APPID") String app_id);


}
