package com.zsy.timeassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsy.timeassistant.R;
import com.zsy.timeassistant.bean.MemoBean;

import java.util.List;

public class MemoAdapter extends BaseAdapter {
    private Context context;
    private List<MemoBean> list;

    public MemoAdapter(Context context, List<MemoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.memo_item, null);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView content = (TextView) view.findViewById(R.id.content);
        time.setText("提醒时间：" + list.get(position).getTime());
        title.setText("标题：" + list.get(position).getTitle());
        content.setText("内容：" + list.get(position).getContent());
        return view;
    }

}
