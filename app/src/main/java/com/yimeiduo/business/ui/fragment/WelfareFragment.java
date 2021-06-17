package com.yimeiduo.business.ui.fragment;

import android.view.View;

import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseFragment;
import com.yimeiduo.business.base.BasePresenter;
import com.yimeiduo.business.util.MyLog;

import butterknife.OnClick;

public class WelfareFragment extends BaseFragment {
    protected static final String TAG = WelfareFragment.class.getSimpleName();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        MyLog.i(TAG,"加载了:initData() ");
//        registerEventBus(this);
        MyLog.i(TAG,"注册");
    }

    @Override
    public void loadData() {
        MyLog.i(TAG,"加载了:loadData() ");
    }

    @Override
    public void onFragmentResume() {
    }

    @OnClick({R.id.tv_logout})
    public void onViewClicked(View view){
       switch (view.getId()){
           case R.id.tv_logout:

              /* new InformationTipsDialog(getActivity(),"您确定要退出吗?","") {
                   @Override
                   public void onPositive(InformationTipsDialog dialog) {
                       ToastUtil.showShort("退出成功！");
                       startActivity(new Intent(getActivity(), LoginActivity.class));
                       getActivity().finish();

                       dialog.dismiss();
                   }

                   @Override
                   public void onNegative(InformationTipsDialog dialog) {
                       dialog.dismiss();
                   }
               }.show();*/

               break;
       }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterEventBus(this);
        MyLog.i(TAG,"取消注册");
    }

}
