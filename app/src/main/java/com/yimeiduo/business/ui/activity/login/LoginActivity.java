package com.yimeiduo.business.ui.activity.login;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.LoginEntity;
import com.yimeiduo.business.ui.activity.MainActivity;
import com.yimeiduo.business.ui.activity.login.presenter.LoginPresenter;
import com.yimeiduo.business.ui.activity.login.view.ILoginView;
import com.yimeiduo.business.util.CommonUtils;
import com.yimeiduo.business.util.ToastUtil;
import com.coorchice.library.SuperTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.tv_login)
    SuperTextView tv_login;

    @BindView(R.id.et_user)
    EditText et_user;
    @BindView(R.id.et_password)
    EditText et_password;

    protected LoginPresenter createPresenter() {
        return new LoginPresenter(LoginActivity.this);
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        permissions();
    }

    private void permissions() {
        final RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
// Must be done during  an initialization phase like onCreate
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            // All requested permissions are granted
                        } else {
                            // At least one permission is denied
                        }
                    }
                });
    }

    @OnClick({R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_login:
//                login();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;

        }
    }

    private void login() {
        if (TextUtils.isEmpty(et_user.getText().toString().trim()) ||
                TextUtils.isEmpty(et_password.getText().toString().trim())){
            ToastUtil.showShort("账号或者密码不能为空");
            return;
        }else{

            if (!CommonUtils.isChinaPhoneLegal(et_user.getText().toString())) {
                ToastUtil.showShort("手机号码无效");
                return;
            }

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onCodeSuccess(CommonResponse commonResponse) {
    }

    @Override
    public void onSuccess(LoginEntity loginEntity) {
        hideProgress();
    }

    @Override
    public void onFails(String exception) {
        hideProgress();
        ToastUtil.showShort(exception);
    }

}
