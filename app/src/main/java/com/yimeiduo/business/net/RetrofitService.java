package com.yimeiduo.business.net;


import com.yimeiduo.business.mvvm.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("data/cityinfo/101210101.html")
    Call<WeatherData> queryWeather();

}
