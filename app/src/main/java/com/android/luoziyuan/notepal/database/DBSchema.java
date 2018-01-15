package com.android.luoziyuan.notepal.database;

import com.android.luoziyuan.notepal.model.Note;

/**
 * Created by John on 2018/1/13.
 */

public class DBSchema
{
    public static final String DB_NAME = "mynotes.db";   //数据库名
    public static final int DB_VERSION = 1; //

    public static class NoteTable
    {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_THEME = "theme";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DATE = "date";

        public static final String CREATE_NOTETABLE = "CREATE TABLE IF NOT EXISTS "
                + NoteTable.TABLE_NAME + "("
                + NoteTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NoteTable.COLUMN_THEME + " VARCHAR(50),"
                + NoteTable.COLUMN_CONTENT + " TEXT,"
                + NoteTable.COLUMN_DATE + " CHAR(10));";
    }
}
