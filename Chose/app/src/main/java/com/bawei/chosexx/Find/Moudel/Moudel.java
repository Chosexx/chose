package com.bawei.chosexx.Find.Moudel;


import com.bawei.chosexx.Find.Bean.FindBean;
import com.bawei.chosexx.Find.Untils.Api;
import com.bawei.chosexx.Find.Untils.ApiServer;
import com.bawei.chosexx.Find.Untils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王帅彪 on 2017/12/13.
 */

public class Moudel {

    public void data(String s, final IMoudel iMoudel){
        ApiServer server = RetrofitUtils.newInstance().create(ApiServer.class, Api.path);
        Observable<FindBean> getdata = server.getdata(s);
        getdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FindBean>() {
                    @Override
                    public void onNext(FindBean bean) {
                        iMoudel.data(bean);
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
