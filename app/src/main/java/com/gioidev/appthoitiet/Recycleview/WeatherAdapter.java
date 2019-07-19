package com.gioidev.appthoitiet.Recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gioidev.appthoitiet.Model.ThoiTiet;
import com.gioidev.appthoitiet.R;

import java.util.ArrayList;

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
