package com.yimeiduo.business.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chaychan.library.UIUtils;
import com.yimeiduo.business.entity.response.LoginEntity;
import com.yimeiduo.business.listener.PermissionListener;
import com.yimeiduo.business.ui.activity.MainActivity;
import com.yimeiduo.business.util.ToastUtil;
import com.yimeiduo.business.util.UIUtil;
import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.github.anzewei.parallaxbacklayout.widget.ParallaxBackLayout;
import com.yimeiduo.business.widget.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;

import static com.github.anzewei.parallaxbacklayout.ViewDragHelper.EDGE_LEFT;
import static com.github.anzewei.parallaxbacklayout.widget.ParallaxBackLayout.EDGE_MODE_DEFAULT;
import static com.github.anzewei.parallaxbacklayout.widget.ParallaxBackLayout.LAYOUT_COVER;

/**
 * @author ChayChan
 * @description: activity的基类
 * @date 2017/6/10  16:42
 */

public abstract class BaseActivity<T extends BasePresenter>  extends AppCompatActivity {
    public final static String TAG = BaseActivity.class.getSimpleName();
    public int page =1;
    protected T mPresenter;
    private static long mPreTime;
    private static Activity mCurrentActivity;// 对所有activity进行管理
    public static List<Activity> mActivities = new LinkedList<Activity>();
    protected Bundle savedInstanceState;
    public PermissionListener mPermissionListener;
    protected LoginEntity loginEntity;

    protected Dialog dialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (enableSlideClose()) {
            ParallaxBackLayout layout = ParallaxHelper.getParallaxBackLayout(this, true);
            layout.setEdgeMode(EDGE_MODE_DEFAULT);//边缘滑动
            layout.setEdgeFlag(getEdgeDirection());
            layout.setLayoutType(getSlideLayoutType(),null);

            layout.setSlideCallback(new ParallaxBackLayout.ParallaxSlideCallback() {
                @Override
                public void onStateChanged(int state) {
                    //收起软键盘
                    UIUtil.hideInput(getWindow().getDecorView());
                }
                @Override
                public void onPositionChanged(float percent) {
                }
            });
        }

        this.savedInstanceState = savedInstanceState;

        //初始化的时候将其添加到集合中
        synchronized (mActivities) {
            mActivities.add(this);
//            AppManager.getAppManager().addActivity(this);
        }

        mPresenter = createPresenter();

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }


    /**
     * 显示加载进度条
     */
    public void showProgress(String content) {
        if(dialog == null) {
            dialog = LoadingDialog.createLoadingDialog(this, content,false);
            dialog.show();
        }
    }

    /**
     * 显示加载进度条
     */
    public void showProgressCanDis(String content) {
        if(dialog == null) {
            dialog = LoadingDialog.createLoadingDialog(this, content,true);
            dialog.show();
        }
    }


    /**
     * 隐藏加载进度条
     */
    public void hideProgress() {
        if(dialog !=null) {
            dialog.dismiss();
            dialog =null;
        }
    }


    public boolean enableSlideClose() {
        return true;
    }

    /**
     * 默认为左滑，子类可重写返回对应的方向
     * @return
     */
    public int getEdgeDirection(){
        return EDGE_LEFT;
    }

    /**
     * 默认为覆盖滑动关闭效果，子类可重写
     * @return
     */
    public int getSlideLayoutType() {
        return LAYOUT_COVER;
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁的时候从集合中移除
        synchronized (mActivities) {
            mActivities.remove(this);
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 退出应用的方法
     */
    public static void exitApp() {

        ListIterator<Activity> iterator = mActivities.listIterator();

        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof MainActivity){
            /*//如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) { // 两次点击间隔大于2秒
                ToastUtil.showShort("再按一次，退出应用");
                mPreTime = System.currentTimeMillis();
                return;
            }*/

            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) { // 两次点击间隔大于2秒
                ToastUtil.showShort("再按一次，退出应用");
                mPreTime = System.currentTimeMillis();
                return;
            }else{

                mCurrentActivity.finish();
//                AppManager.getAppManager().finishAllActivity();
//                System.exit(0);
//                int pid = android.os.Process.myPid();
//                android.os.Process.killProcess(pid);
            }
        }
        super.onBackPressed();// finish()
    }

    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }

    /**
     * 申请运行时权限
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mPermissionListener.onGranted();
                    } else {
                        mPermissionListener.onDenied(deniedPermissions);
                    }
                }
                break;
        }
    }
}
