package com.yimeiduo.business.util;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TestDataModel {

    private Context context;
    private static volatile TestDataModel instance ;
    private TestDataModel(Context context){
        this.context = context;
    }

    public List<Activity> list = new ArrayList<>();

    public static TestDataModel getInstance(Context context){
        if (instance ==null){
            synchronized (TestDataModel.class){
                if (instance == null){
                    instance = new TestDataModel(context);
                }
            }
        }
        return instance;
    }


}
