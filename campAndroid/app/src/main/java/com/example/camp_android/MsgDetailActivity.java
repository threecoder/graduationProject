package com.example.camp_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
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

public class MsgDetailActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    private int msgId;

    Toolbar detailToolbar;
    TextView msgDetailName, msgDetailTime, msgDetailContent;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){

            }else if(msg.what==FAIL){
                Toast.makeText(MsgDetailActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(MsgDetailActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        detailToolbar = findViewById(R.id.msg_detail_toolbar);
        detailToolbar.setTitle("活动详情");
        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        msgDetailName = (TextView) findViewById(R.id.msg_detail_name);
        msgDetailTime = (TextView) findViewById(R.id.msg_detail_time);
        msgDetailContent = (TextView) findViewById(R.id.msg_detail_content);


        msgId = getIntent().getIntExtra("msgId", 0);
        if(msgId == 0){
            Toast.makeText(MsgDetailActivity.this, "消息参数错误", Toast.LENGTH_LONG).show();
            finish();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        HttpUtil.get("/student/getMsgDetail?msgID="+msgId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject respondJson = JSONObject.parseObject(response.body().string());
                Message msg = new Message();
                if (respondJson.getString("code")==null){
                    msg.what=ERROR;
                    errorInfo=respondJson.toJSONString();
                }else if (respondJson.getString("code").equals("fail")) {
                    msg.what=FAIL;
                    failInfo=respondJson.getString("msg");
                } else if (respondJson.getString("code").equals("success")) {
                    msgDetailName.setText(respondJson.getJSONObject("data").getString("title"));
                    msgDetailTime.setText(respondJson.getJSONObject("data").getString("time"));
                    msgDetailContent.setText(respondJson.getJSONObject("data").getString("msg"));
                    msg.what = SUCCESS;
                }
                handler.sendMessage(msg);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent();
                setResult(1, intent);
                finish();
                break;
        }
        return true;
    }
}
