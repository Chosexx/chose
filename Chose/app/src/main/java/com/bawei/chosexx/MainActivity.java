package com.bawei.chosexx;


import android.graphics.Color;
import android.os.Bundle;

import com.bawei.chosexx.base.BaseActivity;
import com.bawei.chosexx.fragment.FaFragment;
import com.bawei.chosexx.fragment.JingFragment;
import com.bawei.chosexx.fragment.MineFragment;
import com.bawei.chosexx.fragment.ZhuanFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );
        initView();
    }

    private void initView() {
        bottomTabBar.init( getSupportFragmentManager())
                .setImgSize( 66, 66 )
                .setTabPadding( 4, 4, 4 )
                .setFontSize( 20 )
                .setChangeColor( Color.parseColor( "#FF0000" ), Color.GRAY )
                .addTabItem( "精选", R.mipmap.found_select, R.mipmap.found, JingFragment.class )
                .addTabItem( "专题", R.mipmap.special_select, R.mipmap.special, ZhuanFragment.class )
                .addTabItem( "发现", R.mipmap.fancy_select, R.mipmap.fancy, FaFragment.class )
                .addTabItem( "我的", R.mipmap.my_select, R.mipmap.my, MineFragment.class )
                .isShowDivider( false )
                .setTabBarBackgroundColor( Color.WHITE );

    }



}
