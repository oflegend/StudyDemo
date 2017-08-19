package demon.com.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.data)
    TextView data;
    private GetPostUtil getPost = GetPostUtil.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send)
    public void onViewClicked() {
        String a = getPost.get("http://192.168.199.100:8080/demon/get.jsp");
        Map<String, String> cmap = new HashMap<>();
        cmap.put("name", "demon");
        cmap.put("pass", "123456");
        String b = getPost.post("http://192.168.199.100:8080/demon/login.jsp", cmap);
        data.setText(a + "\n" + b);
    }
}
