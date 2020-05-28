package com.yimeiduo.business.util;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yimeiduo.business.Constant;
import com.yimeiduo.business.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        //Glide 加载图片简单用法
        Glide.with(activity)
                .load(path)
               // .dontAnimate()
                .placeholder(R.drawable.no_banner)
//                .apply(new RequestOptions().placeholder(R.drawable.no_banner))
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

        if(!TextUtils.isEmpty(path)){
            if(path.contains(Constant.HTTP)){
                Glide.with(activity)                             //配置上下文
                        .load(path)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                      //  .dontAnimate()
                        .into(imageView);
            }else{
                Glide.with(activity)                             //配置上下文
                        .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                      //  .dontAnimate()
                        .into(imageView);
            }
        }

    }

    @Override
    public void clearMemoryCache() {

    }
}
