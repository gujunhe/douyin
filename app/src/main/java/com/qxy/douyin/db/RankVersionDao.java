package com.qxy.douyin.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.qxy.douyin.model.RankVersion;

import java.util.List;


@Dao
public interface RankVersionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRankVersion(RankVersion.DataBean.ListBean rankversion);


    @Query("SELECT *FROM rankversion WHERE version = :version")
    LiveData<RankVersion.DataBean.ListBean> getRankVersionByversion(String version);

    //获取表中所有数据
    @Query("SELECT *FROM rankversion ")
    LiveData<List<RankVersion.DataBean.ListBean>> findAllbytype();
}
