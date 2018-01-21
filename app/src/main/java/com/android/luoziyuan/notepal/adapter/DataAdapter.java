package com.android.luoziyuan.notepal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.luoziyuan.notepal.R;
import com.android.luoziyuan.notepal.model.Exam;
import com.android.luoziyuan.notepal.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by John on 2018/1/13.
 */

public class DataAdapter extends BaseAdapter
{
    private Context context;

    private List<Note> data;

    public DataAdapter(Context context,List<Note> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        ViewHolderEx viewHolderEx = null;
        int type = getItemViewType(position);
        Note dataItem = (Note)getItem(position);
        if (convertView == null)
        {
            switch (type)   //根据不同的type来inflate不同的layout，然后设置不同的tag
            {
                case Note.TYPE_NOTE:
                    convertView = View.inflate(context,R.layout.listview,null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;
                case Note.TYPE_EXAM:
                    convertView = View.inflate(context,R.layout.listview_ex,null);
                    viewHolderEx = new ViewHolderEx(convertView);
                    convertView.setTag(viewHolderEx);
            }
        }
        else        //根据不同的type来获得tag
        {
            switch (type)
            {
                case Note.TYPE_NOTE:
                    viewHolder = (ViewHolder)convertView.getTag();
                    break;
                case Note.TYPE_EXAM:
                    viewHolderEx = (ViewHolderEx)convertView.getTag();
                    break;
            }
        }

        switch (type)       //根据不同的type设置数据
        {
            case Note.TYPE_NOTE:
                viewHolder.themeText.setText(dataItem.getTheme());
                viewHolder.contentText.setText(dataItem.getContent());
                viewHolder.dateText.setText(dataItem.getDate());
                break;
            case Note.TYPE_EXAM:
                Exam exam = (Exam) dataItem;
                viewHolderEx.themeText.setText(exam.getTheme());
                viewHolderEx.placeText.setText(exam.getPlace());
                viewHolderEx.dateText.setText(exam.getDate());
                viewHolderEx.timeText.setText(exam.getTime());
                break;
        }
        return convertView;
    }

    class ViewHolder	//内部类ViewHolder，用于优化加载性能
    {
        @BindView(R.id.themeText_listview)
        TextView themeText;
        @BindView(R.id.contentText_listview)
        TextView contentText;
        @BindView(R.id.dateText_listview)
        TextView dateText;

        public ViewHolder(View view)        //使用ButterKnife
        {
            ButterKnife.bind(this,view);
        }
    }

    class ViewHolderEx      //Exam和Affair类的ViewHolder
    {
        @BindView(R.id.themeText_listviewEx)
        TextView themeText;
        @BindView(R.id.placeText_listviewEx)
        TextView placeText;
        @BindView(R.id.timeText_listviewEx)
        TextView timeText;
        @BindView(R.id.dateText_listviewEx)
        TextView dateText;

        public ViewHolderEx(View view)
        {
            ButterKnife.bind(this,view);
        }
    }
}