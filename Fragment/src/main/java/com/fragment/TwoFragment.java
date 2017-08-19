package com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by D&LL on 2017/4/25.
 */

public class TwoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater
            , ViewGroup container, Bundle data) {
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER);
        tv.setText("第2个Fragment");
        tv.setTextSize(40);
        return tv;
    }
}
