package com.example.android.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.todolist.todo.Todo;

import java.util.ArrayList;

/**
 * Created by Abshafi on 11/21/2016.
 */
public class TodoSQLHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Todo.db";
    private static final String TABLE_TODO = "todos";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_STATUS = "todoStatus";


    public TodoSQLHelper (Context context)
    {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String test = "create table " + TABLE_TODO + " (" + COLUMN_DATE + " text primary key, " + COLUMN_STATUS + " text);";
        db.execSQL(test);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public boolean insertToDB(Todo todo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("insertTODB", todo.getTodoStatus());
        ContentValues contentValues = new ContentValues();
        if(!checkIfExists(todo.getDateAndtime()))
        {
            contentValues.put(COLUMN_DATE, todo.getDateAndtime());;
            contentValues.put(COLUMN_STATUS, todo.getTodoStatus());

            db.insert(TABLE_TODO, null, contentValues);
            return true;

        }

        else
            return false;
    }

    private boolean checkIfExists(String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String check = "select * from " + TABLE_TODO + " where " + COLUMN_DATE + " =' " + date + "'";
        Cursor cursor = db.rawQuery(check,null);
        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }

        else
        {
            cursor.close();
            return true;
        }
    }

    public ArrayList<Todo> getAllData()
    {

        ArrayList<Todo> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String get = "select * from " + TABLE_TODO;
        Cursor cursor = db.rawQuery(get,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Todo todo = new Todo();
            todo.setDateAndtime(cursor.getString(0));
            todo.setTodoStatus(cursor.getString(1));

            arrayList.add(todo);
            cursor.moveToNext();
        }

        return arrayList;
    }

    public void deleteRow(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();

         db.delete(TABLE_TODO,COLUMN_DATE + " = ?",new String[] {date});
    }

    public void deleteall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO,null,null);
    }
}
