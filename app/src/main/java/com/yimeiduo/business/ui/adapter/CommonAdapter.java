package com.yimeiduo.business.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yimeiduo.business.Constant;
import com.yimeiduo.business.R;
import com.yimeiduo.business.entity.response.CommonBean;

import java.util.List;


public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.MyViewHolder> {

    private Context context;
    private List<CommonBean> list;
    private LayoutInflater layoutInflater;

    public CommonAdapter(Context context, List<CommonBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_common_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CommonBean info = list.get(position);
       /* Glide.with(context)
                .load(info)
//                .load(bean.getOrdersGoodsVoList().get(0).getImageSrc())
//                    .apply(new RequestOptions().placeholder(R.mipmap.ic_moren))
                .placeholder(R.mipmap.ic_logo)
                .dontAnimate()
                .into(holder.iv_photo);*/

        holder.tv_name.setText(info.getName());
        switch (info.getState()){
            case 0:
                break;
            case 1:
                holder.tv_state.setText("可以");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_state;

        MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_state = view.findViewById(R.id.tv_state);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, Constant.ITEM,getLayoutPosition());
                    }
                }
            });
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
