package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.V2ListBean;

import java.util.ArrayList;

public class V2ReAdapter extends RecyclerView.Adapter {
    private ArrayList<V2ListBean> list;
    private Context context;

    public V2ReAdapter(ArrayList<V2ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<V2ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.v2date_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder= (MyViewHolder) viewHolder;
        Log.i("TAG","============"+list.get(i).getImg());
        Glide.with(context).load("https:"+list.get(i).getImg()).into(myViewHolder.img);
        myViewHolder.t1.setText(list.get(i).getAuthor());
        myViewHolder.t2.setText(list.get(i).getTopic());
        myViewHolder.t3.setText(list.get(i).getText1());
        myViewHolder.t4.setText(list.get(i).getCommentCount());
        myViewHolder.t5.setText(list.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView t1;
        private final TextView t2;
        private final TextView t3;
        private final TextView t4;
        private final TextView t5;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            t5 = itemView.findViewById(R.id.t5);
        }
    }
}
