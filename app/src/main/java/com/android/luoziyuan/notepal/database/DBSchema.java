package com.android.luoziyuan.notepal.database;

import com.android.luoziyuan.notepal.model.Note;

/**
 * Created by John on 2018/1/13.
 */

public class DBSchema
{
    public static final String DB_NAME = "mynotes.db";   //数据库名
    public static final int DB_VERSION = 2;             //数据库版本号

    public static class NoteTable           //对应数据库中的note表
    {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_THEME = "theme";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DATE = "date";

        public static final String CREATE_NOTETABLE = "CREATE TABLE IF NOT EXISTS "
                + NoteTable.TABLE_NAME + "("
                + NoteTable.COLUMN_ID + " LONG PRIMARY KEY,"
                + NoteTable.COLUMN_THEME + " VARCHAR(50),"
                + NoteTable.COLUMN_CONTENT + " TEXT,"
                + NoteTable.COLUMN_DATE + " CHAR(10));";

        public static final String DROP_NOTETABLE = "DROP TABLE "
                + NoteTable.TABLE_NAME + ";";
    }
}
