package com.zsy.timeassistant.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.zsy.timeassistant.R;
import com.zsy.timeassistant.adapter.CardAdapter;
import com.zsy.timeassistant.db.NoteDB;


public class NoteListActivity extends BaseActivity implements CardAdapter.MyItemClickListener, AdapterView.OnItemLongClickListener {
    private RecyclerView rv;
    private RecyclerView.LayoutManager mLayoutmanager;//设置item 项排列方式
    private Intent intent;
    private CardAdapter adapter;
    private NoteDB noteDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        initToolBar("随身记列表", true);
        initView();
    }

    //设置视图
    public void initView() {
        rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutmanager);

        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();

    }

    @Override
    public void onItemClick(View view, int position) {
        cursor.moveToPosition(position);
        //新增随身记
        Intent intent = new Intent(NoteListActivity.this, ShowNoteActivity.class);
        intent.putExtra(ShowNoteActivity.TEXT_EXTRA,
                cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT)));
        intent.putExtra(ShowNoteActivity.IMAGE_EXTRA,
                cursor.getString(cursor.getColumnIndex(NoteDB.PATH)));
//        intent.putExtra(NoteDB.ID,
//                cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
//        intent.putExtra(NoteDB.CONTENT,
//                cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT)));
//        intent.putExtra(NoteDB.TIME,
//                cursor.getString(cursor.getColumnIndex(NoteDB.TIME)));
//        intent.putExtra(NoteDB.PATH,
//                cursor.getString(cursor.getColumnIndex(NoteDB.PATH)));
//        intent.putExtra(NoteDB.VIDEO,
//                cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO)));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除此条记录？");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                noteDB.delete(position, cursor);
                selectDB();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


    public void selectDB() {
        cursor = dbReader.query(NoteDB.TABLE_NAME, null, null, null, null, null, null);
        adapter = new CardAdapter(this, cursor);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

    //从xml 文件中加载显示在菜单的视图
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                startActivity(new Intent(this, AddNoteActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //删除随身记
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("删除")// 设置对话框的标题
//                .setMessage("真的要删除吗?")// 设置对话框的内容
//                // 设置对话框的按钮
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dbReader.delete("note", "id=?", new String[]{noteDB.get(position).getId() + ""});
//                        dialog.dismiss();
//               //         queryMemo();
//                    }
//                }).create();
//        dialog.show();
        return false;
    }
}
