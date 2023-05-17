package com.qxy.douyin.ui.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.qxy.douyin.R;
import com.qxy.douyin.ui.fans.FansListActivity;

public class MyEventHandleListener {
    private Context context;
    public MyEventHandleListener(Context context){
        this.context=context;
    }
    public  void onButtonClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.fans:
            case R.id.fans1:
                Intent intent=new Intent(context, FansListActivity.class);
                intent.putExtra("fans",true);
                context.startActivity(intent);
            case R.id.follow:
            case R.id.follow1:
                Intent intent1=new Intent(context, FansListActivity.class);
                intent1.putExtra("fans",false);
                context.startActivity(intent1);
                break;
        }



    }
}
