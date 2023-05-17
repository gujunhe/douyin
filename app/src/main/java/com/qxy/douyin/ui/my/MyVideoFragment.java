package com.qxy.douyin.ui.my;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.R;
import com.qxy.douyin.databinding.FragmentMyVideoListBinding;
import com.qxy.douyin.model.VideoList;
import com.qxy.douyin.ui.my.placeholder.PlaceholderContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class MyVideoFragment extends Fragment {
    private FragmentMyVideoListBinding binding;
    private  RecyclerView recyclerView;
    private List<VideoList.DataBean.ListBean> videoList;

    private  View root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMyVideoListBinding.inflate(inflater,container,false);
        root=binding.getRoot();
        recyclerView=binding.list;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setNestedScrollingEnabled(false);
        MyApplication.open_id.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=""&&s!=null)
                {
                    initData(s);
                }
            }
        });
        return root;
    }

    private  void  initData(String openid)
    {
        MyApplication.getApiService().getVedioList("application/json",MyApplication.accesstoken,openid,0,10).enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                Log.d("videoList1",response.body().toString());
                if(response.body().getData().getList()!=null) {
                    videoList = response.body().getData().getList();
                    recyclerView.setAdapter(new MyItemRecyclerViewAdapter(videoList,getContext()));

                }

            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {
                Log.d("videoList2",t.toString());
            }
        });
    }
}