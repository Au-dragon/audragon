package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.HotBean;
import com.example.geek.model.HotModel;
import com.example.geek.net.HotCallBack;
import com.example.geek.view.HotView;

public class HotPresenter extends BasePresenter<HotView> {

    private HotModel hotModel;

    @Override
    protected void initModel() {
        hotModel = new HotModel();
    }
    public void getDatas(){
        hotModel.getDatas(new HotCallBack() {
            @Override
            public void onSuccess(HotBean hotBean) {
                mView.onSuccess(hotBean);
            }
        });
    }
}
