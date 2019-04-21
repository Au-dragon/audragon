package com.example.geek.model;

import android.util.Log;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.ZhiHuBean;
import com.example.geek.net.ZhiHuCallBack;
import com.example.geek.net.ZhiHuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhiHuModel extends BaseModel {
    public void getDatas(final ZhiHuCallBack callBack){
        Retrofit build = new Retrofit.Builder().baseUrl(ZhiHuService.zhiHuUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhiHuService zhiHuService = build.create(ZhiHuService.class);
        Observable<ZhiHuBean> datas = zhiHuService.getDatas();
        datas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ZhiHuBean zhiHuBean) {
                        Log.i("TAG","============"+zhiHuBean.getStories().size());
                            callBack.onSuccess(zhiHuBean);
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
