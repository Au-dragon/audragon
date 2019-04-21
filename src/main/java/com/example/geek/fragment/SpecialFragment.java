package com.example.geek.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.geek.R;
import com.example.geek.adapter.ZhSpecialRvAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.SpecialBean;
import com.example.geek.presenter.SpecialPresenter;
import com.example.geek.view.SpecialView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//专栏 fragment
public class SpecialFragment extends BaseFragment<SpecialView, SpecialPresenter> implements SpecialView {
    @BindView(R.id.special_recycler)
    RecyclerView specialRecycler;
    @BindView(R.id.myMart)
    SmartRefreshLayout myMart;
    Unbinder unbinder;
    private ArrayList<SpecialBean.DataBean> list = new ArrayList<>();
    private ZhSpecialRvAdapter zhSpecialRvAdapter;

    @Override
    protected SpecialPresenter initPresenter() {
        return new SpecialPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.special_item;
    }

    @Override
    protected void initView() {
        super.initView();
        specialRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        zhSpecialRvAdapter = new ZhSpecialRvAdapter(list, getContext());
        specialRecycler.setAdapter(zhSpecialRvAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getDatas();
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        list.addAll(specialBean.getData());
        zhSpecialRvAdapter.setList(list);
    }



}
