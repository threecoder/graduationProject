package com.example.camp_android;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.util.HttpUtil;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ActivityDetailActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    private int activityId;

    Toolbar detailToolbar;
    TextView activityName, activityTime, activityAddress, activityIndroduce, activityContact, activityPhone;
    Button feeSignBtn;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                Toast.makeText(ActivityDetailActivity.this, successInfo, Toast.LENGTH_LONG).show();
            }else if(msg.what==FAIL){
                Toast.makeText(ActivityDetailActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(ActivityDetailActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);

        detailToolbar = findViewById(R.id.activity_detail_toolbar);
        detailToolbar.setTitle("活动详情");
        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        activityName = (TextView) findViewById(R.id.activity_detail_name);
        activityTime = (TextView) findViewById(R.id.activity_detail_time);
        activityAddress = (TextView) findViewById(R.id.activity_detail_address);
        activityIndroduce = (TextView) findViewById(R.id.activity_detail_introduce);
        activityContact = (TextView) findViewById(R.id.activity_detail_contact);
        activityPhone = (TextView) findViewById(R.id.activity_detail_phone);

        feeSignBtn = (Button) findViewById(R.id.activity_detail_sign);

        activityId = getIntent().getIntExtra("activityId", 0);
        if(activityId == 0){
            Toast.makeText(ActivityDetailActivity.this, "培训参数错误", Toast.LENGTH_LONG).show();
            finish();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        activityName.setText(getIntent().getCharSequenceExtra("activityName"));
        activityTime.setText(getIntent().getCharSequenceExtra("activityTime"));
        activityAddress.setText(getIntent().getCharSequenceExtra("activityAddress"));
        activityIndroduce.setText(getIntent().getCharSequenceExtra("activityDesc"));
        activityContact.setText(getIntent().getCharSequenceExtra("activityContact"));
        activityPhone.setText(getIntent().getCharSequenceExtra("activityPhone"));
        feeSignBtn.setText("报名 ￥"+getIntent().getCharSequenceExtra("activityFee"));
        if(getIntent().getCharSequenceExtra("type").equals("signed")){
            feeSignBtn.setBackgroundColor(Color.parseColor("#D3D3D3"));
            feeSignBtn.setClickable(false);
            feeSignBtn.setEnabled(false);
        }

        feeSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog confirm = new AlertDialog.Builder(ActivityDetailActivity.this).create();
                confirm.setMessage("确定要报名吗？");
                confirm.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        JSONObject json = new JSONObject();
                        json.put("activityId", activityId);
                        HttpUtil.post("/student/joinActivity", json, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String resBodyStr = response.body().string();
                                Message msg = new Message();
                                JSONObject res = JSONObject.parseObject(resBodyStr);
                                if(res.getString("code")==null){
                                    msg.what = ERROR;
                                    errorInfo = res.toJSONString();
                                }
                                else if (res.getString("code").equals("fail")) {
                                    msg.what = FAIL;
                                    failInfo = res.getString("msg");
                                } else if (res.getString("code").equals("success")){
                                    msg.what = SUCCESS;
                                    successInfo = res.getString("msg");
                                }
                                handler.sendMessage(msg);
                            }
                        });
                    }
                });
                confirm.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("取消报名");
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
