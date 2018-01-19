package com.android.luoziyuan.notepal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 2018/1/13.
 */

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(Context context) {
        super(context, DBSchema.DB_NAME, null, DBSchema.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBSchema.NoteTable.CREATE_NOTETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBSchema.NoteTable.DROP_NOTETABLE);
        db.execSQL(DBSchema.NoteTable.CREATE_NOTETABLE);
    }
}
