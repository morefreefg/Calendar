package com.zsy.timeassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * 项目名:    Calendar
 * 描述:     TODO 数据库管理类
 */
public class DbHelper extends SQLiteOpenHelper {

	public DbHelper(Context context) {
		super(context, "time.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建用户信息表
		db.execSQL("create table user(id integer primary key autoincrement,userName varchar(20),password varchar(20))");
		// 创建用户备忘表
		db.execSQL(
				"create table memo(id integer primary key autoincrement,time varchar(20),title varchar(40),content varchar(100))");
		// 创建用户记录表
		db.execSQL(
				"create table noto(id integer primary key autoincrement,time varchar(20),title varchar(40),content varchar(100),pic varchar(100),audio varchar(100))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
