package com.loadmodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text)
    TextView text;
    private String TAG = "---OtherActivity---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        text.setText("OtherActivity");
        Log.i(TAG, "TaskID:" + this.getTaskId() + " hashCode:" + this.hashCode());

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }
}
