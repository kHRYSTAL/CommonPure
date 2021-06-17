package com.yimeiduo.business.net.exception;

import android.content.Context;
import android.content.Intent;

import com.yimeiduo.business.MyApplication;
import com.yimeiduo.business.entity.response.ErrorEntity;
import com.yimeiduo.business.ui.activity.login.LoginActivity;
import com.yimeiduo.business.util.AppManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {

    private Context context;

    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ExceptionHandle.ResponseException) {
            if (((ErrorEntity) e.getCause()).getCode() == ExceptionHandle.UNAUTHORIZED) {//                case ExceptionHandle.TOKEN_TIME:
                if (context == null) {
                    context = MyApplication.getContext();
                }
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                AppManager.getAppManager().finishOthersActivity(LoginActivity.class);
            }

            onError((ExceptionHandle.ResponseException) e);
        } else {
            onError(new ExceptionHandle.ResponseException(e, ExceptionHandle.ERROR.UNKNOWN));
        }

        if (e instanceof HttpException) {
            if (((HttpException) e).code() == ExceptionHandle.UNAUTHORIZED) {
//                if ((((HttpException) e).code() == ExceptionHandle.UNAUTHORIZED) || ((HttpException) e).code() == ExceptionHandle.TOKEN_TIME) {
                context.startActivity(new Intent(context, LoginActivity.class));
                AppManager.getAppManager().finishOthersActivity(LoginActivity.class);
            }
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(ExceptionHandle.ResponseException exception);
}