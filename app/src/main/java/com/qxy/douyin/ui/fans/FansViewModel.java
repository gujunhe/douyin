package com.qxy.douyin.ui.fans;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.Following;
import com.qxy.douyin.ui.fans.placeholder.FollowingDataSourceFacory;

public class FansViewModel extends ViewModel {
    public LiveData<PagedList<Fans.DataBean.ListBean>> fansPagedList;
    public LiveData<PagedList<Following.DataBean.ListBean>> followingPagedList;
    private PagedList.Config config;
    public FansViewModel()
    {
         config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(FansDataSource.PER_PAGE)
                .setPrefetchDistance(40)
                .setMaxSize(1000*FansDataSource.PER_PAGE)
                .build();


    }
    public LiveData<PagedList<Fans.DataBean.ListBean>> getFansPagedList()
    {
        fansPagedList=(new LivePagedListBuilder<>(new FansDataSourceFactory(),config)).build();
        return  fansPagedList;
    }
    public LiveData<PagedList<Following.DataBean.ListBean>> getFollowingPagedList()
    {
        followingPagedList=(new LivePagedListBuilder<>(new FollowingDataSourceFacory(),config)).build();
        return  followingPagedList;
    }
}
