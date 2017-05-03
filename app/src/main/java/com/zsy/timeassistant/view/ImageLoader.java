package com.zsy.timeassistant.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/*
 * 项目名:    Calendar
 * 创建时间:  2017/2/24 on 15:18
 * 描述:     TODO  图片加载器
 */
public class ImageLoader implements com.yuyh.library.imgsel.ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
