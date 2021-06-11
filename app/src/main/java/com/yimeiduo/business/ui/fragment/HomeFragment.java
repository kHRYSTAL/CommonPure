package com.yimeiduo.business.ui.fragment;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseFragment;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.ui.adapter.RecycleAdapter;
import com.yimeiduo.business.util.MyLog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment  {
    protected static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecycleAdapter adapter;
    private List<String> list;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        MyLog.i(TAG,"加载了:loadData() ");
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
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
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

}
