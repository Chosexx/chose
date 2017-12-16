package com.bawei.chosexx.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawei.chosexx.R;
import com.bawei.chosexx.chen.MyCollect;
import com.bawei.chosexx.chen.MyHistory;
import com.bawei.chosexx.chen.MySetting;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 111 on 2017/12/13.
 */

public class MineFragment extends Fragment {
    @BindView(R.id.setimageicon)
    ImageButton setimageicon;
    @BindView(R.id.l1)
    RelativeLayout l1;
    @BindView(R.id.l2)
    RelativeLayout l2;
    @BindView(R.id.l3)
    RelativeLayout l3;
    @BindView(R.id.l4)
    RelativeLayout l4;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.l1)
    void OneOnclick() {

        startActivity(new Intent(getActivity(), MyHistory.class));
    }

    @OnClick(R.id.l2)
    void TwoOnclick() {

        Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.l3)
    void ThrOnclick() {

        startActivity(new Intent(getActivity(), MyCollect.class));

    }

    ///切换主题模式
    @OnClick(R.id.l4)
    void FourOnclick() {

        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        //设置主题
        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        getActivity().recreate();

      /*  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择主题");
        builder.setNegativeButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                //设置主题
                if (mode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                getActivity().recreate();
            }

        });

        builder.setPositiveButton("取消", null);
        builder.show();*/

    }


    @OnClick(R.id.setimageicon)
    void ImgIconOnClick() {
        startActivity(new Intent(getActivity(), MySetting.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
