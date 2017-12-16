package com.bawei.chosexx.chen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 陈令鸽 on 2017/12/16.
 */

public class CollectMyHelper extends SQLiteOpenHelper {


    public CollectMyHelper(Context context) {
        super(context, "mycollect", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table myCollectUser(id integer primary key autoincrement,name varchar(50),url integer,urls varchar(5))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
