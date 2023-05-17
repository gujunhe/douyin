package com.qxy.douyin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bytedance.sdk.open.aweme.CommonConstants;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.qxy.douyin.model.AccessToken;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DouYinEntryActivity extends Activity implements IApiEventHandler {

    DouYinOpenApi douYinOpenApi;
    String TAG ="DouYinEntryActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        douYinOpenApi = DouYinOpenApiFactory.create(this);
        douYinOpenApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        // 授权成功可以获得authCode
        if (resp.getType() == CommonConstants.ModeType.SEND_AUTH_RESPONSE) {
            Authorization.Response response = (Authorization.Response) resp;
            if (resp.isSuccess()) {
                Toast.makeText(this, "授权成功，获得权限：" + response.grantedPermissions,
                        Toast.LENGTH_LONG).show();
                Log.d("DouYinEntryActivity",((Authorization.Response) resp).authCode);
                SharedPreferences.Editor editor=getSharedPreferences("user",MODE_PRIVATE).edit();
                editor.putString("code",((Authorization.Response) resp).authCode);


                FormBody formBody = new FormBody.Builder()
                        .add("client_secret", "6f282f67b556b75894df0af02dba431c")
                        .add("code", ((Authorization.Response) resp).authCode)
                        .add("grant_type", "authorization_code")
                        .add("client_key", "aw3qmszxy2pskqbq").build();

                MyApplication.getApiService().getaccess_token(formBody).enqueue(new Callback<AccessToken>() {


                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        Log.d(TAG, response.body().toString());
                        editor.putString("open_id",response.body().getData().getOpen_id());
                        editor.putString("accesstoken",response.body().getData().getAccess_token());
                        editor.apply();
                        MyApplication.accesstoken=response.body().getData().getAccess_token();
                        MyApplication.open_id.setValue((response.body().getData().getOpen_id()));
                        Log.d(TAG,MyApplication.code+"///"+MyApplication.open_id+"///"+MyApplication.accesstoken);
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        Log.d(TAG, t.toString());
                    }
                });

            } else {
                Toast.makeText(this, "授权失败" + response.grantedPermissions,
                        Toast.LENGTH_LONG).show();
                Log.d("HHH",String.valueOf(resp.errorCode));
            }
            finish();
        }
    }

    @Override
    public void onErrorIntent(@Nullable Intent intent) {
        // 错误数据
        Toast.makeText(this, "intent出错啦", Toast.LENGTH_LONG).show();
        finish();
    }
}
