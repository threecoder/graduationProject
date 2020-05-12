package com.example.camp_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VoteStaticDetailActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    private LinearLayout optionGroup;
    private Toolbar detailToolbar;
    TextView voteDetailName, voteDetailType, VoteDetailNum;

    private int voteId;
    private String voteName, voteEndDate, voteType;
    private int optionalNum;
    JSONArray optionArray;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){

            } else if(msg.what==FAIL){
                Toast.makeText(VoteStaticDetailActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(VoteStaticDetailActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_static_detail);

        detailToolbar=findViewById(R.id.vote_static_detail_toolbar);
        detailToolbar.setTitle("投票详情");
        voteDetailName = findViewById(R.id.vote_static_detail_name);
        optionGroup =findViewById(R.id.option_group);
        voteDetailType = findViewById(R.id.vote_static_detail_type);
        VoteDetailNum = findViewById(R.id.vote_static_detail_num);

        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        voteId = getIntent().getIntExtra("voteId", 0);
        if(voteId == 0){
            Toast.makeText(VoteStaticDetailActivity.this, "传入参数有误", Toast.LENGTH_LONG).show();
            finish();
        }

        HttpUtil.get("/student/studentGetVoteStatic?voteId=" + voteId, new Callback() {
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
                    handler.sendMessage(msg);
                }else if (respondJson.getString("code").equals("fail")) {
                    msg.what=FAIL;
                    failInfo=respondJson.getString("msg");
                    handler.sendMessage(msg);
                } else if (respondJson.getString("code").equals("success")) {
                    JSONObject voteDetailJson=respondJson.getJSONObject("data");
                    voteName = voteDetailJson.getString("name");
                    voteType = voteDetailJson.getString("type");
                    optionalNum = voteDetailJson.getInteger("num");
                    optionArray = voteDetailJson.getJSONArray("options");
                    voteDetailName.setText(voteName);
                    voteDetailType.setText(voteType);
                    VoteDetailNum.setText(String.valueOf(optionalNum));
                    for(int i=0; i<optionArray.size(); i++){
                        LinearLayout linearLayout=new LinearLayout(VoteStaticDetailActivity.this);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        TextView dataNum = new TextView(VoteStaticDetailActivity.this);
                        dataNum.setText("票数: "+optionArray.getJSONObject(i).getString("quantity")+"\t占比: "+optionArray.getJSONObject(i).getString("per")+"%");
                        TextView text = new TextView(VoteStaticDetailActivity.this);
                        text.setText(optionArray.getJSONObject(i).getString("text"));
                        dataNum.setTextColor(Color.parseColor("#D2691E"));
                        text.setTextSize(18);
                        linearLayout.addView(dataNum, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        linearLayout.addView(text, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        linearLayout.setPadding(30, 0, 20, 50);
                        optionGroup.addView(linearLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

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
