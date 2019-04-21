package com.example.geek.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.activity.ShowActivity;
import com.example.geek.adapter.GoldVpAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.Constants;
import com.example.geek.bean.GoldBean;
import com.example.geek.presenter.GoldP;
import com.example.geek.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GoldFragment extends BaseFragment<GoldV, GoldP> implements GoldV {


    @BindView(R.id.myTab)
    TabLayout myTab;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.myViewPag)
    ViewPager myViewPag;
    Unbinder unbinder;
    private ArrayList<GoldBean> list;
    private ArrayList<Fragment> fragments;

    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gold_fragment;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add(new GoldBean("工具资源",true));
        list.add(new GoldBean("Android",true));
        list.add(new GoldBean("iOS",true));
        list.add(new GoldBean("设计",true));
        list.add(new GoldBean("产品",true));
        list.add(new GoldBean("阅读",true));
        list.add(new GoldBean("前端",true));
        list.add(new GoldBean("后端",true));
        setFragment();
    }
    public void setFragment(){
        fragments = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            GoldDateFragment goldDateFragment = new GoldDateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",list.get(i).getTitle());
            goldDateFragment.setArguments(bundle);
            if(list.get(i).isChecked()){
                fragments.add(goldDateFragment);
            }
        }
        GoldVpAdapter vpAdapter = new GoldVpAdapter(getChildFragmentManager(), list, fragments);
        myViewPag.setAdapter(vpAdapter);
        myTab.setupWithViewPager(myViewPag);

    }
    @OnClick(R.id.iv)
    public void onClick(){
go2ShowActivity();
    }
    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,list);
        startActivityForResult(intent,100);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                list = (ArrayList<GoldBean>) data.getSerializableExtra(Constants.DATA);
                //刷新界面
                setFragment();
            }
        }
    }
}
