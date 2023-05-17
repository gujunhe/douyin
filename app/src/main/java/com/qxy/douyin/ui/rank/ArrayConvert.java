package com.qxy.douyin.ui.rank;

import java.util.List;

public class ArrayConvert {
    public  static  String DirectorsConvert(List<String>a)
    {
        String s=new String("导演:");
        if(a==null) return "";
            for(int i=0;i<a.size();i++)
            {
                s+=a.get(i)+" ";
            }
            return s;
    }

    public  static  String ActorsConvert(List<String>a)
    {
        String s=new String("演员:");
        if(a==null) return "";
        for(int i=0;i<a.size();i++)
        {
            s+=a.get(i)+" ";
        }
        return s;
    }

    public  static  String AreaConvert(List<String>a)
    {
        String s=new String("地区:");
        if(a==null) return "";
        for(int i=0;i<a.size();i++)
        {
            s+=a.get(i)+" ";
        }
        return s;
    }

    public  static  String TagConvert(List<String>a)
    {
        String s=new String();
        if(a==null) return "";
        for(int i=0;i<a.size();i++)
        {
            s+=a.get(i)+" ";
        }
        return s;
    }

    public  static  String HotConvert(String s)
    {

        return "热度:"+s;
    }
}
