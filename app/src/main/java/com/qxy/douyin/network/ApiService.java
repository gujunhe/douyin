package com.qxy.douyin.network;


import android.app.DownloadManager;

import com.qxy.douyin.model.AccessToken;
import com.qxy.douyin.model.ClientToken;
import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.Following;
import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.model.UserInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/access_token/")
    Call<AccessToken> getaccess_token(@Body RequestBody body);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST("oauth/userinfo/")
    Call<UserInfo>getuserinfo(@Body RequestBody body);


    @GET("/fans/list/")
    Call<Fans>getfans(@Header("Content-Type")String type, @Header("access-token")String accesstoken, @Query("open_id")String openid,@Query("cursor")int cursor,@Query("count")int count);

    @GET("/following/list/")
    Call<Following>getfollowing(@Header("Content-Type")String type, @Header("access-token")String accesstoken, @Query("count")int count, @Query("open_id")String openid, @Query("cursor")int cursor);

    @POST("/oauth/client_token/")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ClientToken>getclientoken(@Body RequestBody body);

    @GET("/discovery/ent/rank/version/")
    Call<RankVersion>getrankversion(@Header("Content-Type")String contenttype, @Header("access-token")String clienttoken,@Query("cursor")int cursor,@Query("count")int count,@Query("type")String type);

    @GET("/discovery/ent/rank/item/")
    Call<RankItem>getrankitem(@Header("Content-Type")String contenttype, @Header("access-token")String clienttoken,@Query("type")String type,@Query("version")String version);


}
