package com.yimeiduo.business.ui.activity;

import android.util.Log;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.base.BaseFragment;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.eventbus.TypeEvent;
import com.yimeiduo.business.ui.adapter.MainTabAdapter;
import com.yimeiduo.business.ui.fragment.ChatFragment;
import com.yimeiduo.business.ui.fragment.HomeFragment;
import com.yimeiduo.business.ui.fragment.MineFragment;
import com.yimeiduo.business.util.MyLog;
import com.yimeiduo.business.widget.NoScrollViewPager;
import com.yimeiduo.business.widget.bottombar.BottomBarItem;
import com.yimeiduo.business.widget.bottombar.BottomBarLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    public final static String TAG = MainActivity.class.getSimpleName();


    @BindView(R.id.vp_content)
    NoScrollViewPager mVpContent;

    @BindView(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;

    private List<BaseFragment> mFragments;
    private MainTabAdapter mTabAdapter;

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
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());

        mFragments.add(new ChatFragment());

        mFragments.add(new MineFragment());
    }

    @Override
    public void initListener() {
        mTabAdapter = new MainTabAdapter(mFragments, getSupportFragmentManager());
        mVpContent.setAdapter(mTabAdapter);
        mVpContent.setOffscreenPageLimit(mFragments.size());
        mBottomBarLayout.setViewPager(mVpContent);
        //设置条目点击的监听
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
                MyLog.i(TAG, "position: " + currentPosition);

                EventBus.getDefault().post(new TypeEvent("refresh"));
            }
        });
    }

}
