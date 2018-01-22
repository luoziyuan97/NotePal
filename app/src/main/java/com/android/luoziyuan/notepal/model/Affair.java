package com.android.luoziyuan.notepal.model;

/**
 * Created by John on 2018/1/21.
 */

public class Affair extends Note
{
    private String time;
    private String place;

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public Affair(long id, String theme, String description, String date, String time, String place)
    {
        super(id,Note.TYPE_AFFAIR,theme,description,date);
        this.time = time;
        this.place = place;
    }
}
