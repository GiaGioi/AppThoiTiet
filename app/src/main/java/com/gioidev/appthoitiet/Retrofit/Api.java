package com.gioidev.appthoitiet.Retrofit;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.google.gson.internal.bind.TypeAdapters.URL;

public class Api {

    private static Service mc2Service;
    public static final String Base_Url="http://api.openweathermap.org/data/2.5";

    public static Service getData(){
        return Client.getClient(Base_Url).create(Service.class);
    }
}

