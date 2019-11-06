package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public ApiInterface apiInterface;
    public ApiInterface getApiInterface() {
        apiInterface = ApiClient.getBaseData().create(ApiInterface.class);
        return apiInterface;
    }
}
