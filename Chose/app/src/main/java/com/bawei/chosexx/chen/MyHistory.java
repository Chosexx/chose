package com.bawei.chosexx.chen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.bawei.chosexx.base.BaseActivity;
import com.bawei.chosexx.chen.adapter.CollectAdapter;
import com.bawei.chosexx.chen.bean.MyHistoryBean;
import com.bawei.chosexx.chen.db.Dao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyHistory extends BaseActivity {

    @BindView(R.id.collect_recycler)
    RecyclerView collectRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycollect);
        ButterKnife.bind(this);

        Dao dao = new Dao(this);
        List<MyHistoryBean> beans = dao.queryAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        collectRecycler.setLayoutManager(linearLayoutManager);
        //设置适配器
        CollectAdapter mdapter = new CollectAdapter(this,beans);
        collectRecycler.setAdapter(mdapter);
        mdapter.setOnItemClickListener(new OnitemCliecklineasn() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(MyHistory.this,"点击测试成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
