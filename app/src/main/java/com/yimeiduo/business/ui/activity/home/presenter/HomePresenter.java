package com.yimeiduo.business.ui.activity.home.presenter;

import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.NewsDetail;
import com.yimeiduo.business.net.exception.BaseObserver;
import com.yimeiduo.business.net.exception.ExceptionHandle;
import com.yimeiduo.business.ui.activity.home.model.HomeModel;
import com.yimeiduo.business.ui.activity.home.view.IHomeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView, HomeModel> {
    public HomePresenter(IHomeView view, HomeModel model) {
        super(view, model);
    }

    @Override
    protected void updateView() {
        if (model().getCache(HomeModel.HOME_KEY) != null) {
            view().onSuccess((NewsDetail) model().getCache(HomeModel.HOME_KEY));
        }
    }

    public void getNewsDetail(String url) {
        model().getNewsDetail(url).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommonResponse<NewsDetail>>() {
                    @Override
                    public void onNext(@NonNull CommonResponse<NewsDetail> newsDetailCommonResponse) {
                        view().onSuccess(newsDetailCommonResponse.getData());
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException exception) {
                        view().onFails(exception.getMessage());
                    }
                });
    }
}
