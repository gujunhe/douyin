package com.qxy.douyin.ui.rank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qxy.douyin.MyApplication;
import com.qxy.douyin.R;
import com.qxy.douyin.adapter.FansTabAdapter;
import com.qxy.douyin.databinding.ActivityRankBinding;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.model.TabItemModel;
import com.qxy.douyin.ui.fans.FansListFragment;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity  implements LifecycleOwner{
    private ActivityRankBinding binding;
    private  String TAG=this.getClass().getName();
    private List<RankVersion>rankVersionList;
    private TabLayout tab;
    public RankViewModel rankViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rankViewModel=new ViewModelProvider(this).get(RankViewModel.class);
        LifecycleOwner lifecycleOwner=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏为白色
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        rankViewModel.getClienToken().observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG,s);
                if(s!=null)
                {
                    MyApplication.clientToken.postValue(s);
                }
            }
        });
        initView();

    }

    private void initView() {
        tab = binding.tab;
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RankFragment.newInstance(1));
        fragments.add(RankFragment.newInstance(2));
        fragments.add(RankFragment.newInstance(3));
        ViewPager2 viewPager = binding.viewpager;
        viewPager.setAdapter(new FansTabAdapter(getSupportFragmentManager(),getLifecycle(),fragments));
        new TabLayoutMediator(tab, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:tab.setText("电影榜单");
                        break;
                    case 1:tab.setText("电视剧榜单");
                        break;
                    case 2:tab.setText("综艺榜单");
                        break;
                }
            }
        }).attach();

    }
    private List<TabItemModel> getTabs() {
        List<TabItemModel> tabs = new ArrayList<>();
        Bundle bundle1=new Bundle();
        bundle1.putInt("type",1);
        tabs.add(new TabItemModel("电影榜单", FansListFragment.class.getName(), bundle1));
        Bundle bundle2=new Bundle();
        bundle2.putInt("type",2);
        tabs.add(new TabItemModel("电视剧榜单", FansListFragment.class.getName(), bundle2));
        Bundle bundle3=new Bundle();
        bundle3.putInt("type",3);
        tabs.add(new TabItemModel("综艺榜单", FansListFragment.class.getName(), bundle3));
        return tabs;
    }
}