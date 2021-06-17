package com.yimeiduo.business.base;

/**
 * Created by limingde1 on 2017/5/16.
 */
public interface IBaseModel<T extends BaseData> {
    T getCache(String key);

    void setCache(String key, T t);
}
