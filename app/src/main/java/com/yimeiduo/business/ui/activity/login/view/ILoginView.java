package com.yimeiduo.business.ui.activity.login.view;

import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.LoginEntity;

public interface ILoginView {
    void onCodeSuccess(CommonResponse commonResponse);
    void onSuccess(LoginEntity loginEntity);
    void onFails(String exception);
}
