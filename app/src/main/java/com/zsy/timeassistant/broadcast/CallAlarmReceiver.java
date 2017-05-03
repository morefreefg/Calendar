package com.zsy.timeassistant.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zsy.timeassistant.activity.AlarmSettingAgain;

public class CallAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, AlarmSettingAgain.class);
        intent.putExtra(AlarmSettingAgain.TITLE_EXTRA,
                intent.getStringExtra(AlarmSettingAgain.TITLE_EXTRA));
        intent.putExtra(AlarmSettingAgain.MESSAGE_EXTRA,
                intent.getStringExtra(AlarmSettingAgain.MESSAGE_EXTRA));
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmIntent);
    }

}
