package demon.com.asyntaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.down)
    Button down;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.down)
    public void onViewClicked(){
        DownloadTask task = new DownloadTask(this);
        try {
            task.execute(new URL("http://192.168.199.100:8080/mp3/Me.mp3"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
