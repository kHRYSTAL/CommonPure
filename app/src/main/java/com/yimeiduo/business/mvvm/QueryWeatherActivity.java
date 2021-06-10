package com.yimeiduo.business.mvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import com.yimeiduo.business.R;
import com.yimeiduo.business.databinding.ActivityWeatherBinding;
import com.yimeiduo.business.mvvm.viewmodel.QueryWeatherViewModel;


public class QueryWeatherActivity extends AppCompatActivity {

    // ViewModel
    private QueryWeatherViewModel mViewModel;

    // DataBinding
    private ActivityWeatherBinding mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_weather);

        // 创建ViewModel
        mViewModel = new QueryWeatherViewModel();
        // 绑定View和ViewModel
        mDataBinding.setViewModel(mViewModel);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消请求
        mViewModel.cancelRequest();
    }

}
