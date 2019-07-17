package com.gioidev.appthoitiet.Recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gioidev.appthoitiet.R;
import com.gioidev.appthoitiet.WeatherResponse;

import java.util.List;

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder> {

    private int dataList;
    private Context context;

    public WeatherAdapter(int dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

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

    }

    @Override
    public int getItemCount() {
        return (int) Integer.parseInt(String.valueOf(dataList));
    }
}
