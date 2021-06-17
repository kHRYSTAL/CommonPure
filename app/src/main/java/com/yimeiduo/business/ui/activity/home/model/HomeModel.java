package com.yimeiduo.business.ui.activity.home.model;

import com.yimeiduo.business.base.BaseModel;
import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.NewsDetail;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel<NewsDetail> {

    public static final String HOME_KEY = "home_key";

    public Observable<CommonResponse<NewsDetail>> getNewsDetail(String url) {
        return mApiService.getNewsDetail(url)
                .observeOn(Schedulers.io()).doOnNext(v -> {
            // TODO 设置缓存示例
            setCache(HOME_KEY, v.getData());
        });
    }
}
