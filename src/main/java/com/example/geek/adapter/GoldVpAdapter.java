package com.example.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geek.bean.GoldBean;

import java.util.ArrayList;

public class GoldVpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<GoldBean> list;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> newList=new ArrayList<>();

    public GoldVpAdapter(FragmentManager fm, ArrayList<GoldBean> list, ArrayList<Fragment> fragments) {
        super(fm);
        this.list = list;
        this.fragments = fragments;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isChecked()){
                newList.add(list.get(i).getTitle());
            }
        }
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
        return newList.get(position) ;
    }
}
