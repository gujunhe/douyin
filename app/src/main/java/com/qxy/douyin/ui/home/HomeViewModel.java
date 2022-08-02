package com.qxy.douyin.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.db.UserDatabase;
import com.qxy.douyin.db.UserInfoDao;
import com.qxy.douyin.model.UserInfo;

public class HomeViewModel extends ViewModel {


    private LiveData<UserInfo.DataBean> userinfo;
    private HomeRepository homeRepository;

    public HomeViewModel() {
        UserDatabase userDatabase= MyApplication.getUserDatabase();
        UserInfoDao userInfoDao=userDatabase.userInfoDao();
        homeRepository=new HomeRepository(userInfoDao,MyApplication.getApiService());
        userinfo=homeRepository.getUSerInfo(MyApplication.open_id);

    }

    public LiveData<UserInfo.DataBean> getUserInfo() {
        return userinfo;
    }
}