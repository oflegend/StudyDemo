package com.mvpdemo.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.mvpdemo.model.BitmapCallBack;
import com.mvpdemo.model.ICallBack;
import com.mvpdemo.model.Model;
import com.mvpdemo.view.MainView;

import java.util.Map;

/**
 * 方法操作层
 * Created by D&LL on 2017/3/13.
 */

public class MainPresenter {

    private Model model = Model.getInstance();
    private MainView mainView;
    private Context context;

    public MainPresenter(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;

    }

    public void getRequest(String url) {

        model.getSynchronized(context, url, new ICallBack() {
            @Override
            public void result(String s) {
                System.out.println(s);
                mainView.getView(s);

            }
        });
    }

    public void postMap(String url, Map<String, String> map) {
        model.postMap(context, url, map, new ICallBack() {
            @Override
            public void result(String s) {
                System.out.println(s);
                mainView.postView(s);

            }
        });
    }

    public void downFile(String url,String name){
        model.downAsynFile(context, url, name, new BitmapCallBack() {
            @Override
            public void imgBitmap(Bitmap bitmap) {
                mainView.imgView(bitmap);
            }
        });
    }

}
