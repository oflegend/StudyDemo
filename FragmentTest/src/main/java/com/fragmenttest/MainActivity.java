package com.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.openleft)
    ImageView openleft;
    @BindView(R.id.vPager)
    ViewPager vPager;
    @BindView(R.id.picture_text)
    TextView pictureText;
    @BindView(R.id.movie_text)
    TextView movieText;
    @BindView(R.id.music_text)
    TextView musicText;
    @BindView(R.id.title)
    TextView title;

    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private MyViewPage adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        //初始化ViewPager
        InitViewPager();
    }


    private void init() {
        fragments = new ArrayList<>();
        fragments.add(new AFragment());
        fragments.add(new AFragment());
        fragments.add(new AFragment());

        fragmentManager = getSupportFragmentManager();


    }


    private void InitViewPager() {
        adapter = new MyViewPage(fragmentManager, fragments);
        vPager.setAdapter(adapter);
        //让ViewPager缓存2个页面
        vPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        vPager.setCurrentItem(1);
        //将顶部文字恢复默认值
        resetColor();
        pictureText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));

        vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        title.setText("图片");
                        resetColor();
                        pictureText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        break;
                    case 1:
                        title.setText("电影");
                        resetColor();
                        movieText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        break;
                    case 2:
                        title.setText("音乐");
                        resetColor();
                        musicText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.one, R.id.two, R.id.openleft, R.id.picture_text, R.id.movie_text, R.id.music_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.two:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.openleft:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.picture_text:
                vPager.setCurrentItem(0);
                break;
            case R.id.movie_text:
                vPager.setCurrentItem(1);
                break;
            case R.id.music_text:
                vPager.setCurrentItem(2);
                break;
        }
    }

    private void resetColor() {
        pictureText.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        movieText.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        musicText.setTextColor(getResources().getColor(R.color.main_top_tab_color));
    }

}
