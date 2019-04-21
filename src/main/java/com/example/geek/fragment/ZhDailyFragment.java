package com.example.geek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.geek.R;
import com.example.geek.activity.FormerActivity;
import com.example.geek.adapter.BeforAdapter;
import com.example.geek.adapter.ZhRvAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.BeforeBean;
import com.example.geek.bean.ZhiHuBean;
import com.example.geek.presenter.BeforePresenter;
import com.example.geek.presenter.DailyPresenter;
import com.example.geek.utils.CircularAnimUtil;
import com.example.geek.view.BeforeBeanView;
import com.example.geek.view.DialyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 日报 fragment
 * 欧俊龙 1808D 4/18
 */
public class ZhDailyFragment extends BaseFragment<DialyView, DailyPresenter> implements DialyView,BeforeBeanView {
    @BindView(R.id.myRecycler)
    RecyclerView myRecycler;

    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.muSmart)
    SmartRefreshLayout muSmart;
private boolean bo=true;
    private ArrayList<ZhiHuBean.StoriesBean> list = new ArrayList<>();
    private ArrayList<ZhiHuBean.TopStoriesBean> banners = new ArrayList<>();
    private ArrayList<BeforeBean.StoriesBean> stros=new ArrayList<>();
    private ZhRvAdapter zhRvAdapter;

    private String date;
    private BeforAdapter beforAdapter;
    private String data1;

    @Override
    protected DailyPresenter initPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.zhdaily_item;
    }

    @Override
    public void onSuccess(ZhiHuBean zhiHuBean) {
        list.addAll(zhiHuBean.getStories());
        banners.addAll(zhiHuBean.getTop_stories());
        zhRvAdapter.setBanners(banners);
        zhRvAdapter.setList(list);
        date = zhiHuBean.getDate();
    }

    @OnClick(R.id.fab_calender)
    public void click() {
        Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), FormerActivity.class);
        //CircularAnimUtil.startActivityForResult(getActivity(),intent,1,fabCalender,R.anim.in_anim);
        startActivityForResult(intent,1);
       getActivity().overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
    }

    @Override
    protected void initView() {
        super.initView();

            myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            zhRvAdapter = new ZhRvAdapter(list, getContext(), banners);
            myRecycler.setAdapter(zhRvAdapter);




    }

    @Override
    protected void initData() {
        super.initData();
        if(bo){
            mPresenter.getDatas();
        }else {
            initData2(data1);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            data1 = data.getStringExtra("data");
            Toast.makeText(getContext(), data1, Toast.LENGTH_SHORT).show();
            if(data1.equals(date)){
                initData();
                bo=true;
            }else {
                initData2(data1);
                bo=false;
            }
        }
    }

    private void initData2(String data) {
        BeforePresenter beforePresenter = new BeforePresenter(this);
        beforePresenter.getDatas(data);
    }

    @Override
    public void onSuccesss(BeforeBean beforeBean) {
        stros.clear();
        stros.addAll(beforeBean.getStories());
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        beforAdapter = new BeforAdapter(stros, getContext());
        myRecycler.setAdapter(beforAdapter);

    }
}
