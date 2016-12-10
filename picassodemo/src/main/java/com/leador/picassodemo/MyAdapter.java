package com.leador.picassodemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xuwei on 2016/12/4.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<NewsBean> list;
    MyItemClickListner listner;

    public MyAdapter(Context context,List<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(MyItemClickListner listener) {
        this.listner = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_review,null);
        MyViewHolder holder = new MyViewHolder(view,listner);
        return holder;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDetail.setText(list.get(position).getDescription());
        holder.tvTime.setText(list.get(position).getCtime());
        list.get(position).setPicUrl("http://i.imgur.com/DvpvklR.png");
        Picasso.with(context).load(list.get(position).getPicUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface MyItemClickListner {
        void onItemClick(View view,int position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private TextView tvDetail;
        private TextView tvTime;
        private ImageView img;
         MyItemClickListner listner;
        public MyViewHolder(View itemView,MyItemClickListner listner) {
            super(itemView);
            this.listner = listner;
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            img = (ImageView) itemView.findViewById(R.id.iv);
        }

        @Override
        public void onClick(View view) {
            if (listner != null) {
                listner.onItemClick(view,getPosition());
            }
        }

    }
}
