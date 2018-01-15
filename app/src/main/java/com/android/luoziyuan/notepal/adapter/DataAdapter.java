package com.android.luoziyuan.notepal.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.luoziyuan.notepal.R;
import com.android.luoziyuan.notepal.model.Note;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by John on 2018/1/13.
 */

public class DataAdapter extends ArrayAdapter
{
    private int resourceId;

    public DataAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects)
    {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view;
        ViewHolder viewHolder;
        Note dataItem = (Note)getItem(position);
        if (convertView == null)
        {
            view = LayoutInflater.from(getContext())
                    .inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.theme = (TextView)view.findViewById(R.id.themeText_listview);
            viewHolder.content = (TextView)view.findViewById(R.id.contentText_listview);
            viewHolder.date = (TextView)view.findViewById(R.id.dateText_listview);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.theme.setText(dataItem.getTheme());
        viewHolder.content.setText(dataItem.getContent());
        viewHolder.date.setText(dataItem.getDate());
        return view;
    }

    class ViewHolder	//内部类ViewHolder，用于优化加载性能
    {
        TextView theme;
        TextView content;
        TextView date;
    }
}