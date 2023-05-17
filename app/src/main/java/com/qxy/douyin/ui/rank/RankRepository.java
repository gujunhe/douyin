package com.qxy.douyin.ui.rank;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.db.RankItemDao;
import com.qxy.douyin.db.RankVersionDao;
import com.qxy.douyin.model.ClientToken;
import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.network.ApiService;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankRepository {
    private  String TAG=this.getClass().getName();
    private  ApiService apiService;
    private  RankVersionDao rankVersionDao;
    private RankItemDao rankItemDao;
    MutableLiveData<String>clientToken=new MutableLiveData<String>();
    public  RankRepository(RankVersionDao rankVersionDao, RankItemDao rankItemDao,ApiService apiService)
    {
        this.rankVersionDao=rankVersionDao;
        this.apiService=apiService;
        this.rankItemDao=rankItemDao;
    }
    public LiveData<List<RankVersion.DataBean.ListBean>> getRankVersionByType(String type)
    {
        refreshversion(type);
        return rankVersionDao.findAllbytype();
    }

    public LiveData<List<RankItem.DataBean.ListBean>> getRankItemByTypeAndVersion(String type,String version)
    {
        refreshitembyTypeAndVersion(type,version);
        return rankItemDao.findAllbytype(type);
    }
    public void refreshitembyTypeAndVersion(String type,String version)
    {
        apiService.getrankitem("application/json",MyApplication.clientToken.getValue(),type,version).enqueue(new Callback<RankItem>() {
            @Override
            public void onResponse(Call<RankItem> call, Response<RankItem> response) {
                if(response.body().getData().getList()!=null)
                {
                    insertRankItem(response.body().getData().getList());
                    Log.d(TAG,response.body().getData().getList().toString());
                }
            }
            @Override
            public void onFailure(Call<RankItem> call, Throwable t) {
            }
        });
    }


    public  void refreshversion(String type) {
        apiService.getrankversion("application/json",MyApplication.clientToken.getValue(),0,20,type).enqueue(new Callback<RankVersion>() {
            @Override
            public void onResponse(Call<RankVersion> call, Response<RankVersion> response) {
                if(response.body().getData().getList()!=null)
                {
                    insertRankVersion(response.body().getData().getList());
                    Log.d(TAG,response.body().getData().getList().toString());
                }

            }

            @Override
            public void onFailure(Call<RankVersion> call, Throwable t) {

            }
        });



    }
    public MutableLiveData<String> getClienToken()
    {
        FormBody formBody = new FormBody.Builder()
                .add("client_key", MyApplication.clientkey)
                .add("client_secret", MyApplication.clientsecret)
                .add("grant_type","client_credential")
                .build();
        apiService.getclientoken(formBody).enqueue(new Callback<ClientToken>() {
            @Override
            public void onResponse(Call<ClientToken> call, Response<ClientToken> response) {
                String accesstoken=response.body().getData().getAccess_token();
                if(accesstoken!=null&&accesstoken!="") {
                    clientToken.postValue(accesstoken);
                }
            }

            @Override
            public void onFailure(Call<ClientToken> call, Throwable t) {

            }
        });
        return clientToken;
    }
    private  void insertRankVersion(List<RankVersion.DataBean.ListBean> list )
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<list.size();i++) {
                    Log.d(TAG, list.get(i).toString());
                    rankVersionDao.insertRankVersion(list.get(i));
                }
            }
        });
    }
    private  void insertRankItem(List<RankItem.DataBean.ListBean> list )
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<list.size();i++) {
                    Log.d(TAG, list.get(i).toString());
                    rankItemDao.insertRankItem(list.get(i));
                }
            }
        });
    }
}
