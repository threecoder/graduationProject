package com.example.camp_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VoteDetailActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private int requestType;//1为请求数据，2为投票

    private String failInfo, errorInfo, successInfo;

    private LinearLayout checkGroup;
    private Toolbar detailToolbar;
    TextView voteDetailName, voteDetailEndTime, voteDetailType, VoteDetailNum;
    private Button voteSubmitBtn;

    private int voteId;
    private String voteName, voteEndDate, voteType;
    private int optionalNum;
    JSONArray optionArray;
    private List<CheckBox> checkBoxList;
    //当前被选中的数目
    private int selectedNum;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                if(requestType==1){
                    voteDetailName.setText(voteName);
                    voteDetailEndTime.setText(voteEndDate);
                    voteDetailType.setText(voteType);
                    VoteDetailNum.setText(String.valueOf(optionalNum));
                    for(int i=0; i<optionArray.size(); i++){
                        CheckBox tempCheckBox=new CheckBox(VoteDetailActivity.this);
                        tempCheckBox.setText(optionArray.getString(i));
                        tempCheckBox.setTextSize(18);
                        tempCheckBox.setTextColor(Color.parseColor("#808080"));
                        checkGroup.addView(tempCheckBox, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        checkBoxList.add(tempCheckBox);
                    }

                    // 监听所有checkBox
                    for(CheckBox checkBox:checkBoxList){
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                System.out.println("---------"+selectedNum);
                                if(b){
                                    selectedNum++;
                                    if(selectedNum == optionalNum){
                                        for(CheckBox checkBox:checkBoxList){
                                            if(!checkBox.isChecked()) {
                                                checkBox.setEnabled(false);
                                                checkBox.setClickable(false);
                                            }
                                        }
                                    }
                                } else {
                                    selectedNum--;
                                    for(CheckBox checkBox:checkBoxList){
                                        checkBox.setEnabled(true);
                                        checkBox.setClickable(true);
                                    }
                                }
                            }
                        });
                    }
                }else {
                    Toast.makeText(VoteDetailActivity.this, successInfo, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent();
                    setResult(1, intent);
                    finish();
                }

            } else if(msg.what==FAIL){
                Toast.makeText(VoteDetailActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(VoteDetailActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_detail);

        selectedNum=0;
        checkBoxList=new ArrayList<>();

        detailToolbar=findViewById(R.id.vote_detail_toolbar);
        detailToolbar.setTitle("投票");
        voteDetailName = findViewById(R.id.vote_detail_name);
        checkGroup = findViewById(R.id.check_group);
        voteDetailType = findViewById(R.id.vote_detail_type);
        voteDetailEndTime = findViewById(R.id.vote_detail_end_time);
        VoteDetailNum = findViewById(R.id.vote_detail_num);
        voteSubmitBtn = findViewById(R.id.vote_submit);

        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        voteId = getIntent().getIntExtra("voteId", 0);
        if(voteId == 0){
            Toast.makeText(VoteDetailActivity.this, "传入参数有误", Toast.LENGTH_LONG).show();
            finish();
        }

        HttpUtil.get("/student/getVoteDetail?voteId=" + voteId, new Callback() {
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
                    voteEndDate = voteDetailJson.getString("date");
                    voteType = voteDetailJson.getString("type");
                    optionalNum = voteDetailJson.getInteger("num");
                    optionArray = voteDetailJson.getJSONArray("options");
                    requestType=1;
                    msg.what=SUCCESS;
                    handler.sendMessage(msg);
                }
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        voteSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(selectedNum == 0){
                    Toast.makeText(VoteDetailActivity.this, "还未选中任何选项", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog confirm = new AlertDialog.Builder(VoteDetailActivity.this).create();
                    confirm.setMessage("确定投票吗？");
                    confirm.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            JSONObject json = new JSONObject();
                            json.put("voteId", voteId);
                            JSONArray array=new JSONArray();
                            for(int index=0; index<checkBoxList.size(); index++){
                                if(checkBoxList.get(index).isChecked()){
                                    JSONObject object=new JSONObject();
                                    object.put("index", index+1);
                                    object.put("text", checkBoxList.get(index).getText());
                                    array.add(object);
                                }
                            }
                            json.put("data", array);
                            System.out.println("投票发送的数据为: "+json);
                            HttpUtil.post("/student/studentVote", json, new Callback() {
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
                                        requestType=2;
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
                            System.out.println("取消投票");
                        }
                    });
                    confirm.show();
                }
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
