package com.example.geek.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.ZhVpadapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.ZhiHuDailyNewsP;
import com.example.geek.view.ZhiHuDailayNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZhiHuDailyNewsFragment extends BaseFragment  {
    @BindView(R.id.myTab)
    TabLayout myTab;
    @BindView(R.id.myViewPager)
    ViewPager myViewPager;
    Unbinder unbinder;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private ArrayList<Integer> tabs=new ArrayList<>();
    private ZhVpadapter vpadapter;

    @Override
    protected ZhiHuDailyNewsP initPresenter() {
        return new ZhiHuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.zhihu_fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        fragments.add(new ZhDailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new HotFragment());
        tabs.add(R.string.dialy);
        tabs.add(R.string.theme);
        tabs.add(R.string.special);
        tabs.add(R.string.hot);
        vpadapter = new ZhVpadapter(getChildFragmentManager(), fragments, getContext(), tabs);
        myViewPager.setAdapter(vpadapter);
        myTab.setupWithViewPager(myViewPager);
    }


}
