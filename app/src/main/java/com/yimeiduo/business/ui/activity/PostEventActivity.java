package com.yimeiduo.business.ui.activity;

import android.view.View;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.eventbus.TypeEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class PostEventActivity extends BaseActivity {

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
        return R.layout.activity_postevent;
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_send:
//                EventBus.getDefault().post(new TypeEvent("测试。。。"));finish();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new TypeEvent("测试。。。"));finish();
                    }
                }).start();
                break;
        }
    }

    @Override
    public void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
