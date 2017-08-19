package com.zxingcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.Toast;

import com.google.zxing.WriterException;

public class ZxingcodeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView zxing;
    private Button save;
    private Bitmap bitmap;
    private String world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxing_code);
        Intent intent = getIntent();
        world = intent.getStringExtra("string");
        try {
            bitmap = EncodingHandler.createQRCode(world + "", 500);//生成二维码
        } catch (WriterException e) {
            e.printStackTrace();
        }
        init();
    }

    private void init() {
        zxing = (ImageView) findViewById(R.id.zxing);
        zxing.setImageBitmap(bitmap);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == save) {
            BitmapUtil.saveBitmap(this, bitmap);
            Toast.makeText(this, "保存成功！", Toast.LENGTH_LONG).show();
        }
    }
}

