package com.yimeiduo.business.base;


import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> {

    public static final String TAG = BasePresenter.class.getSimpleName();

    private M model;
    protected WeakReference<V> mViewRef;
    public CompositeDisposable mCompositeSubscription;

    public BasePresenter(V view, M model) {
        addSubscription();
        attachView(view);
        attachModel(model);
        if (setupDone()) {
            updateView();
        }
    }

    /**
     * view和model第一次初始化后默认执行该方法
     * 用于view视图的更新
     */
    protected void updateView() {

    }

    protected V view() {
        if (mViewRef == null)
            return null;
        return mViewRef.get();
    }

    protected M model() {
        return model;
    }

    private boolean setupDone() {
        return view() != null && model() != null;
    }

    private void attachModel(M model) {
        this.model = model;
    }

    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }


    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
        }
        onUnsubscribe();
    }


    public void addSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
    }

    //Rxjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
            mCompositeSubscription = null;
        }
    }
}