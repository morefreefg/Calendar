package com.zsy.timeassistant.broadcast;

import com.zsy.timeassistant.activity.AlarmSettingAgain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent alarmIntent = new Intent(context, AlarmSettingAgain.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmIntent);
	}

}
