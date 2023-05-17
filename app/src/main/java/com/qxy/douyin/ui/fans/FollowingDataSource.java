package com.qxy.douyin.ui.fans;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.model.Following;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingDataSource extends PageKeyedDataSource<Integer, Following.DataBean.ListBean> {
    public static final int FIRST_PAGE =0;
    public static final int PER_PAGE=10;

    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Following.DataBean.ListBean> callback) {
        MyApplication.getApiService().getfollowing("application/json",MyApplication.accesstoken,PER_PAGE,MyApplication.open_id.getValue(),FIRST_PAGE).enqueue(new Callback<Following>() {
            @Override
            public void onResponse(Call<Following> call, Response<Following> response) {
                if(response.body().getData().getError_code()==0)
                {
                    callback.onResult(response.body().getData().getList(),null,FIRST_PAGE+response.body().getData().getCursor());
                }
                Log.d("Following",response.body().toString());
            }
            @Override
            public void onFailure(Call<Following> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Following.DataBean.ListBean> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Following.DataBean.ListBean> callback) {

        MyApplication.getApiService().getfollowing("application/json",MyApplication.accesstoken,PER_PAGE,MyApplication.open_id.getValue(),params.key).enqueue(new Callback<Following>() {
            @Override
            public void onResponse(Call<Following> call, Response<Following> response) {
                if(response.body().getData().getError_code()==0) {
                    Integer nextKey = response.body().getData().isHas_more() ? response.body().getData().getCursor() : null;
                    callback.onResult(response.body().getData().getList(), nextKey);
                }
                Log.d("Following",response.body().toString());

            }

            @Override
            public void onFailure(Call<Following> call, Throwable t) {

            }
        });

    }
}

