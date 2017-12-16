package com.bawei.chosexx.Find.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bawei.chosexx.R;
import com.bawei.chosexx.chen.bean.MyCollectBean;
import com.bawei.chosexx.chen.db.CollectDao;
import com.bawei.chosexx.chen.db.Dao;
import com.squareup.picasso.Picasso;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 王帅彪 on 2017/12/14.
 */
public class Main2 extends AppCompatActivity {
    private String[] title = {
            "简介", "评论"
    };
    private ViewPager pager;
    private String description;
    private String dataid;
    private String pic;
    private String loadurl;
    private String shareurl;
    private String title1;
   //收藏
    private List<MyCollectBean> list;
    private ImageView image_shoucang;
    private LinearLayout lin_shoucang;
    private CollectDao dao;
    private JCVideoPlayerStandard player;
    private Dao d;
    private SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lin_shoucang = findViewById(R.id.linear_shoucang);
        image_shoucang = findViewById(R.id.iamge_shoucang);
        aa();
        inview();
        shoucang();
        d = new Dao(this);
        d.add(title1, pic, loadurl);
    }
    public void shoucang(){
        dao = new CollectDao(this);
        list = dao.queryCollect();
         for (int i = 0; i < this.list.size(); i++) {
            String name = this.list.get(i).getName();
            Log.i("收藏ssssssssss","ss"+name);
            if (name.equals(title1)) {
                image_shoucang.setImageResource(R.mipmap.collection_select);
            }else{
                image_shoucang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        image_shoucang.setImageResource(R.mipmap.collection_select);
                        dao.addCollect(title1, pic, loadurl);
                    }
                });
            }
        }
    }
    public void inview() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        for (String str : title) {
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new Myadapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
    }
    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment_jianjie.newInstance(title[position], description, dataid, pic, loadurl, shareurl, title1);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
    public void aa() {
        Intent intent = getIntent();
        String airtime = intent.getStringExtra("airtime");
        String duration = intent.getStringExtra("duration");
        String loadtype = intent.getStringExtra("loadtype");
        dataid = intent.getStringExtra("dataid");
        description = intent.getStringExtra("description");
        loadurl = intent.getStringExtra("loadurl");
        shareurl = intent.getStringExtra("shareurl");
        pic = intent.getStringExtra("pic");
        title1 = intent.getStringExtra("title");
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(title1);
        if (shareurl != null) {
            player = (JCVideoPlayerStandard) findViewById(R.id.video);
            boolean setUp = player.setUp(shareurl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            if (setUp) {
                Picasso.with(this).load(pic).into(player.thumbImageView);
            }
        } else {
            Toast.makeText(this, "已下架", Toast.LENGTH_SHORT).show();
        }



    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
