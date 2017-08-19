package com.screenshotsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GlobalScreenshot screenshot = new GlobalScreenshot(this);
        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenshot.takeScreenshot(MainActivity.this,getWindow().getDecorView(), new Runnable() {
                    @Override
                    public void run() {
                    }
                }, true, true);
            }
        });
    }


}
