package com.example.geek.net;

import com.example.geek.bean.BeforeBean;
import com.example.geek.bean.HotBean;
import com.example.geek.bean.SpecialBean;
import com.example.geek.bean.ZhiHuBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

//欧俊龙 1808D 4/17
public interface ZhiHuService {
    String zhiHuUrl="http://news-at.zhihu.com/api/4/";
    @GET("news/latest")
    Observable<ZhiHuBean> getDatas();

    @GET("sections")
    Observable<SpecialBean> getDatas2();
@GET("news/hot")
    Observable<HotBean> getDatas3();

@GET("")
    Observable<BeforeBean> getDatas4(@Url String url);

}
