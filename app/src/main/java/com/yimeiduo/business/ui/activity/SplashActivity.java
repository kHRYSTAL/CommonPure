package com.yimeiduo.business.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;

import com.yimeiduo.business.Constant;
import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.entity.response.LoginEntity;
import com.yimeiduo.business.ui.activity.login.LoginActivity;
import com.yimeiduo.business.util.MyLog;
import com.yimeiduo.business.util.SpUtil;
import com.yimeiduo.business.util.UIUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        permissions();
        read();
    }

    private void permissions() {
        final RxPermissions rxPermissions = new RxPermissions(SplashActivity.this); // where this is an Activity or Fragment instance
// Must be done during  an initialization phase like onCreate
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            // All requested permissions are granted
                            read();
                            MyLog.i(TAG,"SplashActivity--granted");
                        } else {
                            // At least one permission is denied
                            finish();
                            MyLog.i(TAG,"SplashActivity--not");
                        }
                    }
                });
    }

    /*读*/
    private void read() {
        MyLog.i(TAG,"SplashActivity--read");
        LoginEntity loginEntity = SpUtil.getObjFromSP(Constant.SP_LOGINENTITY);
        String token = (String)SpUtil.get(Constant.SP_TOKEN,"");
        if (loginEntity != null && !TextUtils.isEmpty(token)) {
            MyLog.i(TAG, loginEntity.toString());
            UIUtils.postTaskDelay(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            },1000);
        }else{
            goActivity();
        }
    }

    private void goActivity(){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();

        MyLog.i(TAG,"SplashActivity--goActivity");
    }


}
