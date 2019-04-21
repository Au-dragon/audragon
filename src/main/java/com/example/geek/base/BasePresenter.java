package com.example.geek.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    protected V mView;
    protected ArrayList<BaseModel> models=new ArrayList<>();
    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    protected void setView(V mbaseView){
        this.mView=mbaseView ;
    }
    public void onDestory(){
        mView=null;
        if(models.size()>0){
            for (int i = 0; i < models.size(); i++) {
                models.get(i).onDestory();
            }
        }
    }

}
