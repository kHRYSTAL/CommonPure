package com.yimeiduo.business.ui.activity.home.presenter;

import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.base.DataListener;
import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.NewsDetail;
import com.yimeiduo.business.net.exception.BaseObserver;
import com.yimeiduo.business.net.exception.ExceptionHandle;
import com.yimeiduo.business.ui.activity.home.model.HomeModel;
import com.yimeiduo.business.ui.activity.home.view.IHomeView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {
    HomeModel homeModel ;
    IHomeView iHomeView;
    public HomePresenter(IHomeView view) {
        super(view);
        this.iHomeView = iHomeView;
        homeModel = new HomeModel();
    }

    public void getNewsDetail(String url){
        homeModel.getNewsDetail(this,url, true,true);
    }

    /*@Override
    public void onLoadSuccess(Object obj, boolean ischache) {
        if (getView()!=null){
            NewsDetail data= (NewsDetail)obj;
            getView().onSuccess(data);
        }
    }

    @Override
    public void onLoadError(String msg, int code) {
        if (getView()!=null){
            getView().onFails(msg);
        }
    }*/
}
