package com.qxy.douyin.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverters {
    @TypeConverter
    public List<String>stringToObject(String value)

    {
        Type listType = new TypeToken<List<String>>(){}.getType();

        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String objectToString(List<String> list){
        Gson gson =new  Gson();
        return gson.toJson(list);
    }

}
