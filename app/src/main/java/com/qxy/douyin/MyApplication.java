package com.qxy.douyin;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.DouYinOpenConfig;
import com.qxy.douyin.db.UserDatabase;
import com.qxy.douyin.model.AccessToken;
import com.qxy.douyin.network.ApiService;
import com.qxy.douyin.network.RetrofitClient;

public class MyApplication extends Application {
    private static Context context;
    private static ApiService apiService;
    private SharedPreferences sharedPreferences;
    public  static String clientkey="aw3qmszxy2pskqbq";
    public  static String clientsecret="6f282f67b556b75894df0af02dba431c";
    public  static MutableLiveData<String> open_id;
    public  static  String code="";
    public  static  String accesstoken="";
    private static UserDatabase userDatabase;
    public   static MutableLiveData<String> clientToken;
    public  static boolean hasrankcache;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        DouYinOpenApiFactory.init(new DouYinOpenConfig(clientkey));
        apiService= RetrofitClient.getInstance().getApi();
        clientToken=new MutableLiveData<>();
        clientToken.postValue("");
        sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        code=sharedPreferences.getString("code","");
        hasrankcache=sharedPreferences.getBoolean("hasrankcache",false);
        open_id=new MutableLiveData<>();
        open_id.setValue(sharedPreferences.getString("open_id",""));
        accesstoken=sharedPreferences.getString("accesstoken","");
        userDatabase=UserDatabase.getInstance(this);

    }

    public  static  Context getInstance()
    {
        return context;
    }
    public  static ApiService getApiService()
    {
        return apiService;
    }
    public  static UserDatabase getUserDatabase(){return userDatabase;}
}
