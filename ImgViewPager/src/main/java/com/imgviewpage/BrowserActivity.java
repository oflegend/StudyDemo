package com.imgviewpage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by D&LL on 2017/4/24.
 */

public class BrowserActivity extends Activity {
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.num)
    TextView num;

    int[] imgs;
    int pos;
    private ImgAdapter imgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_papager);
        ButterKnife.bind(this);

        imgs = getIntent().getIntArrayExtra("imgs");
        pos = getIntent().getIntExtra("pos", 0);

        imgAdapter = new ImgAdapter(this, imgs);
        pager.setAdapter(imgAdapter);

        pager.setCurrentItem(pos);//设置起始位置
        pager.setPageTransformer(true, new DepthPageTransformer());//修改动画效果

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                num.setText((position + 1) + "/" + imgs.length);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
