package com.mvpdemo.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.mvpdemo.DialogUtil;
import com.mvpdemo.OkHttp3;
import com.mvpdemo.view.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 方法模型层
 * Created by D&LL on 2017/3/13.
 */

public class Model {
    private static Model instance = new Model();//单例

    public static Model getInstance() {
        return instance;
    }

    private ProgressDialog dialog;


    public OkHttpClient client = OkHttp3.getClient();

    /**
     * 创建新线程实现同步get请求
     *
     * @param context
     * @param url
     * @return
     * @throws Exception
     */

    public String getSysn(final Context context, final String url) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                return result;
            }
        });
        new Thread(task).start();
        return task.get();
    }

    /**
     * 异步get请求
     *
     * @param context
     * @param url
     * @return
     * @throws Exception
     */
    public void getSynchronized(final Context context, final String url, final ICallBack callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.result(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.result(response.body().string());
                } else {
                    callback.result("请求失败！");
                }
            }
        });

    }

    /**
     * post方式提交Map
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public void postMap(final Context context, final String url, Map<String, String> map, final ICallBack callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            //增强for循环遍历
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().post(formBody).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.result(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.code() + "~~~~~~~~");
                    callback.result(response.body().string());
                } else {
                    callback.result("请求失败！");
                }
            }
        });
    }

    /**
     * post方式提交Json
     *
     * @param context
     * @param url
     * @param content
     * @param callback
     */
    public void postJson(final Context context, String url, String content, final ICallBack callback) {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"), content);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.result(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.result(response.body().string());
                } else {
                    callback.result("请求失败！");
                }
            }
        });
    }

    /**
     * 上传文件
     *
     * @param context
     * @param url
     * @param file
     * @param callback
     */
    private void postFile(final Context context, String url, File file, final ICallBack callback) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * 异步下载文件
     *
     * @param context
     * @param url
     * @param name
     */
    public void downAsynFile(final Context context, String url, final String name, final BitmapCallBack callback) {
        dialog = DialogUtil.getProgressDialog(context);
        dialog.show();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();//获取文件输入流
                    Bitmap bitmap = FileUtil.saveFile(name, inputStream);
                    callback.imgBitmap(bitmap);
                    System.out.println("下载成功！");

                } else {
                    System.out.println("下载失败！");
                }
                response.close();//下载文件耗时较久，完成后需要手动关闭请求
                dialog.dismiss();
            }
        });
    }
}