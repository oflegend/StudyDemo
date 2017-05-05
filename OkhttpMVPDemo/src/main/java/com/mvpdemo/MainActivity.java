package com.mvpdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvpdemo.presenter.MainPresenter;
import com.mvpdemo.view.MainView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    String geturl = "http://p.3.cn/prices/mgets?skuIds=J_954086&type=1";//京东获取单个商品价格接口:
    String posturl = "http://gc.ditu.aliyun.com/geocoding";//阿里云根据地区名获取经纬度接口

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.get)
    TextView get;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.img)
    ImageView img;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initData();


    }


    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "苏州市");
        //map.put("type", "1");
        presenter = new MainPresenter(this, this);
        presenter.getRequest(geturl);
        presenter.postMap(posturl, map);
    }


    @OnClick(R.id.button)
    public void onClick() {
        presenter.downFile("http://images.csdn.net/20150817/1.jpg", "demo.jpg");
    }

    @Override
    public void getView(final String s) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                get.setText(s);
            }
        });
    }

    @Override
    public void postView(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                post.setText(s);
            }
        });
    }

    @Override
    public void imgView(final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                img.setImageBitmap(bitmap);
            }
        });
    }


}
