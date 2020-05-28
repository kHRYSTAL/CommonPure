package com.yimeiduo.business.util;

import android.content.Context;
import android.widget.ImageView;

import com.yimeiduo.business.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
       /* //Glide 加载图片简单用法
        Glide.with(context)
                .load(path)
                // .dontAnimate()
//                .placeholder(R.drawable.no_banner)
                .apply(new RequestOptions().placeholder(R.drawable.no_banner))
                .into(imageView);*/

        //Picasso 加载图片简单用法
        Picasso.with(context).load((String)path).placeholder(R.drawable.no_banner).into(imageView);

    }
}
