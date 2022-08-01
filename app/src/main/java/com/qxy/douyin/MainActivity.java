package com.qxy.douyin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qxy.douyin.databinding.ActivityMainBinding;
import com.qxy.douyin.model.AccessToken;
import com.qxy.douyin.utils.NavGraphBuilder;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private NavController navController;
    private ActivityMainBinding binding;

    private String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = NavHostFragment.findNavController(fragment);
        NavGraphBuilder.build(this, navController, fragment.getId());

        navView.setOnNavigationItemSelectedListener(this);

        if(MyApplication.code=="") {
            DouYinOpenApi douyinOpenApi = DouYinOpenApiFactory.create(this);

            Authorization.Request request = new Authorization.Request();
            request.scope = "user_info,trial.whitelist";                          // 用户授权时必选权限
            request.state = "ww";                                 // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
            request.callerLocalEntry = "com.qxy.douyin.DouYinEntryActivity";
            douyinOpenApi.authorize(request);


        }
        Log.d(TAG,MyApplication.code+"///"+MyApplication.open_id+"///"+MyApplication.accesstoken);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navController.navigate(item.getItemId());

        return !TextUtils.isEmpty(item.getTitle());
    }
}