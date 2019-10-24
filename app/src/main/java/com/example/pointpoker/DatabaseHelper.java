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


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String USER_TABLE_CREATE = "create table user(UID integer primary key autoincrement, uname text not null)";
        db.execSQL(USER_TABLE_CREATE);
        String TASK_TABLE_CREATE = "create table task(TID integer primary key autoincrement, tname text not null)";
        db.execSQL(TASK_TABLE_CREATE);
        String VOTE_TABLE_CREATE = "create table vote(UID integer, TID integer primary key, VText text not null " +
                "primary key, status text not null, foreign key (UID) references user(UID), foreign key (TID) " +
                "references task(TID))";
        db.execSQL(VOTE_TABLE_CREATE);
        Log.d(TAG, "Database created!");
        String INSERT_NAMES = "insert into user(uname) values ('Roland','Botond')";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists task");
        db.execSQL("drop table if exists vote");
        Log.d(TAG, "Database upgraded!");
        onCreate(db);
    }


}
