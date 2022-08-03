package com.qxy.douyin.ui.home;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.qxy.douyin.MainActivity;
import com.qxy.douyin.MyApplication;
import com.qxy.douyin.db.UserDatabase;
import com.qxy.douyin.db.UserInfoDao;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.network.ApiService;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private  String TAG =this.getClass().getName();
    public HomeViewModel() {




    }

}