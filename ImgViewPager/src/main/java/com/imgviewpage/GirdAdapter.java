package com.imgviewpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by D&LL on 2017/4/24.
 */

public class GirdAdapter extends BaseAdapter {
    private int[] imgs;
    private Context context;

    public GirdAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gird, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ViewHolder viewHolder = holder;
        holder.imageView.setImageResource(imgs[position]);
        return convertView;
    }
}
