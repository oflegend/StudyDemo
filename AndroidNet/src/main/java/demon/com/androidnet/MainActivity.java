package demon.com.androidnet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private GetPostUrl getPost = GetPostUrl.getGetPost();
    private HttpConnectionUtil httpConnect = HttpConnectionUtil.getHttp();
    @BindView(R.id.iv_url)
    ImageView ivUrl;
    @BindView(R.id.url)
    Button url;
    @BindView(R.id.tv_urlConnect)
    TextView tvUrlConnect;
    @BindView(R.id.urlConnect)
    Button urlConnect;
    @BindView(R.id.tv_httpurlconnect)
    TextView tvHttpurlconnect;
    @BindView(R.id.httpurlconnect)
    Button httpurlconnect;
    private Bitmap bitmap;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                ivUrl.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.url, R.id.urlConnect, R.id.httpurlconnect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.url:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://192.168.199.100:8080/image/D.png");
                            InputStream is = url.openStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            handler.sendEmptyMessage(0x123);
                            OutputStream os = openFileOutput("demon.png", MODE_PRIVATE);
                            byte[] bytes = new byte[1024];
                            int has;
                            while ((has = is.read(bytes)) > 0) {
                                os.write(bytes, 0, has);
                            }
                            is.close();
                            os.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.urlConnect:
                String a = getPost.get("http://192.168.199.100:8080/demon/get.jsp");
                Map<String, String> cmap = new HashMap<>();
                cmap.put("name", "demon");
                cmap.put("pass", "123456");
                String b = getPost.post("http://192.168.199.100:8080/demon/login.jsp", cmap);
                tvUrlConnect.setText(a + "\n" + b);
                break;
            case R.id.httpurlconnect:
                String x = httpConnect.getRequset("http://192.168.199.100:8080/demon/get.jsp");
                Map<String, String> map = new HashMap<>();
                map.put("name", "demon");
                map.put("pass", "123456");
                String y = httpConnect.postRequset("http://192.168.199.100:8080/demon/login.jsp", map);
                tvHttpurlconnect.setText(x + "\n" + y);
                break;
        }
    }
}
