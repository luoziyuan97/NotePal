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
import android.widget.Toast;

import com.android.luoziyuan.notepal.R;
import com.android.luoziyuan.notepal.model.Note;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by John on 2018/1/16.
 */

public class AddActivity extends Activity
{
    private int type;           //记录的类型

    @Nullable
    @BindView(R.id.addButton_addActivity)
    Button addButton;

    @Nullable
    @BindView(R.id.themeText_addActivity)
    EditText themeText;

    @Nullable
    @BindView(R.id.contentText_addActivity)
    EditText contentText;

    @Optional
    @OnClick(R.id.addButton_addActivity)
    public void addNote(View view)              //添加按键的响应事件
    {
        String theme = themeText.getText().toString();
        String content = contentText.getText().toString();
        if (theme.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("好歹写个主题吧_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("type",type);
        intent.putExtra("theme",theme);
        intent.putExtra("content",content);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Nullable
    @BindView(R.id.themeText_addexActivity)
    EditText themeTextEx;

    @Nullable
    @BindView(R.id.dateText_addexActivity)
    TextView dateText;

    @Nullable
    @BindView(R.id.timeText_addexActivity)
    TextView timeText;

    @Nullable
    @BindView(R.id.placeText_addexActivity)
    EditText placeText;

    @Nullable
    @BindView(R.id.contentText_addexActivity)
    EditText contentTextEx;

    @Nullable
    @BindView(R.id.addButton_addexActivity)
    Button addButtonEx;

    @Optional
    @OnClick(R.id.dateText_addexActivity)
    public void setDate()                   //点击dateText设置日期
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);   //月从1开始
        int day = calendar.get(Calendar.DAY_OF_MONTH);
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
    @OnClick(R.id.timeText_addexActivity)
    public void setTime()               //点击timeText设置时间
    {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
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
    @OnClick(R.id.addButton_addexActivity)
    public void addNoteEx(View view)
    {
        String theme = themeTextEx.getText().toString();
        String content = contentTextEx.getText().toString();
        String date = dateText.getText().toString();
        String time = timeText.getText().toString();
        String place = placeText.getText().toString();
        if (theme.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("好歹告诉我你考啥吧_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        if (date.equals("点击设置日期"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("你没说考试日期啊_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        if (time.equals("点击设置时间"))
            time = "";
        Intent intent = new Intent();
        intent.putExtra("type",type);
        intent.putExtra("subject",theme);
        intent.putExtra("description",content);
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        intent.putExtra("place",place);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getIntExtra("type",-1);
        if (type == Note.TYPE_NOTE || type == Note.TYPE_HOMEWORK)
            setContentView(R.layout.activity_add);
        else
            setContentView(R.layout.activity_addex);

        ButterKnife.bind(this);

        switch (type)
        {
            case Note.TYPE_NOTE:
                break;
            case Note.TYPE_EXAM:
                break;
            case Note.TYPE_HOMEWORK:
                themeText.setHint("科目");
                break;
            case Note.TYPE_AFFAIR:
                break;
        }
    }
}
