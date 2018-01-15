package com.android.luoziyuan.notepal.model;

/**
 * Created by John on 2018/1/13.
 */

public class Note
{
    private int id;
    private String theme;
    private String content;
    private String date;

    public int getId() {
        return id;
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

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Note(int id, String theme, String content, String createDate)
    {

        this.id = id;
        this.theme = theme;
        this.content = content;
        this.date = createDate;
    }

}
