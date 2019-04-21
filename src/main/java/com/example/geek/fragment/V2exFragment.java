package com.example.geek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.activity.JieDianDaoHActivity;
import com.example.geek.adapter.V2ViewAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.V2Bean;
import com.example.geek.bean.V2ListBean;
import com.example.geek.presenter.V2exP;
import com.example.geek.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//欧俊龙 1808D 4/21
public class V2exFragment extends BaseFragment<V2exV, V2exP> implements V2exV {
    @BindView(R.id.myV2Tab)
    TabLayout myV2Tab;
    @BindView(R.id.myViewP)
    ViewPager myViewP;
    @BindView(R.id.iv)
    ImageView iv;
    Unbinder unbinder;
    private String mUrl = "https://www.v2ex.com/";
    private static final String TAG = "V2exFragment";


    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.v2ex_fragment;
    }

    @Override
    protected void initData() {
//欧俊龙 1808D 4/21
        new Thread(new Runnable() {
            private ArrayList<V2Bean> tabsList;


            @Override
            public void run() {
                try {
                    tabsList = new ArrayList<>();
                    Document doc = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素,因为只有一个,直接调用了first()
                    Element tabs = doc.select("div#Tabs").first();
                    //查找带有href属性的a元素
                    Elements allTabs = tabs.select("a[href]");
                    for (Element element : allTabs) {
                        String linkHref = element.attr("href");
                        String tab = element.text();
                        //Log.d(TAG, "linkHref: "+linkHref+",tab:"+tab);
                        V2Bean bean = new V2Bean(linkHref, tab);
                        tabsList.add(bean);
                        setFragment(tabsList);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setFragment(final ArrayList<V2Bean> tabsList) {
        final ArrayList<Fragment> fragments=new ArrayList<>();
        for (int i = 0; i < tabsList.size(); i++) {
            V2DateFragment v2DateFragment = new V2DateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("link",tabsList.get(i).getLink());
            v2DateFragment.setArguments(bundle);
            fragments.add(v2DateFragment);
        }
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               initView(tabsList,fragments);
           }
       });
    }

    private void initView(ArrayList<V2Bean> tabsList, ArrayList<Fragment> fragments) {
        V2ViewAdapter adapter = new V2ViewAdapter(getChildFragmentManager(), tabsList, fragments);
        myViewP.setAdapter(adapter);
        myV2Tab.setupWithViewPager(myViewP);
        initView();
    }
    @OnClick(R.id.iv)
    public void onClick(){
        Intent intent = new Intent(getContext(),JieDianDaoHActivity.class);
        startActivity(intent);

    }
}
