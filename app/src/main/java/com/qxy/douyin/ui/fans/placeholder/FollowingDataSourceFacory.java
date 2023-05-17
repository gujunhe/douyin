package com.qxy.douyin.ui.fans.placeholder;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.Following;
import com.qxy.douyin.ui.fans.FansDataSource;
import com.qxy.douyin.ui.fans.FollowingDataSource;

public class FollowingDataSourceFacory extends DataSource.Factory<Integer, Following.DataBean.ListBean> {
    private MutableLiveData<FollowingDataSource>followingDataSourceMutableLiveData=new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource<Integer, Following.DataBean.ListBean> create() {
        FollowingDataSource dataSource=new FollowingDataSource();
        followingDataSourceMutableLiveData.postValue(dataSource);

        return dataSource;
    }
}
