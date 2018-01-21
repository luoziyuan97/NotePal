package com.android.luoziyuan.notepal.model;

/**
 * Created by John on 2018/1/21.
 */

public class Homework extends Note
{
    public Homework(long id, String subject, String description, String deadline)
    {
        super(id,Note.TYPE_HOMEWORK,subject,description,deadline);
    }
}
