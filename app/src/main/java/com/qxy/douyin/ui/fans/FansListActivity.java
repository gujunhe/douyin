package com.qxy.douyin.ui.fans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qxy.douyin.R;
import com.qxy.douyin.adapter.FansTabAdapter;
import com.qxy.douyin.adapter.TabAdapter;
import com.qxy.douyin.databinding.ActivityFansListBinding;
import com.qxy.douyin.fragment.TabFragment;
import com.qxy.douyin.model.TabItemModel;
import com.qxy.douyin.view.FullViewPager;

import java.util.ArrayList;
import java.util.List;

public class FansListActivity extends AppCompatActivity {
    private ActivityFansListBinding binding;
    private TabLayout tab;
    boolean isfans=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        isfans=(boolean)bundle.get("fans");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏为白色
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        binding=ActivityFansListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }
    private void initView() {
        tab = binding.tab;
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FansListFragment.newInstance(0));
        fragments.add(FansListFragment.newInstance(1));
        ViewPager2 viewPager = binding.viewpager;
        viewPager.setAdapter(new FansTabAdapter(getSupportFragmentManager(),getLifecycle(),fragments));
        new TabLayoutMediator(tab, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:tab.setText("关注");
                        break;
                    case 1:tab.setText("粉丝");
                    break;
                }
            }
        }).attach();
        if(isfans==true)
        tab.selectTab(tab.getTabAt(1));

    }
    private List<TabItemModel> getTabs() {
        List<TabItemModel> tabs = new ArrayList<>();
        Bundle bundle1=new Bundle();
        bundle1.putBoolean("fans",false);
        tabs.add(new TabItemModel("关注", FansListFragment.class.getName(), bundle1));
        Bundle bundle2=new Bundle();
        bundle2.putBoolean("fans",true);
        tabs.add(new TabItemModel("粉丝", FansListFragment.class.getName(), bundle2));
        return tabs;
    }
    private int getStatusBarHeight() {
        int height = 0;
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            height = getResources().getDimensionPixelSize(resId);
        }
        return height;
    }
}