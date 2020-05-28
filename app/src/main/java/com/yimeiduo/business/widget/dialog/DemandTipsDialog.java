package com.yimeiduo.business.widget.dialog;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yimeiduo.business.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class DemandTipsDialog extends BaseDialog {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.ll_1)
    LinearLayout ll_1;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.ll_2)
    LinearLayout ll_2;
    @BindView(R.id.tv_2)
    TextView tv_2;

    @BindView(R.id.btn_negative)
    TextView btn_negative;

    private String title;
    private Activity activity;
    private String content;
    private boolean leftVisiable;

    public DemandTipsDialog(Activity activity, String title, String content, boolean leftVisiable) {
        super(activity);
        this.title = title;
        this.activity = activity;
        this.content = content;
        this.leftVisiable = leftVisiable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_demand_tips);
        ButterKnife.bind(this);

        if (leftVisiable){
            btn_negative.setVisibility(View.VISIBLE);
            ll_2.setVisibility(View.VISIBLE);
            tv_1.setText("请确认使用公司钱包金额支付");
            tv_2.setText("本次资金现结需求支付金额："+content+"元");
        }else{
            tv_1.setText(content);
        }
        tv_title.setText(title);
    }

    @OnClick({R.id.btn_positive,R.id.btn_negative})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_positive:
                onPositive(this,content);
                break;
            case R.id.btn_negative:
                onNegative(this);
                break;
        }
    }

    public abstract void onPositive(DemandTipsDialog dialog, String content);
    public abstract void onNegative(DemandTipsDialog dialog);

}
