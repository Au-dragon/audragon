package com.example.geek.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView,P extends BasePresenter>extends Fragment implements BaseView{
protected P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, view);
       mPresenter = initPresenter();
        if(mPresenter!=null){
            mPresenter.setView((V) this);
        }

        initView();
        initData();
        initListenter();

        return view;
    }

    protected void initData() {
    }

    protected void initListenter() {
    }

    protected void initView() {
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();
//欧俊龙 1808D 4/18
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mPresenter.onDestory();
        mPresenter=null;
    }
}
