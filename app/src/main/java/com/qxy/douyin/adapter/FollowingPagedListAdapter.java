package com.qxy.douyin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.douyin.R;
import com.qxy.douyin.databinding.FragmentFansListBinding;
import com.qxy.douyin.databinding.FragmentFollowingListBinding;
import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.Following;

public class FollowingPagedListAdapter extends PagedListAdapter<Following.DataBean.ListBean,FollowingPagedListAdapter.FollowingViewHolder> {

    private Context context;

    public FollowingPagedListAdapter(Context context)
    {
        super(DIFF_CALLBACK);
        this.context=context;
    }
    private  static DiffUtil.ItemCallback<Following.DataBean.ListBean> DIFF_CALLBACK=new DiffUtil.ItemCallback<Following.DataBean.ListBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull Following.DataBean.ListBean oldItem, @NonNull Following.DataBean.ListBean newItem) {
            return oldItem.getOpen_id()==newItem.getOpen_id();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Following.DataBean.ListBean oldItem, @NonNull Following.DataBean.ListBean newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public FollowingPagedListAdapter.FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentFollowingListBinding fragmentFollowingListBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_following_list,parent,false);

        return new FollowingPagedListAdapter.FollowingViewHolder(fragmentFollowingListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingPagedListAdapter.FollowingViewHolder holder, int position) {
        Following.DataBean.ListBean listBean=getItem(position);
        if(listBean!=null)
            holder.fragmentFollowingListBinding.setFollowing(listBean);


    }
    class FollowingViewHolder extends RecyclerView.ViewHolder
    {
        FragmentFollowingListBinding fragmentFollowingListBinding;
        public FollowingViewHolder(@NonNull FragmentFollowingListBinding itemView) {

            super(itemView.getRoot());
            fragmentFollowingListBinding=itemView;
        }

    }


}
