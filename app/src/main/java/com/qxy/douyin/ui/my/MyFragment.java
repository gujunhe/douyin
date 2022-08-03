package com.qxy.douyin.ui.my;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.R;
import com.qxy.douyin.databinding.FragmentMyBinding;
import com.qxy.douyin.model.UserInfo;
import com.qxy.libnavannotation.FragmentDestination;

@FragmentDestination(pageUrl = "main/tabs/my", needLogin = true)
public class MyFragment extends Fragment {

    private FragmentMyBinding binding;
    String TAG=this.getClass().getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel=new ViewModelProvider(this).get(MyViewModel.class);
        binding=FragmentMyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        MyApplication.open_id.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG,"openidchanged"+s);
                myViewModel.getUserInfo(s).observe(getViewLifecycleOwner(), new Observer<UserInfo.DataBean>() {
                    @Override
                    public void onChanged(UserInfo.DataBean userinfo) {
                        Log.d(TAG,"UserInfoonchanged");
                        if (userinfo != null) {
                            binding.setUserinfo(userinfo);
                            Log.d(TAG,"UserInfoonchanged"+userinfo.getNickname());
                        }
                    }
                });
            }
        });
        return root;
    }
}