package com.bawei.chosexx.Find.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chosexx.Find.Bean.Find2_Bean;
import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王帅彪 on 2017/12/14.
 */


public class Find2_Adapter extends RecyclerView.Adapter<Find2_Adapter.ViewHolder> {

    private Context context;
    private List<Find2_Bean.RetBean.ListBean.ChildListBean> list;
    private OnitemCliecklineasn mItemClickListener;
    public Find2_Adapter(Context context,List<Find2_Bean.RetBean.ListBean.ChildListBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jianjie_item, parent, false);
        return new Find2_Adapter.ViewHolder(view,mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Find2_Adapter.ViewHolder hoder= (Find2_Adapter.ViewHolder) holder;
        String s = list.get(position).getTitle().toString();
        if (s.length()>5){
            String substring = s.substring(0, 5);
            hoder.tv.setText(substring+"..");
        }else {
            hoder.tv.setText(s);
        }

        Picasso.with(context).load(list.get(position).getPic()).into(hoder.image);
    }

    public void setOnItemClickListener(OnitemCliecklineasn listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnitemCliecklineasn mListener;
        public TextView tv;
        public ImageView image;
        ViewHolder(View itemView,OnitemCliecklineasn lineasn) {
            super(itemView);
            image =  itemView.findViewById(R.id.jianjie_item_image);
            tv =  itemView.findViewById(R.id.jianjie_item_tv);
            this.mListener=lineasn;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }
}