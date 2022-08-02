package com.qxy.douyin.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.qxy.douyin.databinding.FragmentHomeBinding;
import com.qxy.douyin.model.UserInfo;
import com.qxy.libnavannotation.FragmentDestination;

@FragmentDestination(pageUrl = "main/tabs/home", asStarter = true)
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        homeViewModel.getUserInfo().observe(getViewLifecycleOwner(), new Observer<UserInfo.DataBean>() {
            @Override
            public void onChanged(UserInfo.DataBean userinfo) {
                if(userinfo!=null)
                {
                    binding.setUserinfo(userinfo);
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}