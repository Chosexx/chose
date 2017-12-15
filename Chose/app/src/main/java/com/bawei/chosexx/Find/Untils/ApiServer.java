package com.bawei.chosexx.Find.Untils;


import com.bawei.chosexx.Find.Bean.FindBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 王帅彪 on 2017/12/13.
 */

public interface ApiServer {
    //http://api.svipmovie.com/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=0
    @GET
    Observable<FindBean> getdata(@Url String s);
    @GET
    Call<ResponseBody> listRepos(@Url String s);
}
