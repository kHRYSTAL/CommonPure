package com.yimeiduo.business.base;

/**
 * Created by limingde1 on 2017/5/16.
 */

public interface DataListener<T> {
    void onLoadSuccess(T t,boolean ischache);
    void onLoadError(String msg, int code);
}
