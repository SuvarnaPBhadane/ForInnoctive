package com.example.logindemo;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICall {
    private Call<ResponseBody> callAPI = null;

    public final String TAG = APICall.class.getName();
    public void postLoginInfo(ApiInterface apiInterface, PostLoginData postLoginData,final APICallback apiCallback)
    {
        callAPI=apiInterface.postLoginInfo(postLoginData);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, new StringBuilder().append("postLoginInfo:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("postLoginInfo:onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);

            }
        });
    }


    public void getLoginInfo(ApiInterface apiInterface, int ID,
                                          final APICallback apiCallback) {
        callAPI = apiInterface.getLoginInfo(ID);
        callAPI.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"getMyBuyers Raw Response :  "+response.raw().request().url());
                Log.d(TAG, new StringBuilder().append("getOrders:onResponse:-").append(response.message()).append(response.code()).append(response.isSuccessful()).toString());
                apiCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, new StringBuilder().append("getMyBuyers :onFailure:-").append(t.getMessage()).toString());
                call.cancel();
                apiCallback.onFailure(t);
            }
        });
    }
}
