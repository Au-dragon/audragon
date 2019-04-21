package com.example.geek.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.HotAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.BasePresenter;
import com.example.geek.bean.HotBean;
import com.example.geek.presenter.HotPresenter;
import com.example.geek.view.HotView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//热门 fragment
public class HotFragment extends BaseFragment<HotView,HotPresenter> implements HotView {
    @BindView(R.id.myRecy)
    RecyclerView myRecy;
    Unbinder unbinder;
    private ArrayList<HotBean.RecentBean> list=new ArrayList<>();
    private HotAdapter hotAdapter;

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hot_item;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getDatas();

    }

    @Override
    protected void initView() {
        super.initView();
        myRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        hotAdapter = new HotAdapter(list, getContext());
        myRecy.setAdapter(hotAdapter);
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        list.addAll(hotBean.getRecent());
        hotAdapter.setList(list);
        hotAdapter.notifyDataSetChanged();
    }
}
