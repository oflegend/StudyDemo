package demon.com.progressdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterfallActivity extends AppCompatActivity {

    @BindView(R.id.waterfall)
    RecyclerView waterfall;
    private WaterfallAdapter adapter;
    private List<Info> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall);
        ButterKnife.bind(this);
        initData();
        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        waterfall.setLayoutManager(sgm);
        adapter = new WaterfallAdapter(list);
        waterfall.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Info info = new Info(R.mipmap.ic_launcher_round, randomString("第" + (i + 1) + "条数据"));
            list.add(info);
        }

    }

    private String randomString(String s) {
        Random random = new Random();
        int len = random.nextInt(10) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(s);

        }
        return builder.toString();
    }
}
