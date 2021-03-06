package com.android.luoziyuan.notepal.model;

/**
 * Created by John on 2018/1/21.
 */

public class Homework extends Note
{
    private String deadline;

    public String getDeadline() {
        return deadline;
    }

    public String getSubject()
    {
        return super.getTheme();
    }

    public String getDescription()
    {
        return super.getContent();
    }

    public String getCreateDate()
    {
        return super.getDate();
    }

    public String getDate()
    {
        return deadline;
    }

    public Homework(long id, String subject, String description, String date, String deadline)
    {
        super(id,Note.TYPE_HOMEWORK,subject,description,date);
        this.deadline = deadline;
    }
}
