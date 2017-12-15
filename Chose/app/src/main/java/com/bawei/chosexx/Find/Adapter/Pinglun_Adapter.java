package com.bawei.chosexx.Find.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.chosexx.Find.Bean.PingLun_Bean;
import com.bawei.chosexx.Find.Untils.CircleImageView;
import com.bawei.chosexx.Find.utils.OnitemCliecklineasn;
import com.bawei.chosexx.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王帅彪 on 2017/12/14.
 */
public class Pinglun_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PingLun_Bean.RetBean.ListBean> list;
    private OnitemCliecklineasn mItemClickListener;
    public Pinglun_Adapter(Context context,List<PingLun_Bean.RetBean.ListBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pinglun_item, parent, false);
        return new ViewHolder(view,mItemClickListener);
    }
    public void setOnItemClickListener(OnitemCliecklineasn listener) {
        this.mItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder hoder= (ViewHolder) holder;
        try {
            if (list.get(position).getPhoneNumber()!=null){hoder.tv1.setText(list.get(position).getPhoneNumber());}else{hoder.tv1.setText("匿名用户");}
            if (list.get(position).getTime()!=null){hoder.tv2.setText(list.get(position).getTime());}else{hoder.tv2.setText("未知时间");}
            if (list.get(position).getMsg()!=null){hoder.tv3.setText(list.get(position).getMsg());}else{hoder.tv3.setText("没有评论");}
            if (list.get(position).getUserPic()!=null){
                Picasso.with(context).load(list.get(position).getUserPic()).into(hoder.image);
            }else{hoder.image.setImageResource(R.mipmap.ic_launch);}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnitemCliecklineasn mListener;
        public TextView tv1,tv2,tv3;
        public CircleImageView image;
        ViewHolder(View itemView,OnitemCliecklineasn lineasn) {
            super(itemView);
            image =  itemView.findViewById(R.id.pinglun_image);
            tv1 =  itemView.findViewById(R.id.pinglun_name);
            tv2 =  itemView.findViewById(R.id.pinglun_time);
            tv3 =  itemView.findViewById(R.id.pinglun_title);
            this.mListener=lineasn;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }
}