package com.dbhelperdemo;

import android.net.Uri;

/**
 * Created by D&LL on 2017/4/27.
 */

public final class Words {
    public static final String AUTHORITY = "demon.MyProvider";
    public static final String _ID = "_id";
    public static final String WORD = "word";
    public static final String DETAIL = "detail";
    public static final Uri DICT_URI = Uri.parse("content://" + AUTHORITY);
}
