package com.android.luoziyuan.notepal.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.luoziyuan.notepal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John on 2018/1/20.
 */

public class UpdateActivity extends Activity
{
    private String theme;
    private String content;
    private int position;

    @BindView(R.id.themeText_updateActivity)
    EditText themeText;

    @BindView(R.id.contentText_updateActivity)
    EditText contenText;

    @BindView(R.id.okButton_updateActivity)
    Button okButton;

    @BindView(R.id.cancelButton_updateActivity)
    Button cancelButton;

    @OnClick(R.id.cancelButton_updateActivity)
    public void cancelButtonClick(View view)       //点击取消按钮，直接返回
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.okButton_updateActivity)
    public void okButtonClick(View view)        //点击确定按钮，检测是否更改
    {
        String newTheme = themeText.getEditableText().toString();
        String newContent = contenText.getEditableText().toString();
        Intent intent = new Intent();
        if (newTheme.equals(theme) && newContent.equals(content))
            intent.putExtra("isChanged",false);
        else
        {
            intent.putExtra("isChanged",true);
            intent.putExtra("theme",newTheme);
            intent.putExtra("content",newContent);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");
        theme = bundle.getString("theme");
        content = bundle.getString("content");
        themeText.setText(theme);
        contenText.setText(content);
    }
}
