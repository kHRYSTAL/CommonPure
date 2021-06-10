package com.yimeiduo.business.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.yimeiduo.business.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChayChan
 * @description: 首页页签的adapter
 * @date 2017/6/12  21:36
 */

public class MainTabAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mFragments = new ArrayList<BaseFragment>();

    public MainTabAdapter(List<BaseFragment> fragmentList, FragmentManager fm) {
        super(fm);
        if (fragmentList != null){
            mFragments = fragmentList;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
