package com.qxy.douyin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.qxy.douyin.model.UserInfo;

@Dao
public interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserInfo(UserInfo.DataBean userinfo);


    @Query("SELECT *FROM userinfo WHERE openid = :openid")
    LiveData<UserInfo.DataBean>getUserInfoByopen_id(String openid);

}
