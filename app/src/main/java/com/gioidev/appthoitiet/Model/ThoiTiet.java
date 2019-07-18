package com.gioidev.appthoitiet.Model;

import android.widget.ImageView;

public class ThoiTiet {
    private String Day;
    private String Humidity;
    private String ImageView;
    private String Max;
    private String Min;

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getImageView() {
        return ImageView;
    }

    public void setImageView(String imageView) {
        ImageView = imageView;
    }

    public String getMax() {
        return Max;
    }

    public void setMax(String max) {
        Max = max;
    }

    public String getMin() {
        return Min;
    }

    public void setMin(String min) {
        Min = min;
    }

    public ThoiTiet(String day, String humidity, String imageView, String max, String min) {
        Day = day;
        Humidity = humidity;
        ImageView = imageView;
        Max = max;
        Min = min;
    }
}
