package com.activitylifecycledemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    final String TAG = "---MainActivity---";
    @BindView(R.id.dialog)
    Button dialog;
    @BindView(R.id.second)
    Button second;
    @BindView(R.id.finish)
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate创建！");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart启动！");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart重新启动！");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume重新开始！");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause暂停！");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop停止！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy摧毁！");
    }


    @OnClick({R.id.dialog, R.id.second, R.id.finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.second:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
