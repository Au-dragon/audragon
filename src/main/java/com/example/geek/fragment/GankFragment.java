package com.example.geek.fragment;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.GankP;
import com.example.geek.view.GankV;

public class GankFragment extends BaseFragment<GankV,GankP> implements GankV {
    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment;
    }
}
