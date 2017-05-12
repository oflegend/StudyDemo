package demon.com.progressdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.h_view)
    RecyclerView hView;
    private List<Info> list = new ArrayList<>();
    private RecycleAdapter adapter;
    private HRecycleAdapter hadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        initData();
        initView();


    }

    private void initView() {
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleAdapter(list);
        recycleView.setAdapter(adapter);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        hView.setLayoutManager(lm);
        hadapter = new HRecycleAdapter(list);
        hView.setAdapter(hadapter);
        
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Info info = new Info(R.mipmap.ic_launcher_round, "第" + (i + 1) + "条数据");
            list.add(info);
        }
    }

}
