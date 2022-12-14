package com.qxy.douyin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.databinding.FragmentHomeBinding;
import com.qxy.douyin.model.BottomBar;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.model.UserInfo;
import com.qxy.douyin.ui.rank.RankActivity;
import com.qxy.libnavannotation.FragmentDestination;

@FragmentDestination(pageUrl = "main/tabs/home", asStarter = true)
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    String TAG="HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button button=binding.torank;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), RankActivity.class);
                startActivity(intent);
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