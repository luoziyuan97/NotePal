package com.android.luoziyuan.notepal.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.luoziyuan.notepal.model.Note;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by John on 2018/1/13.
 */

public class DataSource
{
    private SQLiteDatabase sqLiteDB;
    private DBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
        sqLiteDB = dbHelper.getWritableDatabase();
    }

    public List<Note> get()
    {
        List<Note> notes = new ArrayList<Note>();
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM notes", null);
        if (cursor.moveToFirst())
        {
            do{
                Note newNote = new Note(
                        cursor.getInt(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_DATE)));
                notes.add(newNote);
            }while(cursor.moveToNext());
        }
        return notes;
    }

    public boolean insert(String theme, String content, String date)
    {
        sqLiteDB.execSQL("INSERT INTO notes(" +
                DBSchema.NoteTable.COLUMN_THEME + "," +
                DBSchema.NoteTable.COLUMN_CONTENT + "," +
                DBSchema.NoteTable.COLUMN_DATE +
                ") " +
                "VALUES(" + "'" +
                theme + "'," + "'" +
                content + "'," + "'" +
                date + "'" +
                ")");
        return true;
    }
}
