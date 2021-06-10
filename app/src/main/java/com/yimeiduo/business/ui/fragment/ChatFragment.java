package com.yimeiduo.business.ui.fragment;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseFragment;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.util.MyLog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class ChatFragment extends BaseFragment  {
    protected static final String TAG = ChatFragment.class.getSimpleName();

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void loadData() {
        MyLog.i(TAG,"加载了:loadData() ");
    }

    @Override
    public void initData() {
        MyLog.i(TAG,"加载了: initData");
        initRecyclerView();
    }

    private void initRecyclerView() {

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        adapter = new OrderAdapter(getActivity(), list,Constant.ORDER_ON);//设置Adapter
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));   //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置增加或删除条目的动画*/


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

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
    }

}
