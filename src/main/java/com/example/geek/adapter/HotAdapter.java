package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.HotBean;

import java.util.ArrayList;

public class HotAdapter extends RecyclerView.Adapter {
    private ArrayList<HotBean.RecentBean> list;
    private Context context;

    public HotAdapter(ArrayList<HotBean.RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<HotBean.RecentBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_adapter_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder= (MyViewHolder) viewHolder;
        myViewHolder.text.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getThumbnail()).into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.text);
        }
    }
}
