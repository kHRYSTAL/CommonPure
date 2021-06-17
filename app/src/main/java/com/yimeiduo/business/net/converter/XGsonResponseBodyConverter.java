package com.yimeiduo.business.net.converter;


import android.util.Log;

import com.yimeiduo.business.entity.response.ErrorEntity;
import com.yimeiduo.business.net.exception.ExceptionHandle;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class XGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    XGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        //把responsebody转为string
        String response = value.string();

        ErrorEntity entity = gson.fromJson(response, ErrorEntity.class);//登录

        if (entity.isSuccess()){
            try {
                return adapter.fromJson(response);
            } finally {
                value.close();
            }
        } else {
            if (entity.getCode() != 200 ) {
                value.close();
                Log.e("Converter2==>", entity.toString());
                throw new ExceptionHandle.ResponseException(entity, entity.getCode());

            } else {
                try {
                    return adapter.fromJson(response);
                } finally {
                    value.close();
                }
            }
        }


       /* ErrorEntity entity = gson.fromJson(response, ErrorEntity.class);//登录
        ExceptionEntity exceptionEntity = gson.fromJson(response, ExceptionEntity.class);//其他请求
//        TokenErrorEntity tokenErrorEntity = gson.fromJson(response, TokenErrorEntity.class);//token
        if (!TextUtils.isEmpty(entity.getError())) {
            value.close();
            Log.e("Converter==>", entity.toString());
            throw new ExceptionHandle.ResponseException(entity, Integer.valueOf(entity.getError()));
        } else {
            if (exceptionEntity.getCode() != 200 ) {
                value.close();
                Log.e("Converter2==>", exceptionEntity.toString());
                throw new ExceptionHandle.ResponseException(exceptionEntity, exceptionEntity.getCode());

            }
            else {
                try {
                    return adapter.fromJson(response);
                } finally {
                    value.close();
                }

            }
        }*/


    }


 /*   @Override
    public T convert(ResponseBody value) throws IOException {
        //把responsebody转为string
        String response = value.string();

        ErrorEntity entity = gson.fromJson(response, ErrorEntity.class);//登录
        ExceptionEntity exceptionEntity = gson.fromJson(response, ExceptionEntity.class);//其他请求
//        TokenErrorEntity tokenErrorEntity = gson.fromJson(response, TokenErrorEntity.class);//token
        if (!TextUtils.isEmpty(entity.getError())) {
            value.close();
            Log.e("Converter==>", entity.toString());
            throw new ExceptionHandle.ResponseException(entity, Integer.valueOf(entity.getError()));
        } else if (exceptionEntity.getCode() != 0&&(exceptionEntity.getCode()<13000||exceptionEntity.getCode()>14000)) {
                value.close();
                Log.e("Converter2==>", exceptionEntity.toString());
                throw new ExceptionHandle.ResponseException(exceptionEntity, exceptionEntity.getCode());

        }
//        else if (tokenErrorEntity.getCode() != 0) {
//            value.close();
//            Log.e("Converter2==>", tokenErrorEntity.toString());
//            throw new ExceptionHandle.ResponseException(tokenErrorEntity, Integer.valueOf(tokenErrorEntity.getError()));
//        }
        else {
            try {
                return adapter.fromJson(response);
            } finally {
                value.close();
            }

        }
    }*/

}