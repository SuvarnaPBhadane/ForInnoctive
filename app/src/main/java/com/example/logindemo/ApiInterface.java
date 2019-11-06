package com.example.logindemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    public Call<ResponseBody> postLoginInfo(@Body PostLoginData postLoginData);

    @GET("users/2")
    public Call<ResponseBody> getLoginInfo(@Query("ID") int ID);

}
