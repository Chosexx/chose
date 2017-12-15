package com.bawei.chosexx.Find.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chosexx.Find.Bean.FindBean;
import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王帅彪 on 2017/12/13.
 */
public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FindBean.RetBean.ListBean> list;
    private OnitemCliecklineasn mItemClickListener;
    public FindAdapter(Context context,List<FindBean.RetBean.ListBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.find_item, parent, false);
        return new ViewHolder(view,mItemClickListener);
    }
    public void setOnItemClickListener(OnitemCliecklineasn listener) {
        this.mItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder hoder= (ViewHolder) holder;
        hoder.tv.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getPic()).into(hoder.image);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnitemCliecklineasn mListener;
       public TextView tv;
       public ImageView image;
       ViewHolder(View itemView,OnitemCliecklineasn lineasn) {
           super(itemView);
           image =  itemView.findViewById(R.id.find_item_image);
           tv =  itemView.findViewById(R.id.find_item_tv);
           this.mListener=lineasn;
           itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }
}