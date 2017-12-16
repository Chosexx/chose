package com.bawei.chosexx.chen.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bawei.chosexx.chen.bean.MyHistoryBean;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context) {
        MyHelper helper = new MyHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(String name, String url, String urls) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("url", url);
        values.put("urls", urls);
        db.insert("Myuser", null, values);
    //    db.close();
    }

    public List<MyHistoryBean> queryAll() {

        List<MyHistoryBean> list = new ArrayList<MyHistoryBean>();
        Cursor cursor = db.rawQuery("select *from Myuser", null);
        while (cursor.moveToNext()) {
            MyHistoryBean bean = new MyHistoryBean();
            bean.setName(cursor.getString(cursor.getColumnIndex("name")));
            bean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            bean.setUrls(cursor.getString(cursor.getColumnIndex("urls")));
            list.add(bean);
        }
     //   cursor.close();
    //    db.close();
        return list;
    }

    public void delete() {
        db.delete("Myuser", null, null);
    }
}
