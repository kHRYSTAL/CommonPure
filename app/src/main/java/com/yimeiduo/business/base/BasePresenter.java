package com.yimeiduo.business.base;



import com.yimeiduo.business.net.RetrofitHelper;
import com.yimeiduo.business.net.RetrofitService;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V> {

    public static final String TAG = BasePresenter.class.getSimpleName();

    protected WeakReference<V> mViewRef;
    public CompositeDisposable mCompositeSubscription;

    public BasePresenter(V view) {
        addSubscription();
        attachView(view);
    }

    public void attachView(V view) {
        mViewRef=new WeakReference<V>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }


    public void detachView() {
        if(mViewRef!=null){
            mViewRef.clear();
        }
        onUnsubscribe();
    }


    public void addSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isDisposed() ) {
            mCompositeSubscription.dispose();
            mCompositeSubscription = null;
        }
    }
}