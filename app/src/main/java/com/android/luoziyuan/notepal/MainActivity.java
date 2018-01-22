package com.android.luoziyuan.notepal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.luoziyuan.notepal.adapter.DataAdapter;
import com.android.luoziyuan.notepal.controller.UpdateActivity;
import com.android.luoziyuan.notepal.database.DataSource;
import com.android.luoziyuan.notepal.model.Exam;
import com.android.luoziyuan.notepal.model.Note;
import com.android.luoziyuan.notepal.controller.AddActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    private final int REQUESTCODE_ADDACTIVITY = 1;
    private final int REQUESTCODE_UPDATEACTIVITY = 2;
    private List<Note> data = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private DataSource dataSource = null;
    private DataAdapter dataAdapter = null;

    @BindView(R.id.searchText_mainActivity)
    EditText searchText;

    @BindView(R.id.listview)
    ListView listView;

    @BindView(R.id.testButton_mainActivity)
    Button testButton;

    @OnClick(R.id.testButton_mainActivity)
    public void addNewNote(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择记录类型");
        builder.setSingleChoiceItems(new String[]{"笔记", "考试", "作业", "事务(会议、活动等)"},
                -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,
                                AddActivity.class);
                        intent.putExtra("type",which);
                        startActivityForResult(intent,REQUESTCODE_ADDACTIVITY);
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("取消",null);
        builder.show();
    }

    @OnTextChanged(R.id.searchText_mainActivity)
    public void OnTextChanged(Editable s)       //搜索功能，检测到搜索框内容变化后刷新数据
    {
        data.clear();
        if (s.length() == 0)
            dataSource.get(data);
        else
            dataSource.get(data, s.toString());
        Collections.sort(data);
        dataAdapter.notifyDataSetChanged();
    }

    @OnItemClick(R.id.listview)         //listview项的点击事件——查看详情
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        int type = data.get(position).getType();
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("type",type);
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        switch (type)           //根据记录类型传输不同的数据
        {
            case Note.TYPE_NOTE:
                bundle.putString("theme",data.get(position).getTheme());
                bundle.putString("content",data.get(position).getContent());
                break;
            case Note.TYPE_EXAM:
                Exam temp = (Exam)data.get(position);
                bundle.putString("subject",temp.getTheme());
                bundle.putString("date",temp.getDate());
                bundle.putString("time",temp.getTime());
                bundle.putString("place",temp.getPlace());
                bundle.putString("description",temp.getContent());
                break;
        }
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUESTCODE_UPDATEACTIVITY);
    }

    @OnItemLongClick(R.id.listview)     //listview项的长按事件——删除记录
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("删除该记录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dataSource.delete(data,position);
                dataAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        data = new ArrayList<Note>();
        dataSource = new DataSource(this);
        dataSource.get(data);
        Collections.sort(data);
        dataAdapter = new DataAdapter(this,data);
        listView.setAdapter(dataAdapter);
    }

    @Override           //添加和更改后续操作
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUESTCODE_ADDACTIVITY:       //响应添加功能
                {
                    int type = intent.getIntExtra("type",-1);
                    switch (type)           //根据记录类型添加记录
                    {
                        case Note.TYPE_NOTE:
                            dataSource.insertNote(data,
                                    intent.getStringExtra("theme"),
                                    intent.getStringExtra("content"),
                                    dateFormat.format(System.currentTimeMillis()));
                            break;
                        case Note.TYPE_EXAM:
                            dataSource.insertExam(data,
                                    intent.getStringExtra("subject"),
                                    intent.getStringExtra("description"),
                                    intent.getStringExtra("date"),
                                    intent.getStringExtra("time"),
                                    intent.getStringExtra("place"));
                            break;
                        case Note.TYPE_HOMEWORK:
                            dataSource.insertHomework(data,
                                    intent.getStringExtra("subject"),
                                    intent.getStringExtra("description"),
                                    intent.getStringExtra("createDate"),
                                    intent.getStringExtra("deadline"));
                            break;
                        case Note.TYPE_AFFAIR:
                            break;
                    }
                    dataAdapter.notifyDataSetChanged();         //更新listview的显示
                    Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                    break;
                }
                case REQUESTCODE_UPDATEACTIVITY:       //响应更改功能
                {
                    if (intent.getBooleanExtra("isChanged",false))
                    {
                        int type = intent.getIntExtra("type",-1);
                        int position = intent.getIntExtra("position",-1);
                        dataSource.delete(data,position);          //在数据集中删除原纪录
                        switch (type)           //根据记录类型删除&添加记录
                        {
                            case Note.TYPE_NOTE:
                                dataSource.insertNote(data,
                                        intent.getStringExtra("theme"),
                                        intent.getStringExtra("content"),
                                        dateFormat.format(System.currentTimeMillis()));
                                break;
                            case Note.TYPE_EXAM:
                                dataSource.insertExam(data,
                                        intent.getStringExtra("subject"),
                                        intent.getStringExtra("description"),
                                        intent.getStringExtra("date"),
                                        intent.getStringExtra("time"),
                                        intent.getStringExtra("place"));
                                break;
                        }
                        dataAdapter.notifyDataSetChanged();
                        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        else if (resultCode == RESULT_CANCELED)
            Toast.makeText(this,"取消了操作",Toast.LENGTH_SHORT).show();
    }
}
