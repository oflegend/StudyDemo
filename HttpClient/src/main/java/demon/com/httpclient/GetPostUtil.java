package demon.com.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


/**
 * Created by D&LL on 2016/9/20.
 * 关于post的一些方法类
 */
public class GetPostUtil {
    private static GetPostUtil instance = new GetPostUtil();

    public HttpClient httpClient = getThreadSafeClient();

    //工具类的实例化
    public static GetPostUtil getInstance() {

        return instance;
    }

    public static DefaultHttpClient getThreadSafeClient() {

        DefaultHttpClient client = new DefaultHttpClient();
        ClientConnectionManager mgr = client.getConnectionManager();
        HttpParams params = client.getParams();

        client = new DefaultHttpClient(
                new ThreadSafeClientConnManager(params,
                        mgr.getSchemeRegistry()), params);

        return client;
    }

    public String get(final String url) {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            String result;

            @Override
            public String call() throws Exception {
                HttpGet get = new HttpGet(url);
                //get.setHeader(); //设置请求头部
                HttpResponse response = httpClient.execute(get);
                if (response.getStatusLine().getStatusCode() == 200) {
                    result = EntityUtils.toString(response.getEntity());
                }
                return result;
            }
        });
        new Thread(task).start();//处理获得返回值
        String data = null;
        try {
            data = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

    /**
     * post请求
     *
     * @param url
     * @param maps
     * @return
     */
    public String post(final String url, final Map<String, String> maps) {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>() {
                    String result;

                    @Override
                    public String call() {
                        try {
                            HttpPost post = new HttpPost(url);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            for (String key : maps.keySet()) {
                                params.add(new BasicNameValuePair(key, maps.get(key)));
                            }
                            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            HttpResponse response = httpClient.execute(post);
                            if (response.getStatusLine().getStatusCode() == 200) {
                                result = EntityUtils.toString(response.getEntity());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                });
        new Thread(task).start();//处理获得返回值
        String data = null;
        try {
            data = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
