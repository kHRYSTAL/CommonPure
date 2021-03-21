package com.yimeiduo.business.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.util.MyLog;
import com.yimeiduo.business.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class SanFangActivity extends BaseActivity {

    @BindView(R.id.tv_desc)
    TextView tv_desc;

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
        return R.layout.activity_sanfang;
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.tv_gonewxt,})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_gonewxt:
                startActivity(new Intent(SanFangActivity.this, PostEventActivity.class));
                break;
        }
    }

//    @Subscribe(threadMode = ThreadMode.ASYNC)
    /**
     * 如果指定为 ThreadMode.ASYNC 模式  进行UI更新（Toast提示）则会抛出异常
     * //java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()
     * at android.widget.Toast$TN.<init>(Toast.java:411) --》 mTN = new TN(context.getPackageName(), looper);
     *
     * 如果指定为 ThreadMode.ASYNC 模式  进行UI更新（v_desc.setText(result);）则不会抛出异常
     *  setText--checkForRelayout();--requestLayout（）--ViewRootImpl实现了父类的requestLayout（）--
     *  checkThread();--if (mThread != Thread.currentThread()) {throw new CalledFromWrongThreadException}
     *
     * event  先于  onResume执行
     * */

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void event(TypeEvent event){
        MyLog.e(TAG,"event--" + event);
        String result = event.getType();
        tv_desc.setText(result);
        MyLog.e(TAG,"result--" + result);
//        ToastUtil.showShort(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.e(TAG,"onResume()--" );
    }

    @Override
    public void initListener() {
        registerEventBus(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
