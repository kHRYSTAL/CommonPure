package com.yimeiduo.business.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //这个方法运行在主线程中的，处理具体的逻辑
        Log.d(TAG, "onReceive: this is " + "in thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(context.getApplicationContext(), "MyReceiver onReceive", Toast.LENGTH_SHORT).show();
        // abortBroadcast();
    }


}
