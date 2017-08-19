package com.sqlitedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.show)
    ListView show;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.select)
    Button select;
    @BindView(R.id.update)
    Button update;

    private SimpleCursorAdapter adapter;
    private SQLiteDatabase sdb;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //在SD卡新建名为my的数据库
        sdb = SQLiteUtil.createSDSQLite(this, "my");
        //新建名为people的数据表
        SQLiteUtil.createTable(sdb, "people", "people(_id integer primary key autoincrement, name varchar(10), sex varchar(5))");


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SQLiteUtil.closeSDB(sdb);
    }


    @OnClick({R.id.add, R.id.delete, R.id.select, R.id.update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                String name = title.getText().toString();
                String sex = content.getText().toString();
                SQLiteUtil.insertData(sdb, "insert into people values(null,?,?)", new String[]{name, sex});//插入对应值
                //SQLiteUtil.grammarDo(sdb, "insert into people values(null,'" + name + "','" + sex + "')");//sql语句操纵
                break;
            case R.id.delete:
                //SQLiteUtil.grammarDo(sdb, "delete from people where name=''");//sql语句操纵
                SQLiteUtil.deleteSDB(sdb, "people", "sex like ?", new String[]{"2_"});
                break;
            case R.id.select:
                cursor = SQLiteUtil.queryData(sdb, "select * from people ", null);//查询所有数据
                //cursor = SQLiteUtil.querySDB(sdb, "people", new String[]{"_id,name,sex"}, "sex like ? or sex like ?", new String[]{"2%", "q%"});
                adapter = new SimpleCursorAdapter(this, R.layout.line, cursor, new String[]{"name", "sex"}, new int[]{R.id.my_title, R.id.my_content},
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//负责封装Cursor的适配器
            show.setAdapter(adapter);
            break;
            case R.id.update:
                //SQLiteUtil.grammarDo(sdb, "update people set name ='333' where sex = '22' ");//sql语句操纵
                ContentValues values = new ContentValues();
                values.put("name", "hello");
                SQLiteUtil.updateSDB(sdb, "people", values, "_id=?", new String[]{"2"});
                break;

        }
    }
}
