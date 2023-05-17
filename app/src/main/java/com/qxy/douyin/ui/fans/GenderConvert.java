package com.qxy.douyin.ui.fans;

public class GenderConvert {
    public  static  String GenderConvert(int i)
    {
        if(i==0) return "性别未知";
        else if(i==1)return "男";
        else return "女";
    }
}
