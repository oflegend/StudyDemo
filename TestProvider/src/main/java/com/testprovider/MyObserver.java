package com.testprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by D&LL on 2017/4/27.
 */

public final class MyObserver extends ContentObserver {
    private ContentResolver resolver;
    private Context context;
    private Uri uri = Words.DICT_URI;

    public MyObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;

    }

    @Override
    public void onChange(boolean selfChange) {
        resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()){
            StringBuilder string  = new StringBuilder();
            string.append(cursor.getString(cursor.getColumnIndex(Words._ID))+" ").append(
                    cursor.getString(cursor.getColumnIndex(Words.WORD))+" ").append(
                            cursor.getString(cursor.getColumnIndex(Words.DETAIL)));
            System.out.println(string);
        }
    }
}
