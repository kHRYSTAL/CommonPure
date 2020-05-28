package com.yimeiduo.business.widget.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yimeiduo.business.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class InformationTipsDialog extends BaseDialog {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.btn_positive)
    TextView btn_positive;

    private String content;
    private String type;
    private String title;
    private Activity activity;

    public InformationTipsDialog(Activity activity, String title) {
        super(activity);
        this.title = title;
        this.activity = activity;
    }

    public InformationTipsDialog(Activity activity, String title, String content) {
        super(activity);
        this.title = title;
        this.content = content;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_information_tips);
        ButterKnife.bind(this);

        tv_title.setText(title);
        if (!TextUtils.isEmpty(content)){
            tv_content.setVisibility(View.VISIBLE);
            tv_content.setText(content);
        }
    }


    @OnClick({R.id.btn_positive,R.id.btn_negative})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_positive:
                onPositive(this);
                break;
            case R.id.btn_negative:
                onNegative(this);
                break;
        }
    }


    public abstract void onPositive(InformationTipsDialog dialog);
    public abstract void onNegative(InformationTipsDialog dialog);

}
