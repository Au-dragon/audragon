package com.example.geek.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.geek.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter> extends AppCompatActivity implements BaseView {
protected P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter=initPresenter();
        if(mPresenter!=null){
            mPresenter.setView((V)this);
        }
        initListenter();
        initView();
    }

    protected void initView() {
    }

    protected void initListenter() {

    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
        mPresenter=null;
    }
}
