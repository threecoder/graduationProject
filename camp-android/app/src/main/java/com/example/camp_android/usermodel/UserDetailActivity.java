package com.example.camp_android.usermodel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.MainActivity;
import com.example.camp_android.R;
import com.example.camp_android.VoteDetailActivity;
import com.example.camp_android.util.HttpUtil;
import com.example.camp_android.util.ToastUtil;


import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserDetailActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    private EditText showUserIdcard, showUserName, showUserPhone, showUserEmail, showUserCompany,
                            showUserPosition, showUserProvince, showUserCity, showUserArea, showUserAddress;
    private Spinner showUserSex;
    private Toolbar detailToolbar;
    private Button btnSave;

    private JSONObject userInfo;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                fillUserInfo();
                Toast.makeText(UserDetailActivity.this, successInfo, Toast.LENGTH_LONG).show();
            }else if(msg.what==FAIL){
                Toast.makeText(UserDetailActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(UserDetailActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        detailToolbar = findViewById(R.id.userInfo_toolbar);
        detailToolbar.setTitle("个人信息");
        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        showUserIdcard=findViewById(R.id.user_idcard);
        showUserName=findViewById(R.id.user_name);
        showUserSex=findViewById(R.id.user_sex);
        showUserPhone=findViewById(R.id.user_phone);
        showUserEmail=findViewById(R.id.user_email);
        showUserCompany=findViewById(R.id.user_company);
        showUserPosition=findViewById(R.id.user_position);
        showUserProvince=findViewById(R.id.user_province);
        showUserCity=findViewById(R.id.user_city);
        showUserArea=findViewById(R.id.user_area);
        showUserAddress=findViewById(R.id.user_address);
        btnSave=findViewById(R.id.btn_save_info);

        HttpUtil.get("/student/getUserInfo", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resBodyStr=response.body().string();
                JSONObject res=JSONObject.parseObject(resBodyStr);
                Message msg=new Message();
                if(res.getString("code")==null){
                    msg.what=ERROR;
                    errorInfo=res.getString("msg");
                    handler.sendMessage(msg);
                }else if(res.getString("code").equals("fail")){
                    msg.what=FAIL;
                    failInfo=res.getString("msg");
                    handler.sendMessage(msg);
                }else {
                    userInfo=res.getJSONObject("data");
                    msg.what=SUCCESS;
                }
            }
        });


    }

    // 填充个人信息
    private void fillUserInfo(){
        showUserIdcard.setText(userInfo.getString("idNum"));
        showUserName.setText(userInfo.getString("name"));
        showUserSex.setSelection(userInfo.getString("sex").equals("男")?0:1);
        showUserPhone.setText(userInfo.getString("phone"));
        showUserEmail.setText(userInfo.getString("email"));
        showUserCompany.setText(userInfo.getString("c"));
        showUserPosition.setText(userInfo.getString("position"));
        showUserProvince.setText(userInfo.getString("province"));
        showUserCity.setText(userInfo.getString("city"));
        showUserArea.setText(userInfo.getString("area"));
        showUserAddress.setText(userInfo.getString("zone"));
    }


    @Override
    protected void onStart() {
        super.onStart();
        // 保存按钮的点击事件
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog confirm = new AlertDialog.Builder(UserDetailActivity.this).create();
                confirm.setMessage("确定保存信息吗？");
                confirm.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String idNum = showUserIdcard.getText().toString();
                        String phone = showUserPhone.getText().toString();
                        String name = showUserName.getText().toString();
                        String email = showUserEmail.getText().toString();
                        String position = showUserPosition.getText().toString();
                        String province = showUserProvince.getText().toString();
                        String city = showUserCity.getText().toString();
                        String area = showUserArea.getText().toString();
                        String address = showUserAddress.getText().toString();
                        String sex = showUserSex.getSelectedItem().toString();
                        JSONObject json = new JSONObject();
                        json.put("idNum", idNum);
                        json.put("phone", phone);
                        json.put("name", name);
                        json.put("email", email);
                        json.put("position", position);
                        json.put("province", province);
                        json.put("city", city);
                        json.put("area", area);
                        json.put("zone", address);
                        json.put("sex", sex);
                        HttpUtil.post("/student/setUserInfo", json, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String resBodyStr=response.body().string();
                                JSONObject res=JSONObject.parseObject(resBodyStr);
                                Message msg=new Message();
                                if(res.getString("code")==null){
                                    msg.what=ERROR;
                                    errorInfo=res.getString("msg");
                                    handler.sendMessage(msg);
                                }else if(res.getString("code").equals("fail")){
                                    msg.what=FAIL;
                                    failInfo=res.getString("msg");
                                    handler.sendMessage(msg);
                                }else {
                                    successInfo=res.getString("msg");
                                    msg.what=SUCCESS;
                                    handler.sendMessage(msg);
                                }
                            }
                        });
                    }
                });
                confirm.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("取消投票");
                    }
                });
                confirm.show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
