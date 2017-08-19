package demon.com.asyntaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by D&LL on 2017/5/16.
 */

public class DownloadTask extends AsyncTask<URL, Integer, String> {
    ProgressDialog dialog;
    Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(URL... params) {
        Response response = null;
        OkHttpClient client;
        try {
            client = new OkHttpClient();
            Request request = new Request.Builder().url(params[0]).build();
            response = client.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();//获取文件输入流
            File appDir = new File(Environment.getExternalStorageDirectory(),
                    "Download");//存储路径为SD卡根目录Download文件
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".mp3";
            File file = new File(appDir, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[2048];
            int len = 0;
            int has = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                has++;
                fileOutputStream.write(buffer, 0, len);
                publishProgress(has);
            }
            fileOutputStream.close();
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("温馨提示");
        dialog.setMessage("下载中...");
        dialog.setCancelable(false);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(false);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        dialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
    }
}
