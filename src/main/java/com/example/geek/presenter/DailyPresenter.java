package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.ZhiHuBean;
import com.example.geek.model.ZhiHuModel;
import com.example.geek.net.ZhiHuCallBack;
import com.example.geek.view.DialyView;

public class DailyPresenter extends BasePresenter<DialyView> {
    private ZhiHuModel zhiHuModel;

    @Override
    protected void initModel() {
        zhiHuModel = new ZhiHuModel();
    }
    public void getDatas() {
        zhiHuModel.getDatas(new ZhiHuCallBack() {
            @Override
            public void onSuccess(ZhiHuBean zhiHuBean) {
                mView.onSuccess(zhiHuBean);
            }
        });
    }
}
