package com.qxy.douyin.ui.fans;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.qxy.douyin.MyApplication;
import com.qxy.douyin.model.Fans;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FansDataSource extends PageKeyedDataSource<Integer, Fans.DataBean.ListBean> {
    public static final int FIRST_PAGE =0;
    public static final int PER_PAGE=20;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Fans.DataBean.ListBean> callback) {
        MyApplication.getApiService().getfans("application/json",MyApplication.accesstoken,MyApplication.open_id.getValue(),FIRST_PAGE,PER_PAGE).enqueue(new Callback<Fans>() {
            @Override
            public void onResponse(Call<Fans> call, Response<Fans> response) {
                if(response.body()!=null)
                {
                    callback.onResult(response.body().getData().getList(),null,FIRST_PAGE+response.body().getData().getCursor());
                }
            }
            @Override
            public void onFailure(Call<Fans> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Fans.DataBean.ListBean> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Fans.DataBean.ListBean> callback) {

        MyApplication.getApiService().getfans("application/json",MyApplication.accesstoken,MyApplication.open_id.getValue(),params.key,PER_PAGE).enqueue(new Callback<Fans>() {
            @Override
            public void onResponse(Call<Fans> call, Response<Fans> response) {
                if(response.body()==null)
                {return;}
                    Integer nextKey=response.body().getData().isHas_more()?response.body().getData().getCursor():null;
                    callback.onResult(response.body().getData().getList(),nextKey);
            }

            @Override
            public void onFailure(Call<Fans> call, Throwable t) {

            }
        });

    }
}
