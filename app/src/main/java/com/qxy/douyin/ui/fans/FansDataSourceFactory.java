package com.qxy.douyin.ui.fans;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.qxy.douyin.model.Fans;

public class FansDataSourceFactory extends DataSource.Factory<Integer, Fans.DataBean.ListBean> {
    private MutableLiveData<FansDataSource>fansDataSourceMutableLiveData=new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource<Integer, Fans.DataBean.ListBean> create() {
        FansDataSource dataSource=new FansDataSource();
        fansDataSourceMutableLiveData.postValue(dataSource);

        return dataSource;
    }
}
