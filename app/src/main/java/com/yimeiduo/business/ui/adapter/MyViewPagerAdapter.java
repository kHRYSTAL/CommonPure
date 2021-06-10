package com.yimeiduo.business.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //处理点击不相邻的Fragment时候, 数据重复加载问题
//        super.destroyItem(container, position, object);
    }
}
