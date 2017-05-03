package com.zsy.timeassistant.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsy.timeassistant.R;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private Context context;
    private Cursor cursor;
    private MyItemClickListener mOnItemClickListener;

    public CardAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false);
        //让adapter 类实现监听
        ViewHolder vh = new ViewHolder(v, mOnItemClickListener);
        return vh;
    }

    //为adapter 创建方法
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        cursor.moveToPosition(position);
        String content = cursor.getString(cursor.getColumnIndex("content"));
        //为viewHolder的itemView设置tag,方便在点击itemView时传出数据
        holder.contenttv.setText(String.valueOf("内容：" + content));

        String time = cursor.getString(cursor.getColumnIndex("time"));
        holder.timetv.setText(String.valueOf("时间：" + time));

        String url = cursor.getString(cursor.getColumnIndex("path"));
        holder.imgiv.setImageBitmap(getImageThumbnail(url, 200, 200));

        String urlvideo = cursor.getString(cursor.getColumnIndex("video"));

    }

    @Override
    public int getItemCount() {
        System.out.print(cursor.getCount());
        return cursor.getCount();
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 获取图库中图片的缩略图
    private Bitmap getImageThumbnail(String uri, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeFile(uri, options);//从SD卡中获得图片
        options.inJustDecodeBounds = false;
        int beWidth = options.outWidth / width;
        int beHeight = options.outHeight / height;
        int be = 1; // 防止图片过小或者没有图片
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }

        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新获取图片(图片为缩略图)
        bitmap = BitmapFactory.decodeFile(uri, options);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    // 获取图库中视频的缩略图
    private Bitmap getVideoThumbnail(String uri, int width, int height, int kind) {
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(uri, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /*
     *定义接口:RecyclerView.setOnItemClickListener
     * 创建item点击的接口回调和设置监听的方法
     */
    public interface MyItemClickListener {
        //将itemView中的tag中数据传出
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    //通过adapter中的静态内部类，来实现ViewHolder类
    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        private TextView contenttv;
        private TextView timetv;
        private ImageView imgiv;
        private ImageView videoiv;
        private MyItemClickListener mOnItemClickListener;

        public ViewHolder(View itemView, MyItemClickListener mOnItemClickListener) {
            super(itemView);
            contenttv = (TextView) itemView.findViewById(R.id.list_content);
            timetv = (TextView) itemView.findViewById(R.id.list_time);
            imgiv = (ImageView) itemView.findViewById(R.id.list_img);
            videoiv = (ImageView) itemView.findViewById(R.id.list_video);

            this.mOnItemClickListener = mOnItemClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        /**
         * 调用接口中的方法
         */
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getPosition());//传入的v对象即点击的itemView
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }
}
