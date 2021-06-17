package com.yimeiduo.business.base;

import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.NewsDetail;
import com.yimeiduo.business.net.RetrofitHelper;
import com.yimeiduo.business.net.RetrofitService;
import com.yimeiduo.business.net.exception.BaseObserver;
import com.yimeiduo.business.net.exception.ExceptionHandle;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseModel<T> implements DataModel{
    protected RetrofitService mApiService = RetrofitHelper.getInstance().getRetrofitService();

    @Override
    public void loadData(String url, DataListener l) {
    }

    @Override
    public void loadData(String url, DataListener l, boolean isFromCache) {
    }

    @Override
    public void loadData(String url, DataListener dataListener, boolean isFromCache, boolean isNeedCache) {
        Observable<CommonResponse<NewsDetail>> observable = mApiService.getNewsDetail(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommonResponse<NewsDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onNext(CommonResponse<NewsDetail> data) {
                        dataListener.onLoadSuccess(data.getData(),true);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException exception) {
                        dataListener.onLoadError(ExceptionHandle.handleException(exception).getMessage(),ExceptionHandle.handleException(exception).getCode());
                    }
                });
    }

    @Override
    public void loadDataPost(String url, Map params, DataListener l) {
    }

}
