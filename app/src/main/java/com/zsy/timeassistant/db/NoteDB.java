package com.zsy.timeassistant.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NoteDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    public static final String PATH = "path";
    public static final String CONTENT = "content";
    public static final String VIDEO = "video";
    public static final String ID = "_id";
    public static final String TIME = "time";

    public NoteDB(Context context) {
        super(context, "notes", null, 1);
    }

    //数据库文件第一次创建时调用，建立表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "("
                + ID + " Integer primary key autoincrement,"
                + CONTENT + " text not null,"
                + PATH + " text not null,"
                + VIDEO + " text not null,"
                + TIME + " text not null" + ")");
    }

    public void delete(int position, Cursor cursor) {
        cursor.moveToPosition(position);
        this.getWritableDatabase().delete(TABLE_NAME, CONTENT + " = ?",
                new String[]{cursor.getString(cursor.getColumnIndex(CONTENT))});
        cursor.moveToFirst();
    }

    @Override
    //更新数据库版本号
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
