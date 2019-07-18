package com.gioidev.appthoitiet.Model;

public class ThoiTiet {
    private String Day;
    private String Humidity;
    private String ImageView;
    private String Max;
    private String Min;

    public ThoiTiet(String day, String humidity, String imageView, String max, String min) {
        Day = day;
        Humidity = humidity;
        ImageView = imageView;
        Max = max;
        Min = min;
    }
}
