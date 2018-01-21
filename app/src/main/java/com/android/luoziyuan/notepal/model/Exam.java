package com.android.luoziyuan.notepal.model;

/**
 * Created by John on 2018/1/21.
 */

public class Exam extends Note
{
    private String time;
    private String place;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Exam(long id, String subject, String description, String date, String time, String place)
    {
        super(id,Note.TYPE_EXAM,subject,description,date);
        this.time = time;
        this.place = place;
    }
}
