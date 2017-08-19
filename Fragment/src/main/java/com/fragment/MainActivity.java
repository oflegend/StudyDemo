package com.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;

    private Fragment oneFragment, twoFragment, threeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //initOne();
        initTwo();
    }

    private void initOne() {
        oneFragment = new OneFragment();
        getFragmentManager().beginTransaction().replace(R.id.linear,oneFragment).commit();

        reColor();
        one.setTextColor(getResources().getColor(R.color.colorAccent));
    }



    private void initTwo() {
      twoFragment = new TwoFragment();
        getFragmentManager().beginTransaction().replace(R.id.linear,twoFragment).commit();

        reColor();
        two.setTextColor(getResources().getColor(R.color.colorAccent));
    }
    private void initThree() {
       threeFragment = new ThreeFragment();
        getFragmentManager().beginTransaction().replace(R.id.linear,threeFragment).commit();

        reColor();
        three.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    @OnClick({R.id.one, R.id.two, R.id.three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                initOne();
                break;
            case R.id.two:
                initTwo();
                break;
            case R.id.three:
                initThree();
                break;
        }
    }
    private void reColor() {
        one.setTextColor(getResources().getColor(R.color.colorPrimary));
        two.setTextColor(getResources().getColor(R.color.colorPrimary));
        three.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
}
