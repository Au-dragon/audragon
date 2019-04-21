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
import com.example.geek.bean.ZhiHuBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class ZhRvAdapter extends RecyclerView.Adapter {
    private ArrayList<ZhiHuBean.StoriesBean> list;
    private Context context;
    private ArrayList<ZhiHuBean.TopStoriesBean> banners;
    private int TYPE_BANNER = 0;
    private int TYPE_Text = 1;
    private int TYPE_List = 2;

    public ZhRvAdapter(ArrayList<ZhiHuBean.StoriesBean> list, Context context, ArrayList<ZhiHuBean.TopStoriesBean> banners) {
        this.list = list;
        this.context = context;
        this.banners = banners;
    }

    public void setList(ArrayList<ZhiHuBean.StoriesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setBanners(ArrayList<ZhiHuBean.TopStoriesBean> banners) {
        this.banners = banners;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (banners.size() > 0) {
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_Text;
            } else {
                return TYPE_List;
            }
        } else {
            if (position == 0) {
                return TYPE_Text;
            } else {
                return TYPE_List;
            }
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==TYPE_BANNER){
            View view = LayoutInflater.from(context).inflate(R.layout.zhrvadapter_banner_item, null);
            return new BannerViewHolder(view);
        }else if(i==TYPE_Text){
            View view = LayoutInflater.from(context).inflate(R.layout.zhrvadapter_text_item, null);
            return new TextViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.zhrvadapter_list_item, null);
            return new ListViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = getItemViewType(i);

        if(itemViewType==TYPE_BANNER){
            BannerViewHolder bannerViewHolder= (BannerViewHolder) viewHolder;
            bannerViewHolder.banner.setImages(banners);
            bannerViewHolder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    ZhiHuBean.TopStoriesBean bean= (ZhiHuBean.TopStoriesBean) path;
                    Glide.with(context).load(bean.getImage()).into(imageView);
                }
            }).start();
        }else if(itemViewType==TYPE_Text){
            TextViewHolder textViewHolder= (TextViewHolder) viewHolder;
            textViewHolder.t1.setText("今日新闻");
        }else {
            ListViewHolder listViewHolder= (ListViewHolder) viewHolder;
            int newPotion=i-1;
            if(banners.size()>0){
                newPotion=i-1-1;
            }
            listViewHolder.text.setText(list.get(newPotion).getTitle());

            Glide.with(context).load(list.get(newPotion).getImages().get(0)).into(listViewHolder.img);
        }
    }

    @Override
    public int getItemCount() {
        if(banners.size()>0){
            return list.size()+2;
        }else {
            return list.size()+1;
        }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.myBanner);
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        private final TextView t1;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView text;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.text);
        }
    }
}
