package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbManager {
    private DatabaseHelper dbhelper;

    private Context context;

    private SQLiteDatabase database;

    public dbManager(Context c) {
        context = c;
    }
    public dbManager open() throws SQLException {
        dbhelper = new DatabaseHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbhelper.close();
    }
    public void insert(String name, String desc,String status) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TASK_NAME, name);
        contentValue.put(DatabaseHelper.TASK_DESCRIPTION, desc);
        contentValue.put(DatabaseHelper.TASK_STATUS, status);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.TASK_NAME, DatabaseHelper.TASK_DESCRIPTION, DatabaseHelper.TASK_STATUS };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long _id, String taskname, String description,String status) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper._ID,id);
        contentValues.put(DatabaseHelper.TASK_NAME, taskname);
        contentValues.put(DatabaseHelper.TASK_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.TASK_STATUS, status);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}
