package com.sharedperferensdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.spRead)
    Button spRead;
    @BindView(R.id.spWrite)
    Button spWrite;
    @BindView(R.id.fileRead)
    Button fileRead;
    @BindView(R.id.fileWrite)
    Button fileWrite;
    @BindView(R.id.spText)
    TextView spText;
    @BindView(R.id.fileText)
    TextView fileText;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        edit.setText("hello world!");
        text = edit.getText().toString();

    }

    @OnClick({R.id.spRead, R.id.spWrite, R.id.fileRead, R.id.fileWrite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spRead:
                String sp = (String) SPUtil.toRead(this, "main", "no data!");
                String result = sp == null ? "No Data!" : sp;
                spText.setText(result);
                break;
            case R.id.spWrite:
                SPUtil.toWrite(this, "main", text);
                break;
            case R.id.fileRead:
                String file = FileUtil.toReadSD(this);
                String show = file == null ? "No Data!" : file;
                fileText.setText(show);
                break;
            case R.id.fileWrite:
                FileUtil.toWriteSD(this, text);
                break;
        }
    }
}
