package com.android.luoziyuan.notepal.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.luoziyuan.notepal.R;
import com.android.luoziyuan.notepal.model.Note;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by John on 2018/1/20.
 */

public class UpdateActivity extends Activity
{
    private String theme;
    private String content;
    private String date;
    private String time;
    private String place;
    private int position;
    private int type;           //记录选中的记录的类型

    @Nullable
    @BindView(R.id.themeText_updateNote)
    EditText themeText;

    @Nullable
    @BindView(R.id.contentText_updateNote)
    EditText contentText;

    @Nullable
    @BindView(R.id.okButton_updateNote)
    Button okButton;

    @Nullable
    @BindView(R.id.cancelButton_updateNote)
    Button cancelButton;

    @Optional
    @OnClick(R.id.cancelButton_updateNote)
    public void cancelButtonClick(View view)       //点击取消按钮，直接返回
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Optional
    @OnClick(R.id.okButton_updateNote)
    public void okButtonClick(View view)        //点击确定按钮，检测是否更改，更改则回传数据
    {
        String newTheme = themeText.getEditableText().toString();
        String newContent = contentText.getEditableText().toString();
        if (newTheme.length() == 0)             //主题为空时给出提示
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("好歹写个主题吧_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        if (newTheme.equals(theme) && newContent.equals(content))   //检测是否更改
            intent.putExtra("isChanged",false);
        else
        {                                                       //回传更改后的数据
            intent.putExtra("isChanged",true);
            intent.putExtra("type",type);
            intent.putExtra("theme",newTheme);
            intent.putExtra("content",newContent);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

//*************************************************************************************
//  Exam和Affair所用的布局内容

    @Nullable
    @BindView(R.id.themeText_updateExam)
    EditText themeTextEx;

    @Nullable
    @BindView(R.id.dateText_updateExam)
    TextView dateText;

    @Nullable
    @BindView(R.id.timeText_updateExam)
    TextView timeText;

    @Nullable
    @BindView(R.id.placeText_updateExam)
    EditText placeText;

    @Nullable
    @BindView(R.id.contentText_updateExam)
    EditText contentTextEx;

    @Nullable
    @BindView(R.id.okButton_updateExam)
    Button okButtonEx;

    @Nullable
    @BindView(R.id.cancelButton_updateExam)
    Button cancelButtonEx;

    @Optional
    @OnClick(R.id.dateText_updateExam)
    public void setDate()                   //点击dateText设置日期
    {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7)) - 1;  //传给DatePicker的月份从0开始
        int day = Integer.parseInt(date.substring(8,10));
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + ".";
                if (month < 9)
                    date += "0" + (month + 1);
                else
                    date += (month + 1);
                date += ".";
                if (dayOfMonth < 10)
                    date += "0" + dayOfMonth;
                else
                    date += dayOfMonth;
                dateText.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.timeText_updateExam)
    public void setTime()               //点击timeText设置时间
    {
        time = timeText.getText().toString();
        int hour;
        int minute;
        if (time.equals("点击设置时间"))          //如果之前没有设置时间
        {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }
        else            //之前已经设置过时间，按格式获取
        {
            try {
                hour = Integer.parseInt(time.substring(0, 2));  //小时的长度可能1位或2位
                minute = Integer.parseInt(time.substring(3, 5));
            } catch(NumberFormatException e){
                hour = Integer.parseInt(time.substring(0,1));
                minute = Integer.parseInt(time.substring(2,4));
            }

        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":";
                if (minute < 10)
                    time += "0" + minute;
                else
                    time += minute;
                timeText.setText(time);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.cancelButton_updateExam)
    public void cancelButtonExClick(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Optional
    @OnClick(R.id.okButton_updateExam)
    public void okButtonExClick()
    {
        String newTheme = themeTextEx.getEditableText().toString();
        String newContent = contentTextEx.getEditableText().toString();
        String newDate = dateText.getText().toString();
        String newTime = timeText.getText().toString();
        String newPlace = placeText.getText().toString();
        if (newTheme.length() == 0)             //主题为空时给出提示
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("好歹告诉我你考啥吧_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        if (newTheme.equals(theme) && newContent.equals(content) && newDate.equals(date)
                && newTime.equals(time) && newPlace.equals(place))   //检测是否更改
            intent.putExtra("isChanged",false);
        else
        {                                                       //回传更改后的数据
            if (newTime.equals("点击设置时间"))
                newTime = "";
            intent.putExtra("isChanged",true);
            intent.putExtra("type",type);
            intent.putExtra("theme",newTheme);
            intent.putExtra("content",newContent);
            intent.putExtra("date",newDate);
            intent.putExtra("time",newTime);
            intent.putExtra("place",newPlace);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

//*************************************************************************************
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");
        type = getIntent().getIntExtra("type",-1);
        switch(type)                //根据记录类型加载布局和内容
        {
            case Note.TYPE_NOTE:
                setContentView(R.layout.update_note);
                ButterKnife.bind(this);
                theme = bundle.getString("theme");
                content = bundle.getString("content");
                themeText.setText(theme);
                contentText.setText(content);
                break;
            case Note.TYPE_EXAM:
                setContentView(R.layout.update_exam);
                ButterKnife.bind(this);
                theme = bundle.getString("subject");
                content = bundle.getString("description");
                date = bundle.getString("date");
                time = bundle.getString("time");
                place = bundle.getString("place");
                themeTextEx.setText(theme);
                dateText.setText(date);
                if (time.length() == 0)
                    timeText.setText("点击设置时间");
                else
                    timeText.setText(time);
                placeText.setText(place);
                contentTextEx.setText(content);
                break;
        }
    }
}
