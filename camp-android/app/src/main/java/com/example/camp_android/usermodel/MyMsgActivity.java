package com.example.camp_android.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.MsgDetailActivity;
import com.example.camp_android.R;
import com.example.camp_android.adapter.ListViewAdapter;
import com.example.camp_android.util.HttpUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyMsgActivity extends AppCompatActivity {
    // undo / unpass / done
    private String type;

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    private int currentPage, pageSize;
    //用来记录每次加载数据要指向哪一个位置
    private int itemPosition;

    private PullToRefreshListView listView;

    private ListViewAdapter listViewAdapter;

    private List<JSONObject> dataList;

    private Toolbar toolbar;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                if(listViewAdapter!=null){
                    listViewAdapter.frashDataList(dataList);
                }else {
                    listViewAdapter = new ListViewAdapter(MyMsgActivity.this, dataList);
                    listView.setAdapter(listViewAdapter);
                }
                listView.getRefreshableView().setSelection(itemPosition);
                currentPage++;
                itemPosition+=(dataList.size());
            }else if(msg.what==FAIL){
                Toast.makeText(MyMsgActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(MyMsgActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg_list);

        listView=findViewById(R.id.myMsgListView);

        toolbar=findViewById(R.id.my_msg_toolbar);
        toolbar.setTitle("消息中心");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        initData();
        loadNewData();

    }

    public void initData() {
        itemPosition = 0;
        currentPage = 1;
        pageSize = 7;
        if (dataList == null)
            dataList = new ArrayList<>();
        else
            dataList.clear();
        listViewAdapter = null;
        failInfo="";
        errorInfo="";
        successInfo="";
    }

    @Override
    protected void onStart() {
        super.onStart();

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        listView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新...");
        listView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开开始刷新...");
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载更多...");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在载入...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开开始加载...");

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                initData();
                loadNewData();
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.onRefreshComplete();
                    } },500);
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                loadNewData();
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.onRefreshComplete();
                    } },500);
                System.out.println("------------------------------currentPage:"+currentPage);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListViewAdapter.My_Msg_ViewHolder my_msg_viewHolder = (ListViewAdapter.My_Msg_ViewHolder)view.getTag();
                int msgId = my_msg_viewHolder.getMsgId();
                JSONObject json=new JSONObject();
                json.put("msgID", msgId);
                System.out.println(json);
                HttpUtil.post("/student/signAsRead", json, new Callback() {
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
                Intent msgDetailIntent = new Intent(MyMsgActivity.this, MsgDetailActivity.class);
                msgDetailIntent.putExtra("msgId", msgId);
                startActivityForResult(msgDetailIntent, 0);
            }
        });

    }



    private void loadNewData() {
        HttpUtil.get("/student/getMsgList"+"?pageSize="+pageSize+"&currentPage="+currentPage, new Callback() {
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
                    addArrayIntoList(dataList, respondJson.getJSONObject("data").getJSONArray("data"));
                    msg.what = SUCCESS;
                }
                handler.sendMessage(msg);
            }
        });
    }


    private void addArrayIntoList(List<JSONObject>dataList, JSONArray jsonArray){
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object=jsonArray.getJSONObject(i);
            object.put("activityType", "myMsg");
            dataList.add(object);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==0 && resultCode==1){
            initData();
            loadNewData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent();
                setResult(1);
                finish();
                break;
        }
        return true;
    }
}
