package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ZhVpadapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private Context context;
    private ArrayList<Integer> tabs;

    public ZhVpadapter(FragmentManager fm, ArrayList<Fragment> fragments, Context context, ArrayList<Integer> tabs) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(tabs.get(position));
    }
}
