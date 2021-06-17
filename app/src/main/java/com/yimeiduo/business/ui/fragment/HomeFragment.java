package com.yimeiduo.business.ui.fragment;


import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yimeiduo.business.Constant;
import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseFragment;
import com.yimeiduo.business.entity.response.NewsDetail;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.ui.activity.home.model.HomeModel;
import com.yimeiduo.business.ui.activity.home.presenter.HomePresenter;
import com.yimeiduo.business.ui.activity.home.view.IHomeView;
import com.yimeiduo.business.ui.adapter.RecycleAdapter;
import com.yimeiduo.business.util.MyLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {
    protected static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.tv_home)
    TextView tv_home;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecycleAdapter adapter;
    private List<String> list;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this, new HomeModel());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        MyLog.i(TAG,"加载了:loadData() ");
        mPresenter.getNewsDetail(Constant.URL_BASE);
    }

    @Override
    public void initData() {
        registerEventBus(this);
        MyLog.i(TAG,TAG+"注册");
        MyLog.i(TAG,"加载了: initData");
        initRecyclerView();
    }

    private void initRecyclerView() {

        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);//设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        adapter = new RecycleAdapter(getActivity(), list);//设置Adapter
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));   //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置增加或删除条目的动画


        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRefresh(TypeEvent event){
        MyLog.i(TAG, "event: " + event);
        switch (event.getType()){
            case "refresh":
                recyclerView.smoothScrollToPosition(0);
                break;
        }
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
        MyLog.i(TAG,TAG+"取消注册");
    }

    @Override
    public void onSuccess(NewsDetail newsDetail) {
        MyLog.i(TAG,"newsDetail--" + newsDetail);
    }

    @Override
    public void onFails(String exception) {
        MyLog.i(TAG,"exception" + exception);
    }
}
