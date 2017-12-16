package com.bawei.chosexx.chen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.bawei.chosexx.chen.bean.MyHistoryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 陈令鸽 on 2017/12/15.
 */

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MyHistoryBean> list;
    private OnitemCliecklineasn mItemClickListener;

    public CollectAdapter(Context context, List<MyHistoryBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.collect_item, parent, false);
        return new CollectAdapter.ViewHolder(view, mItemClickListener);
    }

    public void setOnItemClickListener(OnitemCliecklineasn listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CollectAdapter.ViewHolder hoder = (CollectAdapter.ViewHolder) holder;
        hoder.tv1.setText(list.get(position).getName());
        Picasso.with(context).load(list.get(position).getUrl()).into(hoder.image);
    }

    @Override
    public  int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnitemCliecklineasn mListener;
        public TextView tv1;
        public ImageView image;

        ViewHolder(View itemView, OnitemCliecklineasn lineasn) {
            super(itemView);
            image =  itemView.findViewById(R.id.collect_image);
            tv1 = itemView.findViewById(R.id.collect_tv);
            this.mListener = lineasn;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }
}