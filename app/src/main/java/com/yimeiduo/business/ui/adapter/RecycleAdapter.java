package com.yimeiduo.business.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yimeiduo.business.Constant;
import com.yimeiduo.business.R;
import com.yimeiduo.business.entity.response.CommonBean;

import java.util.List;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;
    private List<String> list;
    private LayoutInflater layoutInflater;

    public RecycleAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycle_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String info = list.get(position);
        Glide.with(context)
                .load(info)
//                .load(bean.getOrdersGoodsVoList().get(0).getImageSrc())
//                    .apply(new RequestOptions().placeholder(R.mipmap.ic_moren))
                .placeholder(R.mipmap.ic_logo)
                .dontAnimate()
                .into(holder.iv_url);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_url;

        MyViewHolder(View view) {
            super(view);
            iv_url = view.findViewById(R.id.iv_url);
        }
    }

    /**
     * 按钮的监听接口
     */
    public interface onItemClickListener {
        void onItemClick(View view,String type,int position);
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener mOnItemDeleteListener) {
        this.onItemClickListener = mOnItemDeleteListener;
    }

}
