package com.qxy.douyin.ui.my;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.db.UserDatabase;
import com.qxy.douyin.db.UserInfoDao;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.model.VideoList;
import com.qxy.douyin.network.ApiService;

import java.util.List;

public class MyViewModel extends ViewModel {

    private  String TAG =this.getClass().getName();
    private LiveData<UserInfo.DataBean> userinfo;
    private  MyRepository myRepository;


    private ApiService apiService;

    public MyViewModel() {
        UserDatabase userDatabase= MyApplication.getUserDatabase();
        UserInfoDao userInfoDao=userDatabase.userInfoDao();
        apiService=MyApplication.getApiService();
        myRepository =new MyRepository(userInfoDao,MyApplication.getApiService());
    }
    public LiveData<UserInfo.DataBean> getUserInfo(String s) {
        return myRepository.getUserinfo(s);
    }
    public LiveData<String> getFans(String s){return myRepository.getFans(s);}
    public List<VideoList.DataBean.ListBean> getVideoList(String s){return myRepository.getVideoList(s);}





}
