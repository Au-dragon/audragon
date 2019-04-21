package com.example.geek.model;

import android.util.Log;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.SpecialBean;
import com.example.geek.net.SpecialCallBack;
import com.example.geek.net.ZhiHuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialModel extends BaseModel {
    public void getDatas(final SpecialCallBack callBack){
        Retrofit build = new Retrofit.Builder().baseUrl(ZhiHuService.zhiHuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhiHuService zhiHuService = build.create(ZhiHuService.class);
        Observable<SpecialBean> datas2 = zhiHuService.getDatas2();
        datas2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecialBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SpecialBean specialBean) {
                        Log.i("TAG","============"+specialBean.getData().size());
                        if(specialBean!=null){
                            callBack.onSuccess(specialBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
