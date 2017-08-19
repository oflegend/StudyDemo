package com.dbhelperdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by D&LL on 2017/3/27.
 */

public class MyDBHelper extends SQLiteOpenHelper {


    public MyDBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table dict(_id integer primary key autoincrement, word , detail)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("--------", oldVersion + "------->" + newVersion);
    }

    public static void insertData(SQLiteDatabase db, String[] agres) {
        db.execSQL("insert into dict values(null,?,?)", agres);
    }

    public static Cursor queryData(SQLiteDatabase db, String key) {
        Cursor cursor = db.rawQuery("select * from dict where word like ? or detail like ?", new String[]{"%" + key + "%", "%" + key + "%"});
        return cursor;
    }
}
