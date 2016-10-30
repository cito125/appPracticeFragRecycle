package com.example.andresarango.apptry;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andresarango.apptry.network.com.example.andresarango.apptry.network.weather_pojo.com.List;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * Created by andresarango on 10/29/16.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private static final String SWAG = "YEEEEERRRR";
    private LinearLayout viewHold;
    private TextView weather_description;
    private TextView windSpeed;
    private TextView date;
    private TextView temp;
    private ImageView weatherpic;
    public static final String URL_FOR_PICTURE_KEY = "pic";
    private String URL_PIC = "";
    public WeatherViewHolder(View itemView) {
        super(itemView);
        weather_description = (TextView) itemView.findViewById(R.id.weather_description);
        windSpeed = (TextView) itemView.findViewById(R.id.windSpeed);
        date = (TextView) itemView.findViewById(R.id.date);
        temp = (TextView) itemView.findViewById(R.id.temp);
        weatherpic = (ImageView) itemView.findViewById(R.id.weather_pic);
        viewHold = (LinearLayout) itemView.findViewById(R.id.weather_view_hold);
        viewHold.setBackgroundColor(getRandomColor());
    }

    private View.OnClickListener whenClicked(final View itemView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(),OtherActivity.class);
//                intent.putExtra(URL_FOR_PICTURE_KEY,URL_PIC);
//                Log.d(SWAG, URL_PIC);
//                view.getContext().startActivity(intent);
                Fragment weatherFrag = new WeatherFragment();
                Bundle bund = new Bundle();
                bund.putString(URL_FOR_PICTURE_KEY,URL_PIC);
                weatherFrag.setArguments(bund);
                ((Activity) itemView.getContext()).getFragmentManager().
                        beginTransaction().
                        add(R.id.activity_main, weatherFrag).
                        commit();
            }
        };
    }

    /*
    *Fragment weatherFrag = new WeatherFragment();
                Bundle bund = new Bundle();
                bund.putString(URL_FOR_PICTURE_KEY,URL_PIC);
                weatherFrag.setArguments(bund);
                ((Activity) itemView.getContext()).getFragmentManager().
                        beginTransaction().
                        add(R.id.weather_frag, weatherFrag).
                        commit();
     *  return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EmployeeView.class);
                intent.putExtra(EmployeeView.EMPLOYEE_NAME, name);
                view.getContext().startActivity(intent);

            }
        };
    * */

    public void onBind(List list) {
        viewHold.setOnClickListener(whenClicked(itemView));
        weather_description.setText("Weather Description: "+list.getWeather().get(0).getDescription());
        windSpeed.setText("Wind Speed: " + Float.toString(list.getWind().getSpeed()));
        date.setText("date: " + list.getDtTxt().substring(0,11));
        temp.setText("temperature: " + convertKelvinToFahrenheit(list.getMain().getTemp()) + " Farrenheit");
        //http://openweathermap.org/img/w/10d.png
        URL_PIC = "http://openweathermap.org/img/w/" +
                list.getWeather().get(0).getIcon()+ ".png";
        Picasso.with(itemView.getContext())
                .load(URL_PIC)
                .placeholder(R.drawable.ic_action_weather)
                .resizeDimen(R.dimen.pic_dimens, R.dimen.pic_dimens)
                .centerInside()
                .into(weatherpic);
    }


    private int getRandomColor(){
        Random rand = new Random();
        int alpha = rand.nextInt(256);
        int red = rand.nextInt(256);
        int blue = rand.nextInt(256);
        int green = rand.nextInt(256);

        return (new Color()).argb(alpha,red,blue,green);

    }

    private String convertKelvinToFahrenheit(double temp){
        temp = temp * 1.8  - 459.67;
        return Double.toString(temp);
    }


}
