package com.android.luoziyuan.notepal.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.luoziyuan.notepal.model.Note;

import java.util.List;

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

    public void get(List<Note> data)     //从数据库获取数据
    {
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM notes", null);
        if (cursor.moveToFirst()) {
            do {
                Note newNote = new Note(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_DATE)));
                data.add(newNote);
            } while (cursor.moveToNext());
        }
    }

    public void get(List<Note> data,String s)     //带参数的获取数据方法，搜索功能使用
    {
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM notes WHERE " +
                DBSchema.NoteTable.COLUMN_CONTENT + " like " + "'%" + s + "%'" + " OR " +
                DBSchema.NoteTable.COLUMN_THEME + " like " + "'%" + s + "%'",
                null);
        if (cursor.moveToFirst())
        {
            do{
                Note newNote = new Note(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_DATE)));
                data.add(newNote);
            }while(cursor.moveToNext());
        }
    }

    public void insertNote(List<Note> data,String theme, String content, String date)
    {
        long id = System.currentTimeMillis();           //获取系统当前时间作为记录id
        //插入新记录到数据库
        sqLiteDB.execSQL("INSERT INTO " + DBSchema.NoteTable.TABLE_NAME + "(" +
                DBSchema.NoteTable.COLUMN_ID + "," +
                DBSchema.NoteTable.COLUMN_THEME + "," +
                DBSchema.NoteTable.COLUMN_CONTENT + "," +
                DBSchema.NoteTable.COLUMN_DATE +
                ") " +
                "VALUES(" + "'" +
                id + "'," + "'" +
                theme + "'," + "'" +
                content + "'," + "'" +
                date + "'" +
                ")");
        if (data.size() == 0)
            data.add(new Note(id,theme,content,date));
        else
        {
            for (int i = 0, len = data.size(); i < len; i++)    //将新记录插入到数据集中的合适位置
            {
                if (date.compareTo(data.get(i).getDate()) < 0)
                    continue;
                data.add(new Note(id, theme, content, date));
                break;
            }
        }
    }

    public void deleteNote(List<Note> data,int position)
    {
        long id = data.get(position).getId();
        sqLiteDB.execSQL("DELETE FROM " + DBSchema.NoteTable.TABLE_NAME + " WHERE " +
                DBSchema.NoteTable.COLUMN_ID + "=" + id);
        data.remove(position);
    }
}
