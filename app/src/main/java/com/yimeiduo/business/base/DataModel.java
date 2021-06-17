package com.yimeiduo.business.base;

import java.util.Map;

/**
 * Created by limingde1 on 2017/5/16.
 */

public interface DataModel<T> {
    /**
     * 网络加载数据  不带后面参数默认为不缓存  不从缓存去数据
     * @param url
     * @param l
     */
    public void loadData(String url, final DataListener l);

    /**
     * 通过网络请求加载数据
     *
     * @param url
     * @param params post请求的参数
     * @param l
     */
    public void loadDataPost(String url, Map<String, String> params, final DataListener l);

    /**
     * 网络加载数据  默认不缓存数据到文件
     * @param url
     * @param l
     * @param isFromCache true  首先从本地获取数据  然后再从网络加载
     */
    public void loadData(String url, final DataListener l, boolean isFromCache);

    /**
     * 网络加载数据
     * @param url
     * @param l
     * @param isFromCache  true  首先从本地获取数据  然后再从网络加载
     * @param isNeedCache  true 会把网络加载到的数据保存到本地
     */
    public void loadData(String url, final DataListener l, boolean isFromCache, boolean isNeedCache);
}
