package com.example.pointpoker;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "Poker";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String USER_TABLE_CREATE = "create table user(UID integer primary key autoincrement, uname text)";
        String TASK_TABLE_CREATE = "create table task(TID integer primary key autoincrement, tname text)";
        String VOTE_TABLE_CREATE = "create table vote(UID integer, TID integer primary key, VText text not null " +
                "primary key, status text not null, foreign key (UID) references user(UID), foreign key (TID) " +
                "references task(TID))";
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(TASK_TABLE_CREATE);
        db.execSQL(VOTE_TABLE_CREATE);
        Log.d(TAG, "Database created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists task");
        db.execSQL("drop table if exists vote");
        onCreate(db);
        Log.d(TAG, "Database upgraded!");
    }

    public boolean addUser(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uname", name);
        db.insert("user", null, contentValues);
        db.close();
        return true;
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

}
