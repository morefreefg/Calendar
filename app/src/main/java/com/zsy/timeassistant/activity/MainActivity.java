package com.zsy.timeassistant.activity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.zsy.timeassistant.R;

/*
 * 项目名:    Calendar
 * 描述:     TODO 主页面
 */
public class MainActivity extends BaseActivity {

    // 用于添加每一个选项卡的id
    private String[] tags = {"B_tag", "C_tag", "D_tag"};
    // 所添加选项卡的文本信息
    private String[] titles = {"万年历", "闹钟", "备忘录"};
    // 用于跳转至不同的Activity
    private Intent[] intents = new Intent[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
        tabWidget.setBackgroundColor(Color.WHITE);
        // 初始化activity管理者
        LocalActivityManager manager = new LocalActivityManager(MainActivity.this, false);
        // 通过管理者保存当前页面状态
        manager.dispatchCreate(savedInstanceState);
        // 将管理者类对象添加至TabHost
        tabHost.setup(manager);
        initIntent();
        for (int i = 0; i < intents.length; i++) {
            // 加载底部导航栏布局
            LayoutInflater inflater = this.getLayoutInflater();
            View view = inflater.inflate(R.layout.tab, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_item);
            textView.setText(titles[i]);
            // 创建选项卡
            TabHost.TabSpec spec = tabHost.newTabSpec(tags[i]);
            spec.setIndicator(view);
            // 设置每个页面的内容
            spec.setContent(intents[i]);
            // 将创建的选项卡添加至tabHost上
            tabHost.addTab(spec);
        }
    }

    // 每个页面放置的Activity
    public void initIntent() {
        intents[1] = new Intent(this, AlarmClockActivity.class);
        intents[0] = new Intent(this, CalendarActivity.class);
        intents[2] = new Intent(this, MemorandumActivity.class);
    }
}
