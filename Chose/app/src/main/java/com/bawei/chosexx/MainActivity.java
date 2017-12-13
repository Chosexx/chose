package com.bawei.chosexx;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.chosexx.fragment.FaFragment;
import com.bawei.chosexx.fragment.JingFragment;
import com.bawei.chosexx.fragment.MineFragment;
import com.bawei.chosexx.fragment.ZhuanFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fram)
    FrameLayout fram;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rg)

    RadioGroup rg;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        butterknife.ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        //获取Fragment管理者
        manager = getSupportFragmentManager();
        //默认加载第一个页面
        manager.beginTransaction().replace(R.id.fram, new JingFragment()).commit();


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        getFragment(new JingFragment());
                        break;
                    case R.id.rb2:
                        getFragment(new ZhuanFragment());
                        break;
                    case R.id.rb3:
                        getFragment(new FaFragment());
                        break;
                    case R.id.rb4:
                        getFragment(new MineFragment());
                        break;
                }
            }
        });

    }

    /**
     * 加载Fragment
     */

    private void getFragment(Fragment fragment) {
        manager.beginTransaction().replace(R.id.fram, fragment).commit();
    }
}
