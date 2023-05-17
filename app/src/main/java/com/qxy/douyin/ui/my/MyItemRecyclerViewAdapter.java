package com.qxy.douyin.ui.my;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qxy.douyin.databinding.FragmentMyVideoBinding;
import com.qxy.douyin.model.VideoList;
import com.qxy.douyin.ui.my.placeholder.PlaceholderContent.PlaceholderItem;
import com.qxy.douyin.ui.videodetail.VideoDetailActivity;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private List<VideoList.DataBean.ListBean> list;
    private Context context;
    public MyItemRecyclerViewAdapter(List<VideoList.DataBean.ListBean> list, Context context) {
        this.list=list;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMyVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VideoList.DataBean.ListBean video=list.get(position);
        holder.fragmentMyVideoBinding.setVideo(video);
        holder.fragmentMyVideoBinding.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, VideoDetailActivity.class);
                intent.putExtra("url",list.get(position).getShare_url());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FragmentMyVideoBinding fragmentMyVideoBinding;



        public ViewHolder(FragmentMyVideoBinding binding) {
            super(binding.getRoot());
            this.fragmentMyVideoBinding=binding;

        }

    }
}