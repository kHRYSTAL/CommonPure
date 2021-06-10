package com.yimeiduo.business.ui.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.entity.response.CommonBean;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.ui.activity.login.LoginActivity;
import com.yimeiduo.business.ui.adapter.CommonAdapter;
import com.yimeiduo.business.util.MyLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CoordinatorLayoutActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CommonAdapter adapter;
    private List<CommonBean> list;

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
        return R.layout.activity_coordinator_layout;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        for(int i = 0; i<20; i++){
            list.add(new CommonBean("CoordinatorLayout",1));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(CoordinatorLayoutActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);//设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        adapter = new CommonAdapter(CoordinatorLayoutActivity.this, list);//设置Adapter
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));   //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置增加或删除条目的动画
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
