package com.leador.toggleview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xuwei on 2016/12/18.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;

    MyAdapter(Context context,ArrayList list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder;
        if (convertView == null) {
            holder = new MyHolder();
            convertView = View.inflate(context,R.layout.item_list,null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(holder);
        }else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.tvTitle.setText(list.get(position));
        return convertView;
    }

    class MyHolder{
        TextView tvTitle;
    }
}
