package com.zsy.timeassistant.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zsy.timeassistant.R;
import com.zsy.timeassistant.db.NoteDB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * 项目名:    Calendar
 * 包名       com.zsy.timeassistant.activity
 * 文件名:    AddNoteActivity
 * 创建时间:  2017/4/22 on 13:27
 * 描述:     TODO 随身记
 */
public class AddNoteActivity extends BaseActivity implements View.OnClickListener {
    private NoteDB noteDB;
    private SQLiteDatabase dbWriter;
    private Button savebtn, cancelbtn;
    private EditText edtext;
    private ImageView c_img;
    private TextView tx_add;

    private String imageUrl;//图片路径

    @Override
    //创建onCreate 方法
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontent);
        //调用baseactivity的inittoolbar{}方法
        initToolBar("添加记录", true);
        noteDB = new NoteDB(this);
        dbWriter = noteDB.getWritableDatabase();//以读写方式打开数据库

        tx_add = (TextView) findViewById(R.id.add);
        tx_add.setOnClickListener(this);
        savebtn = (Button) findViewById(R.id.bt_save);
        savebtn.setOnClickListener(this);
        cancelbtn = (Button) findViewById(R.id.bt_cancle);
        cancelbtn.setOnClickListener(this);
        edtext = (EditText) findViewById(R.id.edtext);
        c_img = (ImageView) findViewById(R.id.c_img);

    }

    //添加数据
    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT, edtext.getText().toString());
        cv.put(NoteDB.TIME, getTime());
        cv.put(NoteDB.PATH, imageUrl + "");
        cv.put(NoteDB.VIDEO, "");
        dbWriter.insert(NoteDB.TABLE_NAME, null, cv);
        showToast(AddNoteActivity.this, "添加成功");
        finish();
    }

    //格式化输出日期
    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);   //  yyyy年MM月dd日 HH:mm:ss
        return str;
    }

    //添加图片
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                /**
                 * 选择图片
                 */
                // 自由配置选项
                ImgSelConfig config = new ImgSelConfig.Builder(this, new com.zsy.timeassistant.view.ImageLoader())
                        // 是否多选
                        .multiSelect(false)
                        // “确定”按钮背景色
                        .btnBgColor(Color.parseColor("#00000000"))
                        // “确定”按钮文字颜色
                        .btnTextColor(Color.WHITE)
                        // 使用沉浸式状态栏
                        .statusBarColor(getResources().getColor(R.color.colorPrimary))
                        // 返回图标ResId
                        .backResId(R.drawable.back)
                        // 标题
                        .title("图片")
                        // 标题文字颜色
                        .titleColor(Color.WHITE)
                        // TitleBar背景色
                        .titleBgColor(getResources().getColor(R.color.colorPrimaryDark))
                        // 裁剪大小。needCrop为true的时候配置
                        .cropSize(1, 1, 1024, 1024)
                        .needCrop(true)
                        // 第一个是否显示相机
                        .needCamera(true)
                        // 最大选择图片数量
                        .maxNum(1)
                        .build();
                // 跳转到图片选择器
                ImgSelActivity.startActivity(this, config, 1);

                break;
            case R.id.bt_save:
                addDB();
                break;
            case R.id.bt_cancle:
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (String path : pathList) {
                    buffer.append(path);
                }
                imageUrl = pathList.get(0);
                Bitmap bitmap = BitmapFactory.decodeFile(pathList.get(0));
                c_img.setImageBitmap(bitmap);
            }

        }
    }
}
