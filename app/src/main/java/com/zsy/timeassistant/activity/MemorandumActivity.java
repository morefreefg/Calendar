package com.zsy.timeassistant.activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zsy.timeassistant.R;
import com.zsy.timeassistant.adapter.MemoAdapter;
import com.zsy.timeassistant.bean.MemoBean;
import com.zsy.timeassistant.broadcast.CallAlarmReceiver;
import com.zsy.timeassistant.db.DbHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * 项目名:    Calendar
 * 描述:     TODO 备忘录
 */
public class MemorandumActivity extends BaseActivity implements OnItemLongClickListener, OnItemClickListener {

    private List<MemoBean> list = new ArrayList<MemoBean>();
    private MemoAdapter adapter;
    private DbHelper helper;
    private SQLiteDatabase database;
    private DatePickerDialog datePickerDialog;//日期选择器
    private int id;
    private Calendar calendar = Calendar.getInstance();
    private TimePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_randum);
        initToolBar("备忘录", false);
        Button button = (Button) findViewById(R.id.add_memo);
        ListView listView = (ListView) findViewById(R.id.list_view);
        helper = new DbHelper(MemorandumActivity.this);
        database = helper.getReadableDatabase();
        adapter = new MemoAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);
        queryMemo();//查询日程记录
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(1);
            }
        });
    }

    //日期选择
    public void selectTime(final int type) {
        Calendar cal = Calendar.getInstance();
        // 显示年月日选择对话框
        datePickerDialog = new DatePickerDialog(MemorandumActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 显示时间选择对话框
                datePickerDialog.dismiss();
                addAlarm(type, year, monthOfYear, dayOfMonth);
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // 查询备忘录
    private void queryMemo() {
        list.clear();
        Cursor cursor = database.query("memo", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                MemoBean memoBean = new MemoBean(time, title, content, id);
                list.add(memoBean);
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();

    }

    /**
     * 显示添加备忘内容对话框
     * type     1--新增  2--修改
     */
    public void showDialog(final int type, final int year, final int monthOfYear, final int dayOfMonth, final String t) {
        View view = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
        final EditText dialogTitle = (EditText) view.findViewById(R.id.dialog_title);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        if (type == 1) {
            dialog.setTitle("请输入内容");// 设置对话框的标题
        } else {
            dialog.setTitle("修改备忘录");// 设置对话框的标题
        }
        dialog.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = editText.getText().toString();
                String title = dialogTitle.getText().toString();

                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(MemorandumActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(MemorandumActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }


                // 广播跳转
                Intent intent = new Intent(MemorandumActivity.this, CallAlarmReceiver.class);
                intent.putExtra(AlarmSettingAgain.MESSAGE_EXTRA, content);
                intent.putExtra(AlarmSettingAgain.TITLE_EXTRA, title);
                // 启动一个广播
                PendingIntent sender = PendingIntent.getBroadcast
                        (MemorandumActivity.this, 0, intent, 0);

                // 创建闹钟
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

                // SharedPreferences保存数据，并提交
                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", t);
                editor.commit();

                String month = monthOfYear + 1 > 10 ? String.valueOf(monthOfYear + 1) : "0" + (monthOfYear + 1);
                String day = dayOfMonth > 10 ? String.valueOf(dayOfMonth) : "0" + dayOfMonth;
                // 保存
                String time = year + "年" + month + "月" + day + "日" + t;
                ContentValues values = new ContentValues();
                values.put("time", time);
                values.put("title", title);
                values.put("content", content);
                database = helper.getWritableDatabase();
                if (type == 1) {
                    database.insert("memo", null, values);
                } else {
                    database.update("memo", values, "id=?", new String[]{String.valueOf(id)});
                }
                dialog.dismiss();
                queryMemo();
            }
        }).create();
        dialog.show();
    }

    //删除备忘录
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("删除")// 设置对话框的标题
                .setMessage("真的要删除吗?")// 设置对话框的内容
                // 设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.delete("memo", "id=?", new String[]{list.get(position).getId() + ""});
                        dialog.dismiss();
                        queryMemo();
                    }
                }).create();
        dialog.show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
        id = list.get(position).getId();
        selectTime(2);
    }

    // 添加时钟
    // 显示时间选择对话框
    private void addAlarm(final int type, final int year, final int monthOfYear, final int dayOfMonth) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);

        dialog = new TimePickerDialog(MemorandumActivity.this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 设置时间
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                String tmpS = format(hourOfDay) + ":" + format(minute);

                dialog.dismiss();
                showDialog(type, year, monthOfYear, dayOfMonth, tmpS);
            }
        }, mHour, mMinute, true);
        dialog.show();
    }

    private String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }
}
