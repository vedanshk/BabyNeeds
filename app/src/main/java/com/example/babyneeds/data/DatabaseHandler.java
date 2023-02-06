package com.example.babyneeds.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.babyneeds.utils.Utils;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Utils.DB_NAME, null, Utils.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE =  "create table " + Utils.TABLE_NAME+"( " + Utils.KEY_ID +" integer primary key autoincrement,"+
                Utils.KEY_NAME+" text," + Utils.KEY_QTY +" integer," + Utils.KEY_COLOR + " text," +
                Utils.KEY_SIZE +" integer " +");";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
