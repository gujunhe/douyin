package com.qxy.douyin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.model.RankVersion;

import java.util.List;

@Dao
public interface RankItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRankItem(RankItem.DataBean.ListBean rankitem);
    //获取表中所有数据
    @Query("SELECT *FROM rankitem WHERE type = :type")
    LiveData<List<RankItem.DataBean.ListBean>> findAllbytype(String type);
}
