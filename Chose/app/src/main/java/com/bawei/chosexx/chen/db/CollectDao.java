package com.bawei.chosexx.chen.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.chosexx.chen.bean.MyCollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈令鸽 on 2017/12/16.
 */

public class CollectDao {

    private SQLiteDatabase db;

    public CollectDao(Context context) {

        CollectMyHelper myHelper = new CollectMyHelper(context);
        db = myHelper.getWritableDatabase();
    }

    private void addCollect(String name, String url, String urls) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("url", url);
        values.put("urls", urls);
        db.insert("myCollectUser", null, values);
    }

    private List<MyCollectBean> queryCollect() {

        //创建集合
        List<MyCollectBean> collectBeans = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from myCollectUser", null);
        while (cursor.moveToNext()) {
            MyCollectBean collectBean = new MyCollectBean();
            collectBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            collectBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            collectBean.setUrls(cursor.getString(cursor.getColumnIndex("urls")));
            //添加到集合
            collectBeans.add(collectBean);
        }
        return collectBeans;
    }
}
