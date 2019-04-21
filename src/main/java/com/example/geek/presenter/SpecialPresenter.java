package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.SpecialBean;
import com.example.geek.model.SpecialModel;
import com.example.geek.net.SpecialCallBack;
import com.example.geek.view.SpecialView;

public class SpecialPresenter extends BasePresenter<SpecialView> {

    private SpecialModel specialModel;

    @Override
    protected void initModel() {
        specialModel = new SpecialModel();
    }
    public void getDatas(){
        specialModel.getDatas(new SpecialCallBack() {
            @Override
            public void onSuccess(SpecialBean specialBean) {
                mView.onSuccess(specialBean);
            }
        });
    }
}
