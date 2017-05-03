package com.zsy.timeassistant.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zsy.timeassistant.R;
import com.zsy.timeassistant.broadcast.CallAlarmReceiver;

import java.util.Calendar;

/*
 * 项目名:    Calendar
 * 描述:     TODO 闹钟
 */
public class AlarmClockActivity extends BaseActivity {

	private Calendar calendar = Calendar.getInstance();//通过该变量获取时间
	private TextView time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_clock);
		time = (TextView) findViewById(R.id.time);
		time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addAlarm();
			}
		});
	}

	// 添加闹钟
	private void addAlarm() {
		// TODO Auto-generated method stub
		calendar.setTimeInMillis(System.currentTimeMillis());
		int mHour = calendar.get(Calendar.HOUR_OF_DAY);
		int mMinute = calendar.get(Calendar.MINUTE);

		new TimePickerDialog(AlarmClockActivity.this, new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// 设置时间
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);

				// 广播跳转
				Intent intent = new Intent(AlarmClockActivity.this, CallAlarmReceiver.class);
				// 启动一个广播
				PendingIntent sender = PendingIntent.getBroadcast(AlarmClockActivity.this, 0, intent, 0);
				// 创建闹钟
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

				String tmpS = format(hourOfDay) + ":" + format(minute);
				time.setText(tmpS);

				// SharedPreferences保存数据，并提交
				SharedPreferences time1Share = getPreferences(0);
				SharedPreferences.Editor editor = time1Share.edit();
				editor.putString("TIME1", tmpS);
				editor.commit();

				Toast.makeText(AlarmClockActivity.this, "设置闹钟时间为" + tmpS, Toast.LENGTH_SHORT).show();
			}
		}, mHour, mMinute, true).show();
	}

	private String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}
}
