package com.android.luoziyuan.notepal.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.luoziyuan.notepal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John on 2018/1/16.
 */

public class AddActivity extends Activity
{
    @BindView(R.id.addButton_addActivity)
    Button addButton;

    @BindView(R.id.themeText_addActivity)
    EditText themeText;

    @BindView(R.id.contentText_addActivity)
    EditText contentText;

    @OnClick(R.id.addButton_addActivity)
    public void add(View view)              //添加按键的响应事件
    {
        String theme = themeText.getText().toString();
        String content = contentText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("theme",theme);
        intent.putExtra("content",content);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);
    }
}
