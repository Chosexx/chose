package com.bawei.chosexx.chen.bean;

/**
 * Created by 陈令鸽 on 2017/12/14.
 */

public class MyBean {

    String name;
    String url;
    String urls;

    public MyBean(String name, String url, String urls) {
        this.name = name;
        this.url = url;
        this.urls = urls;
    }

    public MyBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
