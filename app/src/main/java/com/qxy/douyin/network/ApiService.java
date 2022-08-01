package com.qxy.douyin.network;


import android.app.DownloadManager;

import com.qxy.douyin.model.AccessToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/access_token/")
    Call<AccessToken> getaccess_token(@Body RequestBody body);

}
