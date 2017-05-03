package com.zsy.timeassistant.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;

import com.zsy.timeassistant.broadcast.CallAlarmReceiver;

import java.util.Calendar;

public class AlarmSettingAgain extends Activity {

	private MediaPlayer mp = new MediaPlayer();//音频
	private Vibrator vibrator;//震动
	private PowerManager.WakeLock mWakelock;//唤醒屏幕

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 没有标题
		//设置屏幕不能锁屏，确保在锁屏状态下弹出dialog不会出错
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		winParams.flags |= (WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		startMedia();
		startVibrator();
		createDialog();
	}

    //锁屏状态下唤醒屏幕
	@Override
	protected void onResume() {
		super.onResume();
		// 唤醒屏幕
		acquireWakeLock();
	}

	@Override
	protected void onPause() {
		super.onPause();
		releaseWakeLock();
	}
	/**
	 * 唤醒屏幕
	 */
	private void acquireWakeLock() {
		if (mWakelock == null) {
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,
					this.getClass().getCanonicalName());
			mWakelock.acquire();
		}
	}

	/**
	 * 释放锁屏
	 */
	private void releaseWakeLock() {
		if (mWakelock != null && mWakelock.isHeld()) {
			mWakelock.release();
			mWakelock = null;
		}
	}
	//弹出dialog对话框
	private void createDialog() {
		new AlertDialog.Builder(this).setTitle("闹钟提醒时间到了!").setMessage("闹钟时间到了!!!")
				.setPositiveButton("推迟10分钟", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						tenMRemind();
						mp.stop();
						vibrator.cancel();
						finish();
					}
				}).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				mp.stop();
				vibrator.cancel();
				finish();
			}
		}).show();
	}
	/**
	 * 推迟10分钟提醒
	 */
	private void tenMRemind() {
		// 设置时间
		Calendar calendar_now = Calendar.getInstance();

		calendar_now.setTimeInMillis(System.currentTimeMillis());
		calendar_now.set(Calendar.HOUR_OF_DAY, calendar_now.get(Calendar.HOUR_OF_DAY));
		calendar_now.set(Calendar.MINUTE, calendar_now.get(Calendar.MINUTE) + 10);
		calendar_now.set(Calendar.SECOND, 0);
		calendar_now.set(Calendar.MILLISECOND, 0);

		// 时间选择好了
		Intent intent = new Intent(this, CallAlarmReceiver.class);
		// 注册闹钟广播
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager am;
		am = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar_now.getTimeInMillis(), sender);
	}

	/**
	 * 开始播放铃声
	 */
	private void startMedia() {
		try {
			//闹钟类型为默认闹钟铃声
			mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
			mp.prepare();
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 震动
	 */
	private void startVibrator() {
		/**
		 * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
		 */
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 500, 1000, 500, 1000 }; // 停止 开启 停止 开启
		vibrator.vibrate(pattern, 0);
	}


}
