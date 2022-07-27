package com.qxy.douyin.ui.publish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qxy.douyin.R;
import com.qxy.libnavannotation.ActivityDestination;

@ActivityDestination(pageUrl = "main/tabs/publish", needLogin = true)
public class PublishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
    }
}