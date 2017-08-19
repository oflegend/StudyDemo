package com.testprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ContentResolver resolver;
    private Uri uri = Words.DICT_URI;
    private SimpleCursorAdapter adapter;
    private MyObserver myObserver = new MyObserver(this, new Handler());
    @BindView(R.id.show)
    ListView show;
    @BindView(R.id.insert)
    Button insert;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.update)
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        resolver = getContentResolver();

        //注册监听器
        resolver.registerContentObserver(uri, true, myObserver);

    }


    @OnClick({R.id.insert, R.id.delete, R.id.query, R.id.update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                ContentValues values = new ContentValues();
                values.put(Words.WORD, "hello");
                values.put(Words.DETAIL, "Android");
                resolver.insert(uri, values);
                break;
            case R.id.delete:
                break;
            case R.id.query:
                Cursor cursor = resolver.query(uri, null, null, null, null);
                adapter = new SimpleCursorAdapter(this, R.layout.line, cursor, new String[]{Words.WORD, Words.DETAIL}, new int[]{R.id.my_title, R.id.my_content},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//负责封装Cursor的适配器
                show.setAdapter(adapter);
                break;
            case R.id.update:
                ContentValues value = new ContentValues();
                value.put(Words.WORD, "hello");
                value.put(Words.DETAIL, "Java");
                resolver.update(uri, value, "_id=?", new String[]{"2"});
                break;
        }
    }
}
