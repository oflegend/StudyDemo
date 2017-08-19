package com.mvpdemo;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by D&LL on 2017/5/5.
 */

public class DialogUtil {
    public static int style = R.style.MyDialog;
    public static ProgressDialog getProgressDialog(final Context context) {
        ProgressDialog dialog = new ProgressDialog(context, style);
        dialog.setTitle("温馨提示");
        dialog.setMessage("加载中...");
        dialog.setCancelable(true);
        return dialog;
    }
}
