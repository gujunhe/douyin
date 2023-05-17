package com.qxy.douyin.ui.rank;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qxy.douyin.R;
import com.qxy.douyin.databinding.FragmentFansListBinding;
import com.qxy.douyin.databinding.FragmentRankBinding;
import com.qxy.douyin.databinding.FragmentRankListBinding;
import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.ui.rank.placeholder.PlaceholderContent.PlaceholderItem;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<RankItem.DataBean.ListBean> list;

    public MyItemRecyclerViewAdapter(List<RankItem.DataBean.ListBean> items) {
        this.list = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentRankBinding fragmentRankBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_rank,parent,false);
        return new ViewHolder(fragmentRankBinding);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(list!=null)
        holder.fragmentRankBinding.setRankitem(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  FragmentRankBinding fragmentRankBinding;


        public ViewHolder(@NonNull FragmentRankBinding itemView) {

            super(itemView.getRoot());
            fragmentRankBinding=itemView;
        }


    }
}