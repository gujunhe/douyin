package com.qxy.douyin.ui.rank;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.db.RankItemDao;
import com.qxy.douyin.db.RankVersionDao;
import com.qxy.douyin.db.UserDatabase;
import com.qxy.douyin.db.UserInfoDao;
import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.network.ApiService;
import com.qxy.douyin.ui.my.MyRepository;

import java.util.List;

public class RankViewModel extends ViewModel {
    private  String TAG =this.getClass().getName();
    private RankRepository rankRepository;
    private LiveData<List<RankVersion.DataBean.ListBean>> rankVersion;
    private ApiService apiService;

    public RankViewModel()
    {
        UserDatabase userDatabase= MyApplication.getUserDatabase();
        RankVersionDao rankVersionDao=userDatabase.rankVersionDao();
        RankItemDao rankItemDao=userDatabase.rankItemDao();
        apiService=MyApplication.getApiService();
        rankRepository=new RankRepository(rankVersionDao,rankItemDao,apiService);
    }
    public LiveData<List<RankVersion.DataBean.ListBean>> getRankVersionByType(String s) {
        return rankRepository.getRankVersionByType(s);
    }
    public LiveData<List<RankItem.DataBean.ListBean>> getRankItemByTypeAndVersion(String type,String version) {
        return rankRepository.getRankItemByTypeAndVersion(type,version);
    }
    public MutableLiveData<String> getClienToken()
    {
        return  rankRepository.getClienToken();
    }

}
