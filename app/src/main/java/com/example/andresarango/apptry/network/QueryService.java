package com.example.andresarango.apptry.network;


import com.example.andresarango.apptry.network.com.example.andresarango.apptry.network.weather_pojo.com.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by andresarango on 10/28/16.
 */

//  http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=f860d9dbfba0c35d805c7987da84e007
public interface QueryService {
    @Headers({
            "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
            "Accept-Encoding:gzip, deflate, sdch",
            "Accept-Language:en-US,en;q=0.8",
            "Cache-Control:max-age=0",
            "Connection:keep-alive",
            "Cookie:__utma=124807636.1450849991.1477692693.1477712054.1477692693.5; __utmc=124807636; __utmz=124807636.1477695025.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided)",
            "Host:api.openweathermap.org",
            "Upgrade-Insecure-Requests:1",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"
    })
    @GET("/data/2.5/forecast")
    Call<Forecast> getFiveDayForecast(
            @Query("id") int id,
            @Query("APPID") String apikey
    );
}
