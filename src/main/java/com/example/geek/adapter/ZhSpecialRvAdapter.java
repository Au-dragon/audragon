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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.geek.R;
import com.example.geek.bean.SpecialBean;

import java.util.ArrayList;

public class ZhSpecialRvAdapter extends RecyclerView.Adapter {
    private ArrayList<SpecialBean.DataBean> list;
    private Context context;

    public ZhSpecialRvAdapter(ArrayList<SpecialBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<SpecialBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhspecial_adapter_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            MyViewHolder myViewHolder= (MyViewHolder) viewHolder;

        Glide.with(context).load(list.get(i).getThumbnail()).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(myViewHolder.img);
        myViewHolder.t1.setText(list.get(i).getDescription());
        myViewHolder.t2.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView t1;
        private final TextView t2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.myImg);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
        }
    }
}
