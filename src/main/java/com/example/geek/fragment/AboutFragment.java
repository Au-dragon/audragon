package com.example.geek.fragment;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.EmptyP;
import com.example.geek.view.EmptyV;

public class AboutFragment extends BaseFragment<EmptyV,EmptyP> implements EmptyV{
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.about_fragment;
    }
}
