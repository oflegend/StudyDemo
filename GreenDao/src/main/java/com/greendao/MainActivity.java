package com.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greendao.Dao.DBHelper;
import com.greendao.Dao.User;
import com.greendao.Dao.UserDao;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd, mDelete, mUpdate, mFind;
    private TextView mContext;
    private UserDao userDao;
    private DBHelper dbHelper = DBHelper.getInstance();
    private String TABLE_NAME = "my.db3";//数据表的名称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        //初始化对象
        userDao = dbHelper.initDatabase(this, TABLE_NAME).getUserDao();

    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    private void initView() {
        mContext = (TextView) findViewById(R.id.textView);
        mAdd = (Button) findViewById(R.id.button);
        mDelete = (Button) findViewById(R.id.button2);
        mUpdate = (Button) findViewById(R.id.button3);
        mFind = (Button) findViewById(R.id.button4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                addDate();
                break;
            case R.id.button2:
                deleteDate();
                break;
            case R.id.button3:
                updateDate();
                break;
            case R.id.button4:
                findDate();
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addDate() {
        User user = new User(null, "zhangsan"+ new Random().nextInt(100), "张三");
        userDao.insert(user);
        mContext.setText(user.getUsername() + user.getNickname());
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        deleteUserById(2);

    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {
        userDao.deleteByKey(id);
    }

    /**
     * 更改数据
     */
    private void updateDate() {
        User user = new User((long) 5, "lisi", "李四");
        userDao.update(user);

    }

    /**
     * 查找数据
     */
    private void findDate() {
        List<User> users = userDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getUsername() + "," + users.get(i).getNickname() + "\n";

        }
        mContext.setText("查询全部数据==>" + userName);

    }
}
