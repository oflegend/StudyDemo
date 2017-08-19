package demon.com.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);//设置支持JavaScript脚本
        webView.setWebViewClient(new WebViewClient());//防止调用浏览器打开
        webView.loadUrl("http://blog.csdn.net/demonliuhui");

    }
}
