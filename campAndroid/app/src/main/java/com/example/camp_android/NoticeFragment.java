package com.example.camp_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.adapter.ListViewAdapter;
import com.example.camp_android.util.HttpUtil;
import com.example.camp_android.util.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NoticeFragment extends Fragment {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo;

    //分页查询参数，每页显示10条新闻数据
    private int currentPage, pageSize;
    //用来记录每次加载数据要指向哪一个位置
    private int itemPosition;

    private PullToRefreshListView listView;

    private ListViewAdapter listViewAdapter;

    private List<JSONObject> dataList;

    //每一个Fragment页面都有一个浮动按钮，用于快速回到顶部
    private FloatingActionButton fab;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                if(listViewAdapter!=null){
                    listViewAdapter.frashDataList(dataList);
                    listViewAdapter.notifyDataSetChanged();
                }else {
                    listViewAdapter = new ListViewAdapter(getActivity(), dataList);
                    listView.setAdapter(listViewAdapter);
                }
                listView.getRefreshableView().setSelection(itemPosition);
                currentPage++;
                itemPosition+=(dataList.size());
            }else if(msg.what==FAIL){
                Toast.makeText(getActivity(), failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(getActivity(), errorInfo, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initData(){
        itemPosition=0;
        currentPage=1;
        pageSize=8;
        if (dataList == null)
            dataList = new ArrayList<>();
        else
            dataList.clear();
        listViewAdapter=null;
        failInfo="";
        errorInfo="";
    }

    //每次创建，绘制该Fragment的View组件时回调，将显示的View返回
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_list,  container, false);
        listView=(PullToRefreshListView) view.findViewById(R.id.noticeListView);
        fab=(FloatingActionButton) view.findViewById(R.id.notice_list_fab);
        //初始化数据
        initData();
        loadNewData();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //点击回到顶部
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.getRefreshableView().smoothScrollToPosition(0);
            }
        });

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
                ListViewAdapter.Notice_ViewHolder notice_viewHolder = (ListViewAdapter.Notice_ViewHolder)view.getTag();
                int news_id = notice_viewHolder.getNews_id();
                Intent newsDetailIntent = new Intent(getContext(), NewsDetailActivity.class);
                newsDetailIntent.putExtra("newsId", news_id);
                newsDetailIntent.putExtra("type", "notice");
                startActivity(newsDetailIntent);
            }
        });

    }

    private void loadNewData() {
        HttpUtil.get("/index/getNoticeList?pageSize="+pageSize+"&currentPage="+currentPage, new Callback() {
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
                    if(respondJson.getJSONObject("data").getJSONArray("list").size()==0){
                        msg.what = FAIL;
                        failInfo="已加载到底部";
                    }
                }
                handler.sendMessage(msg);
            }
        });
    }


    private void addArrayIntoList(List<JSONObject>dataList, JSONArray jsonArray){
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object=jsonArray.getJSONObject(i);
            object.put("activityType", "notice");
            dataList.add(object);
        }
    }
}
