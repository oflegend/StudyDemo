package demon.com.androidnet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by D&LL on 2017/5/8.
 */

public class GetPostUrl {
    public static GetPostUrl getPost = new GetPostUrl();

    public static GetPostUrl getGetPost() {
        return getPost;
    }


    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String get(final String url) {
        final StringBuilder sb = new StringBuilder();
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                BufferedReader br = null;
                URLConnection conn;
                try {
                    URL geturl = new URL(url);
                    conn = geturl.openConnection();
                    conn.connect();
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    br = new BufferedReader(isr);
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    System.out.println(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return sb.toString();
            }
        });
        new Thread(task).start();
        String s = null;
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String post(final String url, final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                DataOutputStream out = null;
                BufferedReader br = null;
                URLConnection conn;
                URL posturl = new URL(url);
                try {
                    conn = posturl.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.connect();

                    out = new DataOutputStream(conn
                            .getOutputStream());
                    StringBuilder request = new StringBuilder();
                    for (String key : map.keySet()) {
                        request.append(key + "=" + URLEncoder.encode(map.get(key), "UTF-8") + "&");
                    }
                    out.writeBytes(request.toString());
                    out.flush();
                    out.close();
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    System.out.println(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        br.close();

                    }
                    if (out != null) {
                        out.close();
                    }

                }
                return sb.toString();
            }
        });
        String s = null;
        new Thread(task).start();
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
