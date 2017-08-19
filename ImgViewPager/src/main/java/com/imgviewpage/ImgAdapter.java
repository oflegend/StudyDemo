package com.imgviewpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by D&LL on 2017/4/24.
 */

public class ImgAdapter extends PagerAdapter {
    private int[] imgs;
    private Context context;

    public ImgAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
        View view = LayoutInflater.from(context).inflate(R.layout.imageview,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        imageView.setImageResource(imgs[position]);
        container.addView(view);
        return view;
    }

}
