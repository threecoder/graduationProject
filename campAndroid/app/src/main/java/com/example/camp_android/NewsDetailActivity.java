package com.example.camp_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.adapter.ListViewAdapter;
import com.example.camp_android.util.HttpUtil;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsDetailActivity extends AppCompatActivity {


    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo;

    private Toolbar detailToolbar;
    private TextView newsTitle, newsTime, newsDesc;
    private WebView newsContent;
    private String type;

    int news_id;
    JSONObject newDetailJson;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                if(newDetailJson==null){
                    Toast.makeText(NewsDetailActivity.this, "新闻详情数据为空", Toast.LENGTH_LONG).show();
                }else {
                    newsTitle.setText(newDetailJson.getString("title"));
                    newsTime.setText(newDetailJson.getString("date"));
                    newsDesc.setText(newDetailJson.getString("desc"));
                    String context=newDetailJson.getString("content");
                    String placeHolder=newDetailJson.getString("placeholder");
                    JSONArray urlList=newDetailJson.getJSONArray("urlList");
                    if(placeHolder!=null && urlList!=null){
                        newsContent.loadData(replacePlaceHolder(context, placeHolder, urlList),"text/html; charset=UTF-8", null);
                    }else {
                        newsContent.loadData(context,"text/html; charset=UTF-8", null);
                    }
                }
            } else if(msg.what==FAIL){
                Toast.makeText(NewsDetailActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(NewsDetailActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    private String replacePlaceHolder(String content, String placeHolder, JSONArray imgUrlList){
        StringBuffer contentBuffer=new StringBuffer(content);
        int index=contentBuffer.indexOf(placeHolder);
        String imgItem1="<img style=\"max-width:80%; height:auto;\" src=\"";
        String imgItem2="\"/>";
        int imgIndex=0;
        while(index>=0){
            contentBuffer.replace(index, index+placeHolder.length(), imgItem1+(imgIndex<imgUrlList.size()?imgUrlList.getString(imgIndex).replace("localhost", "10.0.2.2"):"")+imgItem2);
            index=contentBuffer.indexOf(placeHolder);
            imgIndex++;
        }
        return contentBuffer.toString();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


        detailToolbar=findViewById(R.id.news_detail_toolbar);
        newsTitle=findViewById(R.id.news_detail_title);
        newsTime=findViewById(R.id.news_detail_time);
        newsContent=findViewById(R.id.news_detail_content);
        newsContent.getSettings().setBlockNetworkImage(false);

        newsDesc=findViewById(R.id.news_detail_desc);

        news_id=getIntent().getIntExtra("newsId", 0);
        type=getIntent().getStringExtra("type");
        detailToolbar = findViewById(R.id.news_detail_toolbar);
        if(type.equals("news")){
            detailToolbar.setTitle("新闻");
        }else if(type.equals("dynamic")){
            detailToolbar.setTitle("动态");
        }else if(type.equals("notice")){
            detailToolbar.setTitle("公告");
            newsTitle.setGravity(Gravity.LEFT);
        }
        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        if(news_id==0){
            Toast.makeText(NewsDetailActivity.this, "传入的参数有误", Toast.LENGTH_LONG).show();
            finish();
        }

        HttpUtil.get("/index/getNewsDetail?id=" + news_id, new Callback() {
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
                    msg.what = SUCCESS;
                    newDetailJson=respondJson.getJSONObject("data");
                }
                handler.sendMessage(msg);
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
