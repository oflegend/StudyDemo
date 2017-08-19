package com.activitylifecycledemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = new TextView(this);
        text.setText("对话框风格！");
        setContentView(text);
    }
}
