package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SecondActivity extends BaseActivity {
    public static final String TAG = SecondActivity.class.getName();
    TextView id,email,fName,lName,avatar;
    int uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_actcity);
        id = findViewById(R.id.id);
        email = findViewById(R.id.email);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        avatar = findViewById(R.id.avatar);
        getLoginInfo(uId);
    }


    private void getLoginInfo(int Id) {
        Log.d(TAG, "Id :-" + Id);
        new APICall().getLoginInfo(getApiInterface(), Id, new APICallback() {
            @Override
            public void onSuccess(Response<ResponseBody> response) {
                if (response.isSuccessful())
                    try {
                        String responseStr = response.body().string();
                        JSONObject jObj = new JSONObject(responseStr);
                        String resultString = jObj.getString("data");
                        Gson gson = new Gson();
                        Log.d(TAG, "responseStr :" + responseStr);
                        Data data = gson.fromJson(resultString, Data.class);
                        Log.d(TAG,"data:-"+data);
                        appendData(data);
                        Log.d(TAG, "RESULT :" + data);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void appendData(Data data) {
        Log.d(TAG,"data:-"+data);
         id.setText(String.valueOf(data.getId()));
         email.setText(data.getEmail());
         fName.setText(data.getFirstName());
         lName.setText(data.getLastName());
         avatar.setText(data.getAvatar());
    }

}
