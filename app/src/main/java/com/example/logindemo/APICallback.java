package com.example.logindemo;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface APICallback {
    public void onSuccess(Response<ResponseBody> response);
    public void onFailure(Throwable throwable);
    //  public void onSuccessErrorMessage(String errorMessage);
}
