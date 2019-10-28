package com.example.pointpoker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "Poker";
    public static final int DATABASE_VERSION = 3;
    public static final String TASK_TABLE_CREATE = "create table task (TID integer primary key autoincrement, tname text)";
    public static final String USER_TABLE_CREATE = "create table user (UID integer primary key autoincrement, uname text)";
    public static final String VOTE_TABLE_CREATE = "create table vote (UID integer, TID integer, VText text not null " +
            ", status text not null, primary key(TID, UID, VText), foreign key (UID) references user(UID), foreign key (TID) " +
            "references task(TID))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(VOTE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists task");
        db.execSQL("drop table if exists vote");
        onCreate(db);
    }

    public boolean addUser(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uname", name);
        db.insert("user", null, contentValues);
        db.close();
        return true;
    }

    public boolean addProject(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tname", name);
        db.insert("task", null, contentValues);
        db.close();
        return true;
    }

    public String getProject() {
        String name = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from task;", null);
        while(cursor.moveToNext()) {
            name = cursor.getString(1);
            cursor.close();
            return name;
        }
        return name;
    }

    public boolean userExist(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user;", null);
        while(cursor.moveToNext()) {
            if(cursor.getString(1).equals(name)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public String getUserById(int id) {
        String name = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select uname from user where UID = "+ id + ";", null);
        while(cursor.moveToNext()) {
            name = cursor.getString(0);
            cursor.close();
        }
        return name;
    }

    public boolean addTask(String name, int pid, int uid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("VText", name);
        contentValues.put("TID", pid);
        contentValues.put("UID", uid);
        contentValues.put("status", -1);
        db.insert("vote", null, contentValues);
        db.close();
        return true;
    }

    public String getTask() {
        String name = " ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select VText from vote;",null);
        while(cursor.moveToNext()) {
            name = cursor.getString(0);
            cursor.close();
            return name;
        }
        return name;
    }

    public boolean projectExist(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from task;", null);
        while(cursor.moveToNext()) {
            if(cursor.getString(1).equals(name)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public boolean taskExist(String pname, String tname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select t.tname, v.vtext from task t join vote v on t.tid=v.tid;", null);
        while(cursor.moveToNext()) {
            if(cursor.getString(0).equals(pname) && cursor.getString(1).equals(tname)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }
}
