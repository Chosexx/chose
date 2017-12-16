package com.bawei.chosexx.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bawei.chosexx.Find.Adapter.FindAdapter;
import com.bawei.chosexx.Find.Bean.FindBean;
import com.bawei.chosexx.Find.Fragment.Main2;
import com.bawei.chosexx.Find.Presenter.Presenter;
import com.bawei.chosexx.Find.View.IView;
import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.bawei.chosexx.chen.db.CollectDao;
import com.bawei.chosexx.chen.db.Dao;

import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

/**
 * Created by 111 on 2017/12/13.
 */

public class FaFragment extends Fragment  implements IView {
    private RecyclerView recyc;
    private FindAdapter adapter;
    private List<FindBean.RetBean.ListBean> list;
    private Button btn;
    private int id=2;
    private Presenter presenter;
    //http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.activity_fa,container,false );
        recyc = view.findViewById(R.id.faxian_recyc);
        btn = view.findViewById(R.id.find_btn);
        presenter = new Presenter(this);
        presenter.getdata("columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum="+1+"");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                adapter.notifyDataSetChanged();
                ++id;
                presenter.getdata("columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum="+id+"");
            }
        });
        isFristRun();
        return view;
    }
        private boolean isFristRun() {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
            boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (!isFirstRun) {
                return false;
            } else {
                editor.putBoolean("isFirstRun", false);
                editor.commit();
                Log.i("aaaaaaa","diyici");
                CollectDao dao=new CollectDao(getActivity());
                Dao d=new Dao(getActivity());
                d.add("sss","sss","sss");
                dao.addCollect("sss","sss","sss");
                return true;
            }
        }
    @Override
    public void data(FindBean bean) {
        list = bean.getRet().getList();
        aa();
    }
    public void aa(){
        recyc.setItemAnimator(new DefaultItemAnimator());
        adapter = new FindAdapter(getActivity(),list);
        recyc.setAdapter(adapter);
        final CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(adapter, list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<Object>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                FindAdapter.ViewHolder myHolder = (FindAdapter.ViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                } else {
                }
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                FindAdapter.ViewHolder myHolder = (FindAdapter.ViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
            }
            @Override
            public void onSwipedClear() {
                recyc.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                }, 3000L);
            }
        });
        ItemTouchHelper touchHelper=new ItemTouchHelper(cardCallback);
        CardLayoutManager cardLayoutManager =new CardLayoutManager(recyc,touchHelper);
        recyc.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyc);
        adapter.setOnItemClickListener(new OnitemCliecklineasn() {
            @Override
            public void onItemClick(View view, int postion) {
                // Toast.makeText(getActivity(),"未开发"+postion,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), Main2.class);
                intent.putExtra("airtime",""+list.get(postion).getAirTime());
                intent.putExtra("duration",""+list.get(postion).getAngleIcon());
                intent.putExtra("loadtype",""+list.get(postion).getLoadtype());
                intent.putExtra("dataid",""+list.get(postion).getDataId());
                intent.putExtra("description",""+list.get(postion).getDescription());
                intent.putExtra("loadurl",""+list.get(postion).getLoadURL());
                intent.putExtra("shareurl",""+list.get(postion).getShareURL());
                intent.putExtra("pic",""+list.get(postion).getPic());
                intent.putExtra("title",""+list.get(postion).getTitle());
                startActivity(intent);
            }
        });
    }
}
