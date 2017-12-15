package com.bawei.chosexx.Find.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chosexx.Find.Adapter.Find2_Adapter;
import com.bawei.chosexx.Find.Adapter.Pinglun_Adapter;
import com.bawei.chosexx.Find.Bean.Find2_Bean;
import com.bawei.chosexx.Find.Bean.PingLun_Bean;
import com.bawei.chosexx.Find.Untils.ApiServer;
import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.bawei.chosexx.chen.db.Dao;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 王帅彪 on 2017/12/14.
 */

public class Fragment_jianjie extends Fragment {
    private static final String PAGE_TITLE = "PAGE_TITLE";
    private String title;
    private View v;
    private static String jiejian1;
    private static String dataId1;
    private List<Find2_Bean.RetBean.ListBean.ChildListBean> list;
    private List<PingLun_Bean.RetBean.ListBean> list1;
    private static String pic1;
    private static String loadurl1;
    private static String shareurl1;
    private static String name1;

    public static Fragment_jianjie newInstance(String title, String jiejian, String dataId,String pic,String loadurl,String shareurl,String name){
        Bundle bundle = new Bundle();
        bundle.putString(PAGE_TITLE,title);
        jiejian1 = jiejian;
        dataId1 = dataId;
        pic1 = pic;
        loadurl1 = loadurl;
        shareurl1 = shareurl;
        name1 = name;
        Fragment_jianjie fragment1 = new Fragment_jianjie();
        fragment1.setArguments(bundle);
        return fragment1;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(PAGE_TITLE);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        if (title.equals("简介")){
            v = inflater.inflate(R.layout.fragment_jianjie,null);
            TextView tv=v.findViewById(R.id.jianjie_tv);
            if (jiejian1.length()>50){
                String substring = jiejian1.substring(0, 50);
                tv.setText("简介:  "+substring+"...");
            }else{
                tv.setText("简介:  "+jiejian1);
            }
            jianjiedata();
        }else if(title.equals("评论")){
            v=inflater.inflate(R.layout.fragment_pinglun,null);
            pinglundata();
        }else{
            v=inflater.inflate(R.layout.find_shoucang,null);
            final ImageView imageView=v.findViewById(R.id.find_image_shoucang);
            SharedPreferences sp=getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
            boolean first = sp.getBoolean("first", false);
            if (first==true){
                imageView.setImageResource(R.mipmap.collection_select);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sp=getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                    sp.edit().putBoolean("first" ,true).commit();
                    imageView.setImageResource(R.mipmap.collection);
                    Dao dao=new Dao(getActivity());
                    dao.add(name1,pic1,loadurl1);
                }
            });
        }

        return v;
    }
     //评论...
     public void pinglundata(){
         //http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId=67c742837cf443638528066979732dfb
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://api.svipmovie.com/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         ApiServer service = retrofit.create(ApiServer.class);
         Call<ResponseBody> repos = service.listRepos("front/Commentary/getCommentList.do?mediaId="+dataId1+"");
         repos.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 Log.e("APP",response.body().source().toString());
                 try {
                     String string = response.body().string();
                     Gson gson=new Gson();
                     PingLun_Bean bean = gson.fromJson(string, PingLun_Bean.class);
                     list1 = bean.getRet().getList();
                     RecyclerView recyclerView=v.findViewById(R.id.pinglun_recyc);
                     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                     recyclerView.setLayoutManager(linearLayoutManager);
                     //设置适配器
                     Pinglun_Adapter mdapter = new Pinglun_Adapter(getActivity(),list1);
                     recyclerView.setAdapter(mdapter);
                     mdapter.setOnItemClickListener(new OnitemCliecklineasn() {
                         @Override
                         public void onItemClick(View view, int postion) {
                             Toast.makeText(getActivity(),"点击测试成功",Toast.LENGTH_SHORT).show();
                         }
                     });
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 //Toast.makeText(getActivity(),"m",Toast.LENGTH_SHORT).show();
             }
             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 t.printStackTrace();
             }
         });
     }
    //简介
    public void jianjiedata(){
        //http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=03e82ad8963d4bf6b393ed3cd2c7d6cd
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.svipmovie.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer service = retrofit.create(ApiServer.class);
        Call<ResponseBody> repos = service.listRepos("front/videoDetailApi/videoDetail.do?mediaId="+dataId1+"");
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("APP",response.body().source().toString());
                try {
                    String string = response.body().string();
                    Gson gson=new Gson();
                    Find2_Bean bean = gson.fromJson(string, Find2_Bean.class);

                    if (bean.getRet()!=null&&bean.getRet().getList()!=null){
                        list = bean.getRet().getList().get(0).getChildList();
                        RecyclerView recyclerView=v.findViewById(R.id.jianjie_recyc);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        //设置适配器
                        Find2_Adapter mAdapter = new Find2_Adapter(getActivity(), list);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new OnitemCliecklineasn() {
                            @Override
                            public void onItemClick(View view, int postion) {
                                Toast.makeText(getActivity(),"点击测试成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}