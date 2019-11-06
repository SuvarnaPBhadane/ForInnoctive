package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    public final String TAG = MainActivity.class.getName();
    EditText userName,password;
    Button sumbitBtn;
    PostLoginData postLoginData = new PostLoginData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        sumbitBtn = findViewById(R.id.submitBtn);
        sumbitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postLoginData.setEmail(userName.getText().toString());
                postLoginData.setPassword(password.getText().toString());
                postLoginInfo(postLoginData);

            }
        });
    }


        private void postLoginInfo(PostLoginData postLoginData) {
            Log.d(TAG,"postLoginInfo"+postLoginData.toString());
            new APICall().postLoginInfo(getApiInterface(), postLoginData, new APICallback() {
                @Override
                public void onSuccess(Response<ResponseBody> response) {
                    if (response.isSuccessful()) {

                        Log.d(TAG, "login   success :" + response.toString());
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                    }
                    Log.d("TAG", "login  not  success :" + response.toString());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Log.d("TAG","Logout in failure :");
                }
            });
        }

    }

