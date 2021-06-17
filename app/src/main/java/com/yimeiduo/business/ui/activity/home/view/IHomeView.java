package com.yimeiduo.business.ui.activity.home.view;

import com.yimeiduo.business.base.IBaseView;
import com.yimeiduo.business.entity.response.NewsDetail;

public interface IHomeView extends IBaseView {
    void onSuccess(NewsDetail newsDetail);
    void onFails(String exception);
}
