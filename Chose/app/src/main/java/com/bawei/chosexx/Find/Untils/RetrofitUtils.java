package com.bawei.chosexx.Find.Untils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 1. 类的用途
 * 2. @author chensi
 * 3. @date 2017/12/3 19:20
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils=null;
    private OkHttpClient okHttpClient;

    public static RetrofitUtils newInstance(){
        if(retrofitUtils==null){
            synchronized(RetrofitUtils.class){
                if(retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    //构造方法 添加okhttpclient
    public RetrofitUtils(){
        //缓存目录
        File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
        int cacheSize = 10 * 1024 * 1024;
        //OkHttp3拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx", message.toString());
            }
        });
        //Okhttp3的拦截器日志分类 4种
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                //添加OkHttp3的拦截器
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                .build();

    }
    //创建retrofitservice类
    public ApiServer create(Class<ApiServer> retrofitService, String baseurl){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseurl)
                .client(okHttpClient)
                .build();
        ApiServer service = retrofit.create(retrofitService);
        return service;
    }

}
