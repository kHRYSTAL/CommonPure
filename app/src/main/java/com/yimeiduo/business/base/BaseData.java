package com.yimeiduo.business.base;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * usage: 所有数据对象需要继承该类
 * author: kHRYSTAL
 * create time: 2021/6/17
 * update time:
 * email: 723526676@qq.com
 */

public class BaseData extends IOException {
    public String dataToString() {
        // TODO 这里Gson需要改为单例
        return new Gson().toJson(this);
    }
}
