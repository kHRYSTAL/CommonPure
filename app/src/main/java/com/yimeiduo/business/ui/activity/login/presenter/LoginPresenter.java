package com.yimeiduo.business.ui.activity.login.presenter;

import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.ui.activity.login.model.LoginModel;
import com.yimeiduo.business.ui.activity.login.view.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView, LoginModel> {

    public LoginPresenter(ILoginView view, LoginModel model) {
        super(view, model);
    }

}
