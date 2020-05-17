package com.example.camp_android.usermodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.MainActivity;
import com.example.camp_android.R;
import com.example.camp_android.config.SharedPreferencesConfig;
import com.example.camp_android.util.HttpUtil;
import com.example.camp_android.util.Md5Util;
import com.example.camp_android.util.ToastUtil;


import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    private EditText userAccount;
    private EditText userPwd;
    private Button btnLogin;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                Toast.makeText(LoginActivity.this, successInfo, Toast.LENGTH_LONG).show();
            }else if(msg.what==FAIL){
                Toast.makeText(LoginActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(LoginActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userAccount = findViewById(R.id.account_input);
        userPwd = findViewById(R.id.password_input);
        btnLogin=findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject json = new JSONObject();
                final String userId = userAccount.getText().toString();
                String pwd = userPwd.getText().toString();
                json.put("username", userId);
                json.put("password", Md5Util.digest(pwd));
                HttpUtil.post("/login", json, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JSONObject respondJson = JSONObject.parseObject(response.body().string());
                        Message msg=new Message();
                        if(respondJson.getString("code")==null){
                            msg.what=ERROR;
                            errorInfo=respondJson.toJSONString();
                            handler.sendMessage(msg);
                        }
                        if(respondJson.getString("code").equals("fail")){
                            msg.what=FAIL;
                            failInfo=respondJson.getString("msg");
                            handler.sendMessage(msg);
                        }else if(respondJson.getString("code").equals("success")) {
                            msg.what=SUCCESS;
                            successInfo=respondJson.getString("msg");
                            handler.sendMessage(msg);
                            String userName = respondJson.getJSONObject("data").getString("name");
                            SharedPreferencesConfig.init(LoginActivity.this);
                            SharedPreferencesConfig.putString("user_name", userName);
                            Intent intent=new Intent();
                            intent.putExtra("userName", userName);
                            setResult(22, intent);
                            finish();
                        }
                    }
                });
            }
        });

    }

}
