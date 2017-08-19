package com.zxingcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_world;
    private Button create;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_world = (EditText) findViewById(R.id.et_world);
        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == create) {
            String s = et_world.getText().toString();
            if (s.isEmpty()) {
                Toast.makeText(this, "内容不能为空", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, ZxingcodeActivity.class);
                intent.putExtra("string", s);
                startActivity(intent);
            }

        }
    }
}
