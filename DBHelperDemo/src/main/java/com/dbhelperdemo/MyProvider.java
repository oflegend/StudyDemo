package com.dbhelperdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


/**
 * Created by D&LL on 2017/4/27.
 */

public class MyProvider extends ContentProvider {
    private MyDBHelper myDBHelper;

    @Override
    public boolean onCreate() {
        myDBHelper = new MyDBHelper(this.getContext(), "myDict.db3", 1);
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        //return myDBHelper.queryData(db, selection);
        return SQLiteUtil.queryData(db, "select * from dict ", null);//查询所有数据
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        long rowId = db.insert("dict", null, values);
        if (rowId > 0) {
            Uri newUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return newUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        return SQLiteUtil.deleteSDB(db, "dict", selection, selectionArgs);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        return SQLiteUtil.updateSDB(db, "dict", values, selection, selectionArgs);
    }
}
