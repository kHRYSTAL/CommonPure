package com.yimeiduo.business.ui.activity.login;

import android.Manifest;
import android.content.ContentProvider;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yimeiduo.business.R;
import com.yimeiduo.business.base.BaseActivity;
import com.yimeiduo.business.entity.CommonResponse;
import com.yimeiduo.business.entity.response.CommonBean;
import com.yimeiduo.business.entity.response.LoginEntity;
import com.yimeiduo.business.mvvm.QueryWeatherActivity;
import com.yimeiduo.business.ui.activity.MainActivity;
import com.yimeiduo.business.ui.activity.SanFangActivity;
import com.yimeiduo.business.ui.activity.login.presenter.LoginPresenter;
import com.yimeiduo.business.ui.activity.login.view.ILoginView;
import com.yimeiduo.business.ui.adapter.CommonAdapter;
import com.yimeiduo.business.util.CommonUtils;
import com.yimeiduo.business.util.ToastUtil;
import com.coorchice.library.SuperTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_logo)
    ImageView iv_logo;



    private CommonAdapter adapter;

    private List<CommonBean> list;

    protected LoginPresenter createPresenter() {
        return new LoginPresenter(LoginActivity.this);
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        permissions();

        Glide.with(this)
                .load("http://images.youtukd.com/2020/02/14/1581660763873.jpg")
                .placeholder(R.mipmap.ic_logo)
                .override(300,300)
                .into(iv_logo);

        list = new ArrayList<>();
        list.add(new CommonBean("MVP架构",1));
        list.add(new CommonBean("MVVM架构",1));
        list.add(new CommonBean("第三方-eventbus",1));

        LinearLayoutManager layoutManager = new LinearLayoutManager(LoginActivity.this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        adapter = new CommonAdapter(LoginActivity.this, list);//设置Adapter
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));   //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置增加或删除条目的动画

        adapter.setOnItemClickListener(new CommonAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, String type, int position) {
                switch (position){
                    case 0:
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(LoginActivity.this, QueryWeatherActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(LoginActivity.this, SanFangActivity.class));
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    @Override
    public void onCodeSuccess(CommonResponse commonResponse) {
    }

    @Override
    public void onSuccess(LoginEntity loginEntity) {
        hideProgress();
    }

    @Override
    public void onFails(String exception) {
        hideProgress();
        ToastUtil.showShort(exception);
    }

    private void permissions() {
        final RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
// Must be done during  an initialization phase like onCreate
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            // All requested permissions are granted
                        } else {
                            // At least one permission is denied
                        }
                    }
                });
    }

}
