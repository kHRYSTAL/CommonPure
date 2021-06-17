package com.yimeiduo.business.base;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yimeiduo.business.net.RetrofitHelper;
import com.yimeiduo.business.net.RetrofitService;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseModel<T extends BaseData> implements IBaseModel<T> {
    protected RetrofitService mApiService = RetrofitHelper.getInstance().getRetrofitService();
    @Override
    public void setCache(String key, T t) {
        // TODO 数据库设置缓存
        String s = t.dataToString();
    }

    @Override
    public T getCache(String key) {
        // TODO 数据库获取缓存
        String value = "";
        if (!TextUtils.isEmpty(value) && getClazz() != null)
            return fromJson(value, getClazz());
        return null;
    }

    private T fromJson(String result, Class<T> classOfT) {
        if (result == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(result, classOfT);
    }

    private Class<T> getClazz() {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = null;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) superclass;
            Type[] typeArray = parameterizedType.getActualTypeArguments();
            if (typeArray != null && typeArray.length > 0) {
                return (Class<T>) typeArray[0];
            }
        }
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
