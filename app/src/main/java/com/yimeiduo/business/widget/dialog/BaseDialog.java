package com.yimeiduo.business.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yimeiduo.business.R;

/**
 * author huanggang on 2016/2/15 14:04
 * email 553770617@qq.com
 * desc 对话框基类
 */
public class BaseDialog extends Dialog {
    public final static String TAG = BaseDialog.class.getSimpleName();
    ViewGroup root_view;
    ViewGroup base_content;
    View content_view;
//    View progress_bar;
    Activity mActivity;
    private boolean canceledOnTouchOutside = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.root_view:
                    if (canceledOnTouchOutside) {
                        cancel();
                    }
                    break;
            }
        }
    };

    public BaseDialog(Activity activity) {
        super(activity, R.style.Dialog);
        this.mActivity = activity;
        this.setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    @Override
    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        super.setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.dialog_base);
        findViews();
        this.root_view.setOnClickListener(this.mOnClickListener);
    }

    public void setFullScreenWidth() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setGravity(Gravity.CENTER);
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }

    public void setWidth(int width) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        getWindow().setAttributes(params);
    }

    public void setHeight(int height) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = height;
        getWindow().setAttributes(params);
    }

    private void findViews() {
        this.base_content = (ViewGroup) findViewById(R.id.base_content);
//        this.progress_bar = findViewById(R.id.progress_bar);
        this.root_view = (ViewGroup) findViewById(R.id.root_view);
    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, base_content, false));
    }

    @Override
    public void setContentView(View view) {
        this.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (content_view != null && this.base_content.findViewById(content_view.getId()) != null)
            this.base_content.removeView(base_content.findViewById(content_view.getId()));
        this.content_view = view;
        this.base_content.addView(content_view, 0, params);
    }

    /**
     * 使背景变暗
     */
    public void dim() {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        mActivity.getWindow().setAttributes(lp);
    }

    /**
     * 是背景回复正常
     */
    public void lighten() {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 1f;
        mActivity.getWindow().setAttributes(lp);
    }

    @Override
    public void show() {
//        if (dim)
//            dim();
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        if (dim)
//            lighten();
    }

//    /**
//     * 显示加载进度条
//     */
//    public void showProgress() {
//        if (progress_bar != null) {
//            progress_bar.setVisibility(View.VISIBLE);
//        }
//    }

//    /**
//     * 隐藏加载进度条
//     */
//    public void hideProgress() {
//        if (progress_bar != null) {
//            progress_bar.setVisibility(View.GONE);
//        }
//    }

    @Override
    public void onBackPressed() {
//        if (progress_bar != null && progress_bar.getVisibility() == View.VISIBLE) {
//            progress_bar.setVisibility(View.GONE);
//        } else {
//
//        }
        super.onBackPressed();
    }

    private boolean dim = true;

    public BaseDialog setDim(boolean dim) {
        this.dim = dim;
        return this;
    }

    @Override
    public void cancel() {
        super.cancel();
        onCancel();
    }

    public void onCancel() {
    }
}
