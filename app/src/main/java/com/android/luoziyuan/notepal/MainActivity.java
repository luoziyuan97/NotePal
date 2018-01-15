package com.android.luoziyuan.notepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.luoziyuan.notepal.adapter.DataAdapter;
import com.android.luoziyuan.notepal.database.DataSource;
import com.android.luoziyuan.notepal.model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<Note> data = null;
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private DataSource dataSource = null;

    @BindView(R.id.listview)
    ListView listView;

    @BindView(R.id.testButton_mainActivity)
    Button testButton;

    @OnClick(R.id.testButton_mainActivity)
    public void test(View view)
    {
        if (dataSource.insert("test","test message",
                dateFormat.format(System.currentTimeMillis())))
            Toast.makeText(this,"Insert success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Insert fail",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dataSource = new DataSource(this);
        data = dataSource.get();
        DataAdapter dataAdapter = new DataAdapter(this,R.layout.listview,data);
        listView.setAdapter(dataAdapter);
    }
}
