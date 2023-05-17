package com.qxy.douyin.ui.my;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.db.UserInfoDao;
import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.model.VideoList;
import com.qxy.douyin.network.ApiService;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {

    private  String TAG=this.getClass().getName();
    private UserInfoDao userInfoDao;
    private ApiService apiService;
    private List<VideoList.DataBean.ListBean> vedioList;
    private  MutableLiveData<String> fansLiveData=new MutableLiveData<>();
    public  MyRepository(UserInfoDao userInfoDao,ApiService apiService)
    {
        this.apiService=apiService;
        this.userInfoDao=userInfoDao;
    }

    public LiveData<UserInfo.DataBean>getUserinfo(final String openid)
    {
        refresh(openid);
        return userInfoDao.getUserInfoByopen_id(openid);
    }
    public List<VideoList.DataBean.ListBean> getVideoList(String openid)
    {
        apiService.getVedioList("application/json",MyApplication.accesstoken,openid,0,10).enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                Log.d(TAG+"videoList1",response.body().toString());
                vedioList=response.body().getData().getList();
            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {
                Log.d(TAG+"videoList2",t.toString());
            }
        });

        return vedioList;
    }
    public LiveData<String>getFans(final String openid)
    {
        apiService.getfans("application/json",MyApplication.accesstoken,openid,0,1).enqueue(new Callback<Fans>() {
            @Override
            public void onResponse(Call<Fans> call, Response<Fans> response) {
                fansLiveData.setValue(String.valueOf(response.body().getData().getTotal()));
            }

            @Override
            public void onFailure(Call<Fans> call, Throwable t) {

            }
        });
        return fansLiveData;
    }


    public  void refresh(String openid) {
                        FormBody formBody = new FormBody.Builder()
                                .add("access_token", MyApplication.accesstoken)
                                .add("open_id", MyApplication.open_id.getValue()).build();
                        Log.d(TAG, MyApplication.open_id + "////" + MyApplication.accesstoken);
                        apiService.getuserinfo(formBody).enqueue(new Callback<UserInfo>() {
                            @Override
                            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                                Log.d(TAG, response.body().getData().toString());
                                if (response.body().getData().getOpen_id() != null) {
                                    insertUser(response.body().getData());
                                }

                            }
                            @Override
                            public void onFailure(Call<UserInfo> call, Throwable t) {
                                Log.d(TAG, t.toString());

                            }
                        });
    }

    private  void insertUser(final UserInfo.DataBean userInfo)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userInfoDao.insertUserInfo(userInfo);
            }
        });
    }
}
