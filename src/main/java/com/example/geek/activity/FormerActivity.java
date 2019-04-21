package com.example.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.geek.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Currency;

public class FormerActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialCalendarView mCalendarView;
    /**
     * 确定
     */
    private Button mB1;
private CalendarDay currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_former);
        initView();
    }

    private void initView() {
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mB1 = (Button) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        //编辑日历属性
        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        //      设置点击日期的监听
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDate = date;
            }
        });

    }
    /**
     * 获取点击后的日期数
     */







    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.b1:
                if (currentDate != null) {
                    int year = currentDate.getYear();
                    int month = currentDate.getMonth() + 1; //月份跟系统一样是从0开始的，实际获取时要加1
                    int day = currentDate.getDay();
                    Intent intent = getIntent();
                    String data="";
                    if(month<10&&day<10){
                        data=year+"0"+month+"0"+day;
                    }else if(month<10&&day>10){
                        data=year+"0"+month+""+day;
                    }else if(month>10&&day<10){
                        data=year+""+month+"0"+day;
                    }

                    intent.putExtra("data",data);
                    setResult(2,intent);
                    finish();
                }
                break;
        }
    }
}
