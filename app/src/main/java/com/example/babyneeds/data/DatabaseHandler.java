package com.example.babyneeds.data;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.babyneeds.model.Item;
import com.example.babyneeds.utils.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Utils.DB_NAME, null, Utils.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "create table " + Utils.TABLE_NAME + "( " + Utils.KEY_ID + " integer primary key," +
                Utils.KEY_NAME + " text," + Utils.KEY_QTY + " integer," + Utils.KEY_COLOR + " text," +
                Utils.KEY_SIZE + " integer," + Utils.KEY_DATE_NAME + " long" + ");";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + Utils.TABLE_NAME);

        onCreate(db);
    }

    public void addItem(Item item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utils.KEY_DATE_NAME, item.getItemName());
        values.put(Utils.KEY_QTY, item.getQuantity());
        values.put(Utils.KEY_COLOR, item.getColor());
        values.put(Utils.KEY_SIZE, item.getSize());
        values.put(Utils.KEY_DATE_NAME, java.lang.System.currentTimeMillis());

        db.insert(Utils.TABLE_NAME, null, values);

        Log.d(TAG, "addItem: added item");


    }

    public Item getItem(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_NAME, new String[]{Utils.KEY_QTY, Utils.KEY_SIZE, Utils.KEY_NAME, Utils.KEY_DATE_NAME, Utils.KEY_COLOR}, Utils.KEY_ID + "=?", new String[]{
                String.valueOf(id)
        }, null, null, null, null);


        if (cursor != null) {

            cursor.moveToNext();

           db.close();

           return setItem(cursor);


        }


    return null;


    }

    public Item setItem(Cursor cursor){

        Item item = new Item();
        item.setId(cursor.getInt(cursor.getInt(0)));

        item.setItemName(cursor.getString(cursor.getInt(1)));
        item.setQuantity(cursor.getInt(cursor.getInt(2)));

        item.setColor(cursor.getString(cursor.getInt(3)));
        item.setSize(cursor.getInt(cursor.getInt(4)));


        long timeStamp = cursor.getLong(cursor.getInt(5));

        Locale loc = new Locale("en", "US");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);

        String date = dateFormat.format(new Date(timeStamp));


        item.setDateItemAdded(date);
        cursor.close();


        return item;


    }
    public List<Item> getAllItems(){

        SQLiteDatabase db  = this.getReadableDatabase();

        List<Item> itemList = new ArrayList<Item>();
        Cursor cursor = db.query(Utils.TABLE_NAME, new String[]{Utils.KEY_QTY, Utils.KEY_SIZE, Utils.KEY_NAME, Utils.KEY_DATE_NAME, Utils.KEY_COLOR}
       ,null ,null ,  null, null, Utils.KEY_DATE_NAME+ " Desc", null);


        if( cursor !=null){

            if(cursor.moveToFirst()){
                do{
                    Item item = setItem(cursor);
                    itemList.add(item);

                }while(cursor.moveToNext());
            }

            cursor.close();
            db.close();
        }

        return itemList;

    }
}
