package com.qxy.douyin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.douyin.R;
import com.qxy.douyin.databinding.FragmentFansListBinding;
import com.qxy.douyin.databinding.FragmentFansListListBinding;
import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.Following;

import java.util.List;

public class FansPagedListAdapter extends PagedListAdapter<Fans.DataBean.ListBean,FansPagedListAdapter.FansViewHolder> {

   private Context context;
   public FansPagedListAdapter(Context context)
    {
        super(DIFF_CALLBACK);
        this.context=context;
    }
    private  static DiffUtil.ItemCallback<Fans.DataBean.ListBean> DIFF_CALLBACK=new DiffUtil.ItemCallback<Fans.DataBean.ListBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull Fans.DataBean.ListBean oldItem, @NonNull Fans.DataBean.ListBean newItem) {
            return oldItem.getOpen_id()==newItem.getOpen_id();
        }
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Fans.DataBean.ListBean oldItem, @NonNull Fans.DataBean.ListBean newItem) {
            return oldItem.equals(newItem);
        }
    };
    @NonNull
    @Override
    public FansPagedListAdapter.FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentFansListBinding fragmentFansListBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_fans_list,parent,false);
        return new FansViewHolder(fragmentFansListBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull FansPagedListAdapter.FansViewHolder holder, int position) {
        Fans.DataBean.ListBean listBean=getItem(position);
        if(listBean!=null)
        holder.fragmentFansListBinding.setFans(listBean);
    }
    class FansViewHolder extends RecyclerView.ViewHolder
    {
        FragmentFansListBinding fragmentFansListBinding;
        public FansViewHolder(@NonNull FragmentFansListBinding itemView) {

            super(itemView.getRoot());
            fragmentFansListBinding=itemView;
        }
    }
}
