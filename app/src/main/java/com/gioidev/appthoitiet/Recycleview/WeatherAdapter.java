package com.gioidev.appthoitiet.Recycleview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.model.Model;
import com.gioidev.appthoitiet.Model.Temp;
import com.gioidev.appthoitiet.Model.Weather;
import com.gioidev.appthoitiet.R;
import com.gioidev.appthoitiet.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder> {

    private List<Temp> modelList;

    public WeatherAdapter(List<Temp> modelList) {
        this.modelList = modelList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView nhietdo;
        private TextView tvnhietdongay;
        private TextView tvnhietdodem;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nhietdo = mView.findViewById(R.id.nhietdo);
            tvnhietdongay = mView.findViewById(R.id.tvnhietdongay);
            tvnhietdodem = mView.findViewById(R.id.tvnhietdodem);

//            String url = "https://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=b1b15e88fa797225412429c1c50c122a1";
//            JSONObject jsonObject = null;
//            String tempMax = "";
//            try {
//                jsonObject = new JSONObject(url);
//                String weather = jsonObject.getString("weather");
//                String mainTemperature = jsonObject.getString("temp");
//
//                JSONObject mainPart = new JSONObject(mainTemperature);
//                tempMax = mainPart.getInt("min") + "°";
//
//                tvnhietdongay.setText(tempMax);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customcartview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
//            holder.nhietdo.setText(modelList.get(position).getDay());
        holder.tvnhietdongay.setText(modelList.get(position).getMax());
        String url = "https://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=b1b15e88fa797225412429c1c50c122a1";
        JSONObject jsonObject = null;
        String tempMax = "";
        try {
            jsonObject = new JSONObject(url);
            String weather = jsonObject.getString("weather");
            String mainTemperature = jsonObject.getString("temp");

            JSONObject mainPart = new JSONObject(mainTemperature);
            tempMax = mainPart.getInt("min") + "°";

            holder.tvnhietdongay.setText(tempMax);
            Log.e("Data", tempMax );


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return  modelList.size();
    }
}
