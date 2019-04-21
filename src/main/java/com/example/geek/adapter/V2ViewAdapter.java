package com.example.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.geek.bean.V2Bean;

import java.util.ArrayList;

public class V2ViewAdapter extends FragmentPagerAdapter {
    private ArrayList<V2Bean> tabs;
    private ArrayList<Fragment> fragments;
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    public V2ViewAdapter(FragmentManager fm, ArrayList<V2Bean> tabs, ArrayList<Fragment> fragments) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTab();
    }
}
