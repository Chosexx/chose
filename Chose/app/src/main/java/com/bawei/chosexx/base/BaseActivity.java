package com.bawei.chosexx.base;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 111 on 2017/12/13.
 */

public class BaseActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        setTranslucentStatus(true);

       if (enableSliding()) {
           // SlidingLayout rootView = new SlidingLayout(this);
            //rootView.bindActivity(this);
        }

    }
    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    protected boolean enableSliding() {
        return true;
    }
}
