package com.example.camp_android.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.NewsDetailActivity;
import com.example.camp_android.R;
import com.example.camp_android.TrainingDetailActivity;
import com.example.camp_android.adapter.ListViewAdapter;
import com.example.camp_android.util.HttpUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class MyTrainingActivity extends AppCompatActivity {

    // joinable / signed (可报名/已报名)
    private String type;

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    //分页查询参数，每页显示10条新闻数据
    private int currentPage, pageSize;
    //用来记录每次加载数据要指向哪一个位置
    private int itemPosition;

    private PullToRefreshListView listView;

    private ListViewAdapter listViewAdapter;

    private List<JSONObject> dataList;

    private Toolbar detailToolbar;

    //顶部三个按钮
    private RadioGroup trainingTap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                if(listViewAdapter!=null){
                    listViewAdapter.frashDataList(dataList);
                }else {
                    listViewAdapter = new ListViewAdapter(MyTrainingActivity.this, dataList);
                    listView.setAdapter(listViewAdapter);
                }
                listView.getRefreshableView().setSelection(itemPosition);
                currentPage++;
                itemPosition+=(dataList.size());
            }else if(msg.what==FAIL){
                Toast.makeText(MyTrainingActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(MyTrainingActivity.this, errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_training);

        listView=findViewById(R.id.myTrainingListView);

        trainingTap=findViewById(R.id.my_training_tap);

        detailToolbar=findViewById(R.id.my_training_toolbar);
        detailToolbar.setTitle("协会培训");

        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        initData();
        type="joinable";
        loadNewData();

    }

    public void initData() {
        itemPosition = 0;
        currentPage = 1;
        pageSize = 6;
        if (dataList == null)
            dataList = new ArrayList<>();
        else
            dataList.clear();
        listViewAdapter = null;
        failInfo="";
        errorInfo="";
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

        trainingTap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                System.out.println("--------------------------"+type);
                switch (i){
                    case R.id.joinable_training:
                        if(!type.equals("joinable")){
                            type="joinable";
                            initData();
                            loadNewData();
                        }
                        break;
                    case R.id.signed_training:
                        if(!type.equals("signed")){
                            type="signed";
                            initData();
                            loadNewData();
                        }
                        break;
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListViewAdapter.My_Training_ViewHolder my_training_viewHolder = (ListViewAdapter.My_Training_ViewHolder)view.getTag();
                Intent trainingDetailIntent = new Intent(MyTrainingActivity.this, TrainingDetailActivity.class);
                trainingDetailIntent.putExtra("trainingId", my_training_viewHolder.getTraining_id());
                trainingDetailIntent.putExtra("trainingName", my_training_viewHolder.getMyTrainingName().getText());
                trainingDetailIntent.putExtra("trainingDesc", my_training_viewHolder.getMyTrainingDesc().getText());
                trainingDetailIntent.putExtra("trainingTime", my_training_viewHolder.getMyTrainingTime().getText());
                trainingDetailIntent.putExtra("trainingAddress", my_training_viewHolder.getMyTrainingAddress().getText());
                trainingDetailIntent.putExtra("trainingFee", my_training_viewHolder.getMyTrainingFee().getText());
                trainingDetailIntent.putExtra("trainingContact", my_training_viewHolder.getContacts());
                trainingDetailIntent.putExtra("trainingPhone", my_training_viewHolder.getPhone());
                trainingDetailIntent.putExtra("type", type);
                startActivity(trainingDetailIntent);
            }
        });

    }



    private void loadNewData() {
        String url;
        if(type.equals("joinable")){
            url="/student/getJoinableTraining";
        }else {
            url="/student/getSignedTraining";
        }

        HttpUtil.get(url+"?pageSize="+pageSize+"&currentPage="+currentPage, new Callback() {
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
                    addArrayIntoList(dataList, respondJson.getJSONObject("data").getJSONArray("list"));
                    msg.what = SUCCESS;
                }
                handler.sendMessage(msg);
            }
        });
    }


    private void addArrayIntoList(List<JSONObject>dataList, JSONArray jsonArray){
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object=jsonArray.getJSONObject(i);
            object.put("activityType", "myTraining");
            dataList.add(object);
        }
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