package com.qxy.douyin.ui.fans;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.douyin.R;
import com.qxy.douyin.adapter.FansPagedListAdapter;
import com.qxy.douyin.adapter.FollowingPagedListAdapter;
import com.qxy.douyin.databinding.FragmentFansListBinding;
import com.qxy.douyin.databinding.FragmentFansListListBinding;
import com.qxy.douyin.databinding.FragmentMyBinding;
import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.Following;
import com.qxy.douyin.ui.fans.placeholder.PlaceholderContent;
import com.qxy.douyin.ui.my.MyViewModel;

/**
 * A fragment representing a list of Items.
 */
public class FansListFragment extends Fragment {
    private FragmentFansListListBinding binding;
    private  View root;
    private RecyclerView recyclerView;
    private boolean isfans=false;
    String TAG="FansListFragment";
    public FansListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FansListFragment newInstance(int columnCount) {
        FansListFragment fragment = new FansListFragment();
        Bundle args = new Bundle();
        args.putInt("isfans", columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getArguments().getInt("isfans")==1)isfans=true;
        FansViewModel fansViewModel=new ViewModelProvider(this).get(FansViewModel.class);
        binding= FragmentFansListListBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        recyclerView=binding.list;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        if(isfans) {
            final FansPagedListAdapter fansPagedListAdapter = new FansPagedListAdapter(getContext());
            fansViewModel.getFansPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Fans.DataBean.ListBean>>() {
                @Override
                public void onChanged(PagedList<Fans.DataBean.ListBean> listBeans) {
                    fansPagedListAdapter.submitList(listBeans);
                    Log.d(TAG, listBeans.toString());
                }

            });
            recyclerView.setAdapter(fansPagedListAdapter);
        }
        else
        {
            final FollowingPagedListAdapter followingPagedListAdapter = new FollowingPagedListAdapter(getContext());
            fansViewModel.getFollowingPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Following.DataBean.ListBean>>() {
                @Override
                public void onChanged(PagedList<Following.DataBean.ListBean> listBeans) {
                   followingPagedListAdapter.submitList(listBeans);
                    Log.d(TAG, listBeans.toString());
                }

            });
            recyclerView.setAdapter(followingPagedListAdapter);
        }
        return root;
    }
}