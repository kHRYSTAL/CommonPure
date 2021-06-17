package com.yimeiduo.business.ui.activity.home.model;

import com.yimeiduo.business.base.BaseModel;
import com.yimeiduo.business.base.DataListener;
import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.NewsDetail;
import com.yimeiduo.business.net.exception.BaseObserver;
import com.yimeiduo.business.net.exception.ExceptionHandle;
import com.yimeiduo.business.ui.activity.home.presenter.HomePresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel {

    public void getNewsDetail(HomePresenter homePresenter, String url, boolean isFromCache, boolean isNeedCache) {

        Observable<CommonResponse<NewsDetail>> observable = mApiService.getNewsDetail(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommonResponse<NewsDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        homePresenter.mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(CommonResponse<NewsDetail> data) {
                        homePresenter.onLoadSuccess(data.getData(),true);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException exception) {
                        homePresenter.onLoadError(ExceptionHandle.handleException(exception).getMessage(),ExceptionHandle.handleException(exception).getCode());
                    }
                });

    }

}
