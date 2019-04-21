package com.example.geek.model;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.HotBean;
import com.example.geek.net.HotCallBack;
import com.example.geek.net.ZhiHuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotModel extends BaseModel {
    public void getDatas(final HotCallBack callBack){
        Retrofit build = new Retrofit.Builder().baseUrl(ZhiHuService.zhiHuUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhiHuService zhiHuService = build.create(ZhiHuService.class);
        Observable<HotBean> datas3 = zhiHuService.getDatas3();
        datas3.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                            if(hotBean!=null){
                                callBack.onSuccess(hotBean);
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
