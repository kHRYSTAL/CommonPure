package com.yimeiduo.business.ui.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.entity.response.CommonBean;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.ui.adapter.CommonAdapter;
import com.yimeiduo.business.util.MyLog;
import com.yimeiduo.business.widget.PullZoomView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PullScrollViewActivity extends BaseActivity {

    @BindView(R.id.pzv)
    PullZoomView pzv;


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
        return R.layout.activity_pull_scrollview_layout;
    }

    @Override
    public void initView() {
        pzv.setIsParallax(true);
        pzv.setIsZoomEnable(true);
        pzv.setSensitive(3.0f);
        pzv.setZoomTime(2);
        pzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onScroll   t:" + t + "  oldt:" + oldt);
            }

            @Override
            public void onHeaderScroll(int currentY, int maxY) {
                System.out.println("onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
            }

            @Override
            public void onContentScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onContentScroll   t:" + t + "  oldt:" + oldt);
            }
        });
        pzv.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onPullZoom(int originHeight, int currentHeight) {
                System.out.println("onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
            }

            @Override
            public void onZoomFinish() {
                System.out.println("onZoomFinish");
            }
        });
    }

    @Override
    public void initData() {

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
