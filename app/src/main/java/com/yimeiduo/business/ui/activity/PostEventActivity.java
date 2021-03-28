package com.yimeiduo.business.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.receive.MyReceiver;
import com.yimeiduo.business.util.TestDataModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class PostEventActivity extends BaseActivity {

    private IntentFilter intentFilter;
    private MyReceiver receiver;
    public static final String ACTION_FIRST = "com.yimeiduo.business.BROADCAST_FIRST";


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
        TestDataModel.getInstance(this);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @OnClick({R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_send:
//                sendGlobalBroadcast();
//                EventBus.getDefault().post(new TypeEvent("测试。。。"));finish();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 延时5min发送一个消息，此时handler是持有activity引用的
                        mHandler.sendEmptyMessageDelayed(1, 5 * 60 * 1000);
                        EventBus.getDefault().post(new TypeEvent("测试。。。"));finish();
                    }
                }).start();
                break;
        }
    }

    @Override
    public void initListener() {
        /*
          注册广播
         */
        intentFilter = new IntentFilter(ACTION_FIRST);
        receiver = new MyReceiver();
        registerReceiver(receiver, intentFilter);
    }

    public void sendGlobalBroadcast() {
        Intent intent = new Intent();
        //设置前台广播的标志位
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.setAction(ACTION_FIRST);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
