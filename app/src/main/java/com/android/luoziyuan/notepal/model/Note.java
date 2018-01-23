package com.android.luoziyuan.notepal.model;

import android.support.annotation.NonNull;

/**
 * Created by John on 2018/1/13.
 */

public class Note implements Comparable<Note>
{
    public static final int TYPE_NOTE = 0;      //四种记录类型定义为公有常量，便于其他类使用
    public static final int TYPE_EXAM = 1;
    public static final int TYPE_HOMEWORK = 2;
    public static final int TYPE_AFFAIR = 3;

    private long id;
    private int type;
    private String theme;
    private String content;
    private String date;

    public long getId() {
        return id;
    }

    public int getType(){
        return type;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public Note(long id, String theme, String content, String createDate)
    {

        this.id = id;
        this.type = TYPE_NOTE;
        this.theme = theme;
        this.content = content;
        this.date = createDate;
    }

    public Note(long id, int type, String theme, String content, String createDate)
    {

        this.id = id;
        this.type = type;
        this.theme = theme;
        this.content = content;
        this.date = createDate;
    }

    @Override
    public int compareTo(@NonNull Note o)       //按照日期对笔记进行比较（新日期在前）
    {
        int r = date.compareTo(o.date);
        if (r > 0)
            return -1;
        else if (r < 0)
            return 1;
        return 0;
    }
}
