package com.example.andresarango.apptry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andresarango on 10/28/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter{

    private List<com.example.andresarango
            .apptry.network.com.example
            .andresarango.apptry.network
            .weather_pojo.com.List> otherList = new ArrayList<>();

    public void setWeatherList(List<com.example.andresarango.apptry.network.com.example.andresarango.apptry.network.weather_pojo.com.List> otherList) {
        this.otherList.clear();
        this.otherList.addAll(otherList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WeatherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_view_holder,
                parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((WeatherViewHolder) holder).onBind(otherList.get(position));
    }

    @Override
    public int getItemCount() {
        return otherList.size();
    }
}
