package com.zsy.timeassistant.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zsy.timeassistant.R;

/*
 * 项目名:    Calendar
 * 描述:     所有Activity的基类,这里对一些常用放进行封装
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    /**
     * 显示吐司
     * @param context 上下文
     * @param msg     显示的内容
     */
    private long lastTime = 0;
    Toast toast = null;

    public void showToast(Context context, String msg) {
//        获得当前日期
        long nowTime = System.currentTimeMillis();
        if (nowTime - lastTime < 2000) {
            if (toast != null) {
                toast.cancel();
            }
        }
        //toast.LENGTH_SHORT 显示时长较短
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);

        lastTime = nowTime;
    }
    /**
     * 判断手机网络是否连接
     * @param context 上下文
     * @return true 有网 |false 无网络
     */
    public boolean isNetWorkAvailable(Context context) {
        //ConnectivityManager主要管理与网络连接相关的操作
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            //获取代表联网状态的NetWorkInfo 对象
            NetworkInfo info = manager.getActiveNetworkInfo();
            return info != null && info.isConnected();
        } else {
            return false;
        }
    }
    /**
     * 初始化标题栏
     */
    public Toolbar initToolBar(String title, boolean isBack) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //设置透明度（0~255）
        toolbar.getBackground().setAlpha(255);
        toolbar.setTitle("");
        //取代原本的ActionBar(ActionBar使用方式不灵活）
        setSupportActionBar(toolbar);
        TextView tv = (TextView) findViewById(R.id.tool_bar_title);
        tv.setText(title);
        if (isBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //设置NavigationIcon点击事件（需放在setSupportActionBar后设置才会生效）
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        return toolbar;
    }
    /**
     * 弹出progressdialog进度对话框
     */
    ProgressDialog dialog;

    public void showDialog(String Msg) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            //设置在dialog外是否取消dialog进度条
            dialog.setCanceledOnTouchOutside(true);
        }
        dialog.setMessage(Msg);
        dialog.show();
    }
    /**
     * 消失dialog
     */
    public void dimissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}

