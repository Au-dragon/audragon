package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.BeforeBean;
import com.example.geek.model.BeforeModel;
import com.example.geek.net.BeforeCallBack;
import com.example.geek.view.BeforeBeanView;

public class BeforePresenter extends BasePresenter<BeforeBeanView> {

    private BeforeModel beforeModel;
    private BeforeBeanView beforeBeanView;

    public BeforePresenter(BeforeBeanView beforeBeanView) {
        this.beforeBeanView = beforeBeanView;
    }

    @Override
    protected void initModel() {
        beforeModel = new BeforeModel();
    }
    public void getDatas(String data){
        beforeModel.getDatas(new BeforeCallBack() {
            @Override
            public void onSuccess(BeforeBean beforeBean) {
                beforeBeanView.onSuccesss(beforeBean);
            }
        },data);
    }
}
