package com.qxy.douyin.db;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.model.UserInfo;


@Database(entities = {UserInfo.DataBean.class,RankVersion.DataBean.ListBean.class, RankItem.DataBean.ListBean.class},version =3,exportSchema = false )
public abstract class UserDatabase extends RoomDatabase {

    private  static  final String DATABASE_NAME ="user_db";

    private static UserDatabase databaseInstance;

    public static synchronized  UserDatabase getInstance(Context context)
    {
        if(databaseInstance==null)
        {
            databaseInstance= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME).build();
        }
        return databaseInstance;
    }

    public  abstract  UserInfoDao userInfoDao();
    public  abstract  RankVersionDao rankVersionDao();
    public abstract  RankItemDao rankItemDao();

}
