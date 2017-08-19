package com.dbhelperdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.word)
    EditText word;
    @BindView(R.id.detail)
    EditText detail;
    @BindView(R.id.insert)
    Button insert;
    @BindView(R.id.key)
    EditText key;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.show)
    ListView show;

    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //程序会自动保存在数据文件的database文件下
        myDBHelper = new MyDBHelper(this, "myDict.db3", 1);
       myDBHelper.getReadableDatabase();
    }

    @OnClick({R.id.insert, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                String words = word.getText().toString();
                String details = detail.getText().toString();
                MyDBHelper.insertData(myDBHelper.getWritableDatabase(), new String[]{words, details});
                Toast.makeText(this, "succeed", Toast.LENGTH_LONG);
                break;
            case R.id.search:
                String keys = key.getText().toString();
                Cursor cursor = MyDBHelper.queryData(myDBHelper.getReadableDatabase(), keys);
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtras(data);
             startActivity(intent);
                break;
        }
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put("_id", cursor.getString(0));
            map.put("word", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myDBHelper != null) {
            myDBHelper.close();
        }
    }
}
