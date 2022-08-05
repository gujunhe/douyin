package com.qxy.douyin.ui.my;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.qxy.douyin.MyApplication;
import com.qxy.douyin.R;
import com.qxy.douyin.adapter.TabAdapter;
import com.qxy.douyin.databinding.FragmentMyBinding;
import com.qxy.douyin.fragment.TabFragment;
import com.qxy.douyin.model.Fans;
import com.qxy.douyin.model.TabItemModel;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.view.FullViewPager;
import com.qxy.douyin.view.ScaleScrollView;
import com.qxy.douyin.view.TitleLayout;
import com.qxy.libnavannotation.FragmentDestination;

import java.util.ArrayList;
import java.util.List;

@FragmentDestination(pageUrl = "main/tabs/my", needLogin = true)
public class MyFragment extends Fragment {

    private FragmentMyBinding binding;
    String TAG=this.getClass().getName();
    private TabLayout tab1, tab2;
    private TitleLayout titleLayout;
    private int colorPrimary;
    private ArgbEvaluator evaluator;
    private View statusView;
    private  View root;
    private  String nickname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel=new ViewModelProvider(this).get(MyViewModel.class);
        binding=FragmentMyBinding.inflate(inflater, container, false);
         root = binding.getRoot();
         binding.setEventHandler(new MyEventHandleListener(getContext()));
        MyApplication.open_id.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=""&&s!=null) {
                    myViewModel.getUserInfo(s).observe(getViewLifecycleOwner(), new Observer<UserInfo.DataBean>() {
                        @Override
                        public void onChanged(UserInfo.DataBean userinfo) {
                            if (userinfo != null) {
                                binding.setUserinfo(userinfo);
                                nickname = userinfo.getNickname();
                            }
                        }
                    });
                    myViewModel.getFans(s).observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String fans) {
                            if(fans!=null)
                            {
                                binding.setFans(fans);
                                Log.d(TAG,fans.toString());
                            }
                        }
                    });
                }
            }
        });
        colorPrimary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        initView();
        return root;
    }
    private void initView() {
        //设置状态栏和导航栏
        statusView = binding.statusView;
        LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        statusView.setLayoutParams(lp);
        statusView.setBackgroundColor(Color.TRANSPARENT);
        statusView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        AppCompatImageView banner = binding.banner;
        ScaleScrollView scrollView = binding.scrollView;
        scrollView.setTargetView(banner);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (null != tab1 && null != tab2 && null != titleLayout && null != statusView) {
                    int distance = tab1.getTop() - titleLayout.getHeight() - statusView.getHeight();
                    float ratio = v.getScaleY() * 1f / distance;
                    if (distance <= v.getScrollY()) {
                        ratio = 1;
                        if (tab2.getVisibility() != View.VISIBLE) {
                            tab2.setVisibility(View.VISIBLE);
                            statusView.setBackgroundColor(colorPrimary);
                        }
                    } else {
                        if (tab2.getVisibility() == View.VISIBLE) {
                            tab2.setVisibility(View.INVISIBLE);
                            statusView.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                    if (null == evaluator) {
                        evaluator = new ArgbEvaluator();
                    }
                    titleLayout.setBackgroundColor((int) evaluator.evaluate(ratio, Color.TRANSPARENT, colorPrimary));
                    titleLayout.setTitleColor((int) evaluator.evaluate(ratio, Color.TRANSPARENT, Color.WHITE));
                    titleLayout.setTitle(nickname);
                }
            }
        });

        tab1 = binding.tab1;
        tab2 = binding.tab2;
        titleLayout = binding.titleLayout;
        FullViewPager viewPager = binding.viewpager;
        viewPager.setAdapter(new TabAdapter(getContext(), getParentFragmentManager(), getTabs()));
        tab1.setupWithViewPager(viewPager);
        tab2.setupWithViewPager(viewPager);
    }

    private List<TabItemModel> getTabs() {
        List<TabItemModel> tabs = new ArrayList<>();
        tabs.add(new TabItemModel("作品", TabFragment.class.getName(), null));
        tabs.add(new TabItemModel("私密", TabFragment.class.getName(), null));
        tabs.add(new TabItemModel("收藏", TabFragment.class.getName(), null));
        tabs.add(new TabItemModel("喜欢", TabFragment.class.getName(), null));
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