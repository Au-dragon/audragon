package com.example.geek.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.V2ReAdapter;
import com.example.geek.bean.V2ListBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class V2DateFragment extends Fragment {
    private View view;
    private RecyclerView mMyRec;
    private ArrayList<V2ListBean> v2List=new ArrayList<>();
    private V2ReAdapter reAdapter;
    private Element commentPeople;
    private Element author;
    private String href;
    private String commentCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.v2dateitem, null);
        initView(view);
        getDatas();
        return view;
    }

    private void initView(View view) {

        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMyRec.setLayoutManager(new LinearLayoutManager(getContext()));
        reAdapter = new V2ReAdapter(v2List, getContext());
        mMyRec.setAdapter(reAdapter);
    }
    public void getDatas(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<V2ListBean> v2=new ArrayList<>();
                Bundle arguments = getArguments();
                String link = arguments.getString("link");


                try {
                    Log.i("TAG","============https://www.v2ex.com"+link);
                    Document  doc = Jsoup.connect("https://www.v2ex.com"+link).get();
                    Elements items = doc.select("div.cell.item");
                    for (Element element : items) {
                        //图片
                        Element image = element.select("table tbody tr td > a >img.avatar").first();
                        String src = image.attr("src");
                        //Log.d(TAG, "图片: "+src);

                        //评论,有可能没有,需要判空
                        Element comment = element.select("table tbody tr td >a.count_livid").first();
                        if (comment != null) {
                            commentCount = comment.text();
                            href = comment.attr("href");
                            //Log.d(TAG, "评论数量: "+commentCount+",href:"+href);
                        }

                        //新闻的主体信息
                        Element titleElement = element.select("table tbody tr td span.item_title > a").first();
                        String text = titleElement.text();
                        // Log.d(TAG, "标题: " + text);

                        //评论的信息
                        Elements topicElement = element.select("table tbody tr td span.topic_info");
                        String topic = topicElement.text();
                        // Log.d(TAG, "topic: " + topic);

                        Element secondTab = topicElement.select("a.node").first();
                        String text1 = secondTab.text();
                        // Log.d(TAG, "二类的Tab:" + text1);

                        Elements people = topicElement.select("strong > a");
                        if (people.size() > 0) {
                            author = people.get(0);
                            //Log.d(TAG, "作者: "+ author.text());
                        }

                        if (people.size() > 1) {
                            commentPeople = people.get(1);
                            //Log.d(TAG, "评论人: " + commentPeople.text());
                        }
                        V2ListBean v2ListBean = new V2ListBean(src, commentCount, text
                                , topic, text1, author.text(), commentPeople.text());
                        v2.add(v2ListBean);
                        // Log.i("TAG", "========v2ListBean====" + v2ListBean.toString());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            v2List.addAll(v2);
                            reAdapter.setList(v2List);
                            reAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
