package com.zsy.timeassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.zsy.timeassistant.R;

import java.util.Calendar;

/*
 * 项目名:    Calendar
 * 创建时间:  2017/4/22 on 10:58
 * 描述:     TODO
 */
public class CalendarActivity  extends BaseActivity {
    private CalendarView calendarView;
    private Button buttonWeather,button_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView= (CalendarView) findViewById(R.id.calendarView);
        buttonWeather= (Button) findViewById(R.id.button_weather);
        button_note= (Button) findViewById(R.id.button_note);
        //取得calendar实例
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        String date = "今天是 "+year + "年" + month + "月" + day +"日";
        Toast.makeText(CalendarActivity.this, date, Toast.LENGTH_LONG).show();
        //显示选择日期
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "年" + (month+1) + "月" + dayOfMonth +"日";
                Toast.makeText(CalendarActivity.this, date, Toast.LENGTH_LONG).show();

            }
        });

        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalendarActivity.this,WeatherActivity.class));
            }
        });

        button_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalendarActivity.this,NoteListActivity.class));
            }
        });
    }
}
