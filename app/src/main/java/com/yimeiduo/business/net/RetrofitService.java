package com.yimeiduo.business.net;


import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.NewsDetail;
import com.yimeiduo.business.mvvm.model.WeatherData;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitService {

    @GET("data/cityinfo/101210101.html")
    Call<WeatherData> queryWeather();

    // 获取新闻详情
    @GET
    Observable<CommonResponse<NewsDetail>> getNewsDetail(@Url String url);

}
