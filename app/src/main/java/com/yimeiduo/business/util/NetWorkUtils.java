package com.yimeiduo.business.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author ChayChan
 * @description: 网络的工具类
 * @date 2017/6/16  21:22
 */

public class NetWorkUtils {

    /**
     * 没有连接网络
     */
    private static final int NETWORK_NONE = -1;
    /**
     * 移动网络
     */
    private static final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    private static final int NETWORK_WIFI = 1;

    //在Android7.0之前我们只需要静态注册一下广播就可以实现实时监听网络状态的变化;
    //网络变化时系统会发出广播。所以我们监听这个广播，利用接口回调通知activity做相应的操作就好了。。
    //由于7.0删除了隐式广播 — CONNECTIVITY_ACTION，动态注册BroadcastReceiver暂时不受影响。
    //https://blog.csdn.net/mxiaoyem/article/details/50708052
    public static int getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }


    public static boolean isNetworkAvailable(Context context) {
       if(context !=null){
           ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo info = cm.getActiveNetworkInfo();
           if(info !=null){
               return info.isAvailable();
           }
       }
        return false;
    }
}
