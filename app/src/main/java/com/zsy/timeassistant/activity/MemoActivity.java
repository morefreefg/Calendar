package com.zsy.timeassistant.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.zsy.timeassistant.R;

public class MemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 没有标题
        setContentView(R.layout.activity_memo_details);
        initToolBar("备忘详情", true);
        String stringExtra = getIntent().getStringExtra("details");
        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText(stringExtra);
    }


}
