package com.dbhelperdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by D&LL on 2017/3/28.
 */

public class ResultActivity extends Activity {
    @BindView(R.id.show)
    ListView show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        ButterKnife.bind(this);
        Bundle data = getIntent().getExtras();
        List<Map<String, String>> list = (List<Map<String, String>>) data.getSerializable("data");
        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, list, R.layout.line,
                new String[]{"_id", "word", "detail"}, new int[]{R.id._id, R.id.word, R.id.detail});
        show.setAdapter(adapter);
    }
}
