package com.gioidev.appthoitiet.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Client {

    private static Retrofit retrofit=null;

    public static Retrofit getClient(String baseUrl){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(2000, TimeUnit.MILLISECONDS)
                .writeTimeout(2000, TimeUnit.MILLISECONDS)
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
