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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.gioidev.appthoitiet.HomeActivity;
import com.gioidev.appthoitiet.Model.Temp;
import com.gioidev.appthoitiet.Model.ThoiTiet;
import com.gioidev.appthoitiet.Model.Weather;
import com.gioidev.appthoitiet.R;
import com.gioidev.appthoitiet.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder> {


    private Context context;
    private ArrayList<ThoiTiet> modelList;
    public WeatherAdapter(Context context, ArrayList<ThoiTiet> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView nhietdo;
        private TextView tvnhietdongay;
        private TextView tvnhietdodem;
        private TextView day;
        private ImageView imageCloud;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nhietdo = mView.findViewById(R.id.nhietdo);
            tvnhietdongay = mView.findViewById(R.id.tvnhietdongay);
            tvnhietdodem = mView.findViewById(R.id.tvnhietdodem);
            day = mView.findViewById(R.id.day);
            imageCloud = mView.findViewById(R.id.imageCloud);


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
        ThoiTiet thoiTiet = modelList.get(position);

        holder.day.setText(thoiTiet.getDay());
        holder.nhietdo.setText(thoiTiet.getHumidity());
        holder.tvnhietdongay.setText(thoiTiet.getMax());
        holder.tvnhietdodem.setText(thoiTiet.getMin());

        Glide.with(context).load("http://openweathermap.org/img/wn/"+thoiTiet.getImageView()+".png").into(holder.imageCloud);
    }

    @Override
    public int getItemCount() {
        return  modelList.size();
    }
}
