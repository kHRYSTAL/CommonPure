package com.yimeiduo.business.base;

import com.yimeiduo.business.net.RetrofitHelper;
import com.yimeiduo.business.net.RetrofitService;

public abstract class BaseModel<T extends BaseData> implements IBaseModel<T> {
    protected RetrofitService mApiService = RetrofitHelper.getInstance().getRetrofitService();


    @Override
    public void setCache(String key, T t) {
        // TODO 数据库设置缓存
    }

    @Override
    public T getCache(String key) {
        // TODO 数据库获取缓存
        return null;
    }

//    Observable<CommonResponse<NewsDetail>> observable = mApiService.getNewsDetail(url);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new BaseObserver<CommonResponse<NewsDetail>>() {
//        @Override
//        public void onSubscribe(Disposable d) {
////                        mCompositeSubscription.add(d);
//        }
//
//        @Override
//        public void onNext(CommonResponse<NewsDetail> data) {
//            dataListener.onLoadSuccess(data.getData(),true);
//        }
//
//        @Override
//        public void onComplete() {
//        }
//
//        @Override
//        public void onError(ExceptionHandle.ResponseException exception) {
//            dataListener.onLoadError(ExceptionHandle.handleException(exception).getMessage(),ExceptionHandle.handleException(exception).getCode());
//        }
//    });
}
