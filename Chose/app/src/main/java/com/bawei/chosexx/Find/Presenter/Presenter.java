package com.bawei.chosexx.Find.Presenter;

import com.bawei.chosexx.Find.Bean.FindBean;
import com.bawei.chosexx.Find.Moudel.IMoudel;
import com.bawei.chosexx.Find.Moudel.Moudel;
import com.bawei.chosexx.Find.View.IView;

/**
 * Created by 王帅彪 on 2017/12/13.
 */

public class Presenter {
    private IView iView;
    private Moudel moudel;
    public Presenter(IView iView){
        this.iView=iView;
        this.moudel=new Moudel();
    }
    public void getdata(String s){
        moudel.data(s, new IMoudel() {
            @Override
            public void data(FindBean bean) {
                iView.data(bean);
            }
        });
    }
}
