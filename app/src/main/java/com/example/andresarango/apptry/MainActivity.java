package com.example.andresarango.apptry;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.andresarango.apptry.network.QueryService;
import com.example.andresarango.apptry.network.com.example.andresarango.apptry.network.weather_pojo.com.Forecast;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=f860d9dbfba0c35d805c7987da84e007

public class MainActivity extends AppCompatActivity {
    public static final String API_KEY = "f860d9dbfba0c35d805c7987da84e007";
    private static final int NEWYORK_ID =5128638;
    private static final String CITYLISTJSON = "./app/src/main/assets/citylist.json";
    private static final String BASE_URL = "http://api.openweathermap.org";
    public static final String CITY_ID_PATH = "/data/2.5/forecast";
    private static final String SWAG = "yeeeeeeeerrr";
    private static Gson gson = new Gson();
    private RecyclerView weatherRecycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherRecycle = (RecyclerView) findViewById(R.id.weatherRecycle);
        weatherRecycle.setLayoutManager(new LinearLayoutManager(this));
        WeatherAdapter weatherAdapt = new WeatherAdapter();
        weatherRecycle.setAdapter(weatherAdapt);
        QueryService bang = getPojo(BASE_URL,QueryService.class);
        runServeMesThread(bang.getFiveDayForecast(NEWYORK_ID,API_KEY),this,weatherAdapt);
    }

    private static <T> void runServeMesThread(Call<Forecast> call, final Context context, final WeatherAdapter weathadapt){
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if(response.isSuccessful()) {
                    weathadapt.setWeatherList(response.body().getList());
                    Log.d(SWAG, response.body().getCity().getName());
                    Log.d(SWAG, "YOU MADE IT");
                }else{
                    try {
                        Log.d(SWAG, "response is unsuccessful");
                        Log.d(SWAG,response.errorBody().string());
                    } catch (IOException e) {
                        // do nothing
                        Log.d(SWAG, "IO");
                        Log.d(SWAG, e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                // Something went completely wrong (like no internet connection)
                Log.d(SWAG, "Try Again ");
                Log.d(SWAG, String.valueOf(t));
            }
        });
    }

    private static <T> T getPojo(String base_url, Class <T> interfaceService){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(interfaceService);
    }
}
