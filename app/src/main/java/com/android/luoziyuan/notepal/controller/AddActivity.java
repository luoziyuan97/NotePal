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
 * Created by John on 2018/1/16.
 */

public class AddActivity extends Activity
{
    private int type;           //记录的类型

//  添加Note类型记录所用的布局
    @Nullable
    @BindView(R.id.addButton_addNote)
    Button addButton_Note;

    @Nullable
    @BindView(R.id.themeText_addNote)
    EditText themeText_Note;

    @Nullable
    @BindView(R.id.contentText_addNote)
    EditText contentText_Note;

    @Optional
    @OnClick(R.id.addButton_addNote)
    public void addNote(View view)              //添加按键的响应事件
    {
        String theme = themeText_Note.getText().toString();
        String content = contentText_Note.getText().toString();
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

//*************************************************************************************
//  添加Exam类型记录所用的布局

    @Nullable
    @BindView(R.id.subjectText_addExam)
    EditText subjectText_Exam;

    @Nullable
    @BindView(R.id.dateText_addExam)
    TextView dateText_Exam;

    @Nullable
    @BindView(R.id.timeText_addExam)
    TextView timeText_Exam;

    @Nullable
    @BindView(R.id.placeText_addExam)
    EditText placeText_Exam;

    @Nullable
    @BindView(R.id.descriptionText_addExam)
    EditText descriptionText_Exam;

    @Nullable
    @BindView(R.id.addButton_addExam)
    Button addButton_Exam;

    @Optional
    @OnClick(R.id.dateText_addExam)
    public void setDate_Exam()                   //点击dateText设置日期
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
                dateText_Exam.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.timeText_addExam)
    public void setTime_Exam()               //点击timeText设置时间
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
                timeText_Exam.setText(time);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.addButton_addExam)
    public void addExam(View view)
    {
        String subject = subjectText_Exam.getText().toString();
        String description = descriptionText_Exam.getText().toString();
        String date = dateText_Exam.getText().toString();
        String time = timeText_Exam.getText().toString();
        String place = placeText_Exam.getText().toString();
        if (subject.length() == 0)
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
        intent.putExtra("subject",subject);
        intent.putExtra("description",description);
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        intent.putExtra("place",place);
        setResult(RESULT_OK,intent);
        finish();
    }

//*****************************************************************************************
//  添加Homework类型的记录所用的布局

    @Nullable
    @BindView(R.id.subjectText_addHomework)
    EditText subjectText_Homework;

    @Nullable
    @BindView(R.id.descriptionText_addHomework)
    EditText descriptionText_Homework;

    @Nullable
    @BindView(R.id.deadlineText_addHomework)
    TextView deadlineText_Homework;

    @Nullable
    @BindView(R.id.addButton_addHomework)
    Button addButton_Homework;

    @Optional
    @OnClick(R.id.deadlineText_addHomework)
    public void setDeadline_Homework()                   //点击deadlineText设置日期
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
                deadlineText_Homework.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.addButton_addHomework)
    public void addHomework(View view)
    {
        String subject = subjectText_Homework.getText().toString();
        String description = descriptionText_Homework.getText().toString();
        String deadline = deadlineText_Homework.getText().toString();
        if (subject.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("好歹告诉我哪门课吧_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        if (deadline.equals("点击设置deadline"))
        {
            deadline = "";
        }
        Intent intent = new Intent();
        intent.putExtra("type",type);
        intent.putExtra("subject",subject);
        intent.putExtra("description",description);
        intent.putExtra("deadline",deadline);
        setResult(RESULT_OK,intent);
        finish();
    }

//*****************************************************************************************
//  添加Affair类型记录所用的布局

    @Nullable
    @BindView(R.id.themeText_addAffair)
    EditText themeText_Affair;

    @Nullable
    @BindView(R.id.descriptionText_addAffair)
    EditText descriptionText_Affair;

    @Nullable
    @BindView(R.id.placeText_addAffair)
    EditText placeText_Affair;

    @Nullable
    @BindView(R.id.dateText_addAffair)
    TextView dateText_Affair;

    @Nullable
    @BindView(R.id.timeText_addAffair)
    TextView timeText_Affair;

    @Nullable
    @BindView(R.id.addButton_addAffair)
    Button addButton_Affair;

    @Optional
    @OnClick(R.id.dateText_addAffair)
    public void setDate_Affair()                   //点击dateText设置日期
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
                dateText_Affair.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.timeText_addAffair)
    public void setTimeText_Affair()               //点击timeText设置时间
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
                timeText_Affair.setText(time);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.addButton_addAffair)
    public void addAffair(View view)
    {
        String theme = themeText_Affair.getText().toString();
        String description = descriptionText_Affair.getText().toString();
        String date = dateText_Affair.getText().toString();
        String time = timeText_Affair.getText().toString();
        String place = placeText_Affair.getText().toString();
        if (theme.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("好歹告诉我啥事儿啊_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        if (date.equals("点击设置日期"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("那啥");
            builder.setMessage("你没告诉我日期_(:з」∠)_");
            builder.setPositiveButton("是是是",null);
            builder.show();
            return;
        }
        if (time.equals("点击设置时间"))
            time = "";
        Intent intent = new Intent();
        intent.putExtra("type",type);
        intent.putExtra("theme",theme);
        intent.putExtra("description",description);
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        intent.putExtra("place",place);
        setResult(RESULT_OK,intent);
        finish();
    }

//*****************************************************************************************
//  onCreate

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getIntExtra("type",-1);
        switch (type)                       //根据记录类型加载布局
        {
            case Note.TYPE_NOTE:
                setContentView(R.layout.add_note);
                break;
            case Note.TYPE_EXAM:
                setContentView(R.layout.add_exam);
                break;
            case Note.TYPE_HOMEWORK:
                setContentView(R.layout.add_homework);
                break;
            case Note.TYPE_AFFAIR:
                setContentView(R.layout.add_affair);
                break;
        }

        ButterKnife.bind(this);
    }
}
