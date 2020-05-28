package com.yimeiduo.business.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yimeiduo.business.MyApplication;

public class ToastUtil {
    public static Toast mToast;
    private static ToastUtil instance;

    public static ToastUtil getToastUtil() {
        if (instance == null) {
            instance = new ToastUtil();
        }
        return instance;
    }

    public static void showToast(String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), "", duration);

            LinearLayout layout = (LinearLayout) mToast.getView();
            TextView tv = (TextView) layout.getChildAt(0);
            tv.setTextSize(15);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 得到上下文
     *
     * @return
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    //以"字符串类型"为消息的"短显示吐司"
    public static void showShort(String message) {
        showToast(message, Toast.LENGTH_SHORT);
//            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //以"资源ID"为消息的"短显示吐司"
    public static void showShort( int message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //以"字符串类型"为消息的"长显示吐司"
    public static void showLong( String message) {
        showToast(message, 3000);
//            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    //以"字符串资源ID"为消息的"长显示吐司"
    public static void showLong( int message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示位置的吐司, 短吐司, 消息为"字符串资源的ID"
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param gravity
     *            参数类型为Int型,表示在屏幕上所处的位置(如Gravity.centre表示处在屏幕中间)
     * @param xOffset
     *            Toast这个View以Gravity.centre位置为参照物相对X轴的偏移量
     * @param yOffset
     *            Toast这个View以Gravity.centre位置为参照物相对Y轴的偏移量
     */
    public static void showCustomShort( int messageId, int gravity, int xOffset, int yOffset) {
            Toast toast = Toast
                    .makeText(getContext(), messageId, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
    }

    /**
     * 自定义显示位置的吐司, 长吐司, 消息为字符串资源ID
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param gravity
     *            参数类型为Int型,表示在屏幕上所处的位置(如Gravity.centre表示处在屏幕中间)
     * @param xOffset
     *            Toast这个View以Gravity.centre位置为参照物相对X轴的偏移量
     * @param yOffset
     *            Toast这个View以Gravity.centre位置为参照物相对Y轴的偏移量
     */
    public static void showCustomLong(int messageId, int gravity, int xOffset, int yOffset) {
            Toast toast = Toast.makeText(getContext(), messageId, Toast.LENGTH_LONG);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
    }

    /**
     * 自定义显示位置的吐司, 短吐司, 消息为字符串类型
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param gravity
     *            参数类型为Int型,表示在屏幕上所处的位置(如Gravity.centre表示处在屏幕中间)
     * @param xOffset
     *            Toast这个View以Gravity.centre位置为参照物相对X轴的偏移量
     * @param yOffset
     *            Toast这个View以Gravity.centre位置为参照物相对Y轴的偏移量
     */
    public static void showCustomShort( CharSequence message, int gravity, int xOffset, int yOffset) {
            Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
    }

    /**
     * 自定义显示位置的吐司, 长吐司, 消息为字符串类型
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param charSequence
     *            字符串类型的消息
     * @param gravity
     *            参数类型为Int型,表示在屏幕上所处的位置(如Gravity.centre表示处在屏幕中间)
     * @param xOffset
     *            Toast这个View以Gravity.centre位置为参照物相对X轴的偏移量
     * @param yOffset
     *            Toast这个View以Gravity.centre位置为参照物相对Y轴的偏移量
     */
    public static void showCustomLong( CharSequence charSequence, int gravity, int xOffset, int yOffset) {
            Toast toast = Toast.makeText(getContext(), charSequence, Toast.LENGTH_LONG);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
    }

    /**
     * 这是一个带图片的吐司,其吐司的显示位置定义在了屏幕正中间-->短吐司
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param message
     *            字符串类型的消息
     * @param resId
     *            图片资源ID
     */
    public static void showCustomToastWithImageShort(CharSequence message, int resId) {
            Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout ToastContentView = (LinearLayout) toast.getView();
            ImageView img = new ImageView(getContext());
            img.setImageResource(resId);
            ToastContentView.addView(img);
            toast.show();
    }

    /**
     * 这是一个带图片的吐司,其吐司的显示位置定义在了屏幕正中间-->长吐司
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param message
     *            字符串类型的消息
     * @param resId
     *            图片资源ID
     */
    public static void showCustomToastWithImageLong( CharSequence message, int resId) {
            Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout ToastContentView = (LinearLayout) toast.getView();
            ImageView img = new ImageView(getContext());
            img.setImageResource(resId);
            ToastContentView.addView(img);
            toast.show();
    }

    /**
     * 这是一个带图片的吐司,其吐司的显示位置定义在了屏幕正中间-->短吐司
     *
     * @Time 2015年10月20日
     * @Author lizy18
     *            上下文
     * @param messageId
     *            字符串资源的ID值
     * @param resId
     *            图片资源ID
     */
    public static void showCustomToastWithImageShort( int messageId, int resId) {
            Toast toast = Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout ToastContentView = (LinearLayout) toast.getView();
            ImageView img = new ImageView(getContext());
            img.setImageResource(resId);
            ToastContentView.addView(img);
            toast.show();
    }

    /**
     * 这是一个带图片的吐司,其吐司的显示位置定义在了屏幕正中间-->长吐司
     *
     * @Time 2015年10月20日
     * @Author lizy18
     * @param context
     *            上下文
     * @param messageId
     *            字符串资源的ID值
     * @param resId
     *            图片资源ID
     */
    public static void showCustomToastWithImageLong(Context context, int messageId, int resId) {
            Toast toast = Toast.makeText(context, messageId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout ToastContentView = (LinearLayout) toast.getView();
            ImageView img = new ImageView(context);
            img.setImageResource(resId);
            ToastContentView.addView(img);
            toast.show();
    }

}
