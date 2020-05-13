package com.example.camp_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.alibaba.fastjson.JSONObject;
import com.example.camp_android.adapter.ListViewAdapter;
import com.example.camp_android.config.SharedPreferencesConfig;
import com.example.camp_android.usermodel.LoginActivity;
import com.example.camp_android.usermodel.MyActivityActivity;
import com.example.camp_android.usermodel.MyCerActivity;
import com.example.camp_android.usermodel.MyExamActivity;
import com.example.camp_android.usermodel.MyMsgActivity;
import com.example.camp_android.usermodel.MyOrderActivity;
import com.example.camp_android.usermodel.MyTrainingActivity;
import com.example.camp_android.usermodel.MyVoteActivity;
import com.example.camp_android.usermodel.UserDetailActivity;
import com.example.camp_android.util.HttpUtil;
import com.example.camp_android.util.ToastUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final int SUCCESS=1;
    private final int FAIL=2;
    private final int ERROR=3;

    private String failInfo, errorInfo, successInfo;

    //nav的列表按钮一一识别
    private int navType;
    private final int USER_INFO=1;
    private final int USER_CER=2;
    private final int USER_EXAM=3;
    private final int USER_ACTIVITY=4;
    private final int USER_VOTE=5;
    private final int USER_ORDER=6;
    private final int USER_TRANINING=7;
    private final int LOGOUT=8;
    private final int USER_MSG=9;
    private final int UPDATE_MSG_NUM=10;

    private int messageUnReadNum;

    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private FrameLayout home_frame_layout;
    private DrawerLayout mDrawerLayout;

    private TextView userNameTextView;
    private TextView roleNameTextView;

    private NewsFragment newsFragment;
    private DynamicFragment dynamicFragment;
    private NoticeFragment noticeFragment;
    private TrainingFragment trainingFragment;

    private Fragment currentFragment;

    private FragmentTransaction transaction;
    private FragmentManager manager;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==SUCCESS){
                Intent intent;
                switch (navType){
                    case USER_INFO:
                        intent = new Intent(MainActivity.this, UserDetailActivity.class);
                        startActivity(intent);
                        break;
                    case USER_CER:
                        intent = new Intent(MainActivity.this, MyCerActivity.class);
                        startActivity(intent);
                        break;
                    case USER_TRANINING:
                        intent = new Intent(MainActivity.this, MyTrainingActivity.class);
                        startActivity(intent);
                        break;
                    case USER_EXAM:
                        intent = new Intent(MainActivity.this, MyExamActivity.class);
                        startActivity(intent);
                        break;
                    case USER_ACTIVITY:
                        intent = new Intent(MainActivity.this, MyActivityActivity.class);
                        startActivity(intent);
                        break;
                    case USER_VOTE:
                        intent = new Intent(MainActivity.this, MyVoteActivity.class);
                        startActivity(intent);
                        break;
                    case USER_ORDER:
                        intent = new Intent(MainActivity.this, MyOrderActivity.class);
                        startActivity(intent);
                        break;
                    case USER_MSG:
                        intent = new Intent(MainActivity.this, MyMsgActivity.class);
                        startActivityForResult(intent, 1);
                        break;
                    case LOGOUT:
                        Toast.makeText(MainActivity.this, successInfo, Toast.LENGTH_LONG).show();
                        userNameTextView.setText("请登录");
                        roleNameTextView.setText("");
                        break;
                    case UPDATE_MSG_NUM:
                        MenuItem menuItem = navigationView.getMenu().findItem(R.id.nav_message);
                        menuItem.setTitle("消息中心  ("+messageUnReadNum+")");
                        break;
                }
            }else if(msg.what==FAIL){
                Toast.makeText(MainActivity.this, failInfo, Toast.LENGTH_LONG).show();
            } else if(msg.what==ERROR){
                Toast.makeText(MainActivity.this, errorInfo, Toast.LENGTH_LONG).show();
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(loginIntent, 0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navType=0;
        toolbar = findViewById(R.id.toolbar);

        //获取抽屉布局实例
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //获取菜单控件示例
        navigationView = (NavigationView) findViewById(R.id.nav_design);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //无法直接通过findViewById方法获取header布局id
        View v = navigationView.getHeaderView(0);

//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        home_frame_layout = (FrameLayout) findViewById(R.id.home_frame_layout);

        userNameTextView = v.findViewById(R.id.text_name);
        roleNameTextView = v.findViewById(R.id.text_role_name);

        SharedPreferencesConfig.init(this);

        newsFragment=new NewsFragment();
        noticeFragment=new NoticeFragment();
        trainingFragment=new TrainingFragment();
        dynamicFragment=new DynamicFragment();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //设置默认的Fragment
        transaction.replace(R.id.home_frame_layout, newsFragment);
        transaction.commit();
        currentFragment=newsFragment;

        messageUnReadNum = -1;

    }


    public void checkIfHasLogin(final int type){
        HttpUtil.get("/student/getUserInfo", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resBodyStr=response.body().string();
                Message msg = new Message();
                JSONObject res=JSONObject.parseObject(resBodyStr);
                if(res.getString("code").equals("error")){
                    msg.what=ERROR;
                    errorInfo=res.getString("msg");
                    handler.sendMessage(msg);
                }else {
                    msg.what=SUCCESS;
                    navType=type;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        toolbar.setTitle("协会新闻");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //通过HomeAsUp来让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置Indicator来添加一个点击图标（默认图标是一个返回的箭头）
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //设置默认选中第一个
        navigationView.setCheckedItem(R.id.nav_info);
        //设置菜单项的监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //逻辑页面处理
//                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_info:
                        checkIfHasLogin(USER_INFO);
                        break;
                    case R.id.nav_certificate:
                        checkIfHasLogin(USER_CER);
                        break;
                    case R.id.nav_training:
                        checkIfHasLogin(USER_TRANINING);
                        break;
                    case R.id.nav_exam:
                        checkIfHasLogin(USER_EXAM);
                        break;
                    case R.id.nav_activity:
                        checkIfHasLogin(USER_ACTIVITY);
                        break;
                    case R.id.nav_vote:
                        checkIfHasLogin(USER_VOTE);
                        break;
                    case R.id.nav_order:
                        checkIfHasLogin(USER_ORDER);
                        break;
                    case R.id.nav_message:
                        checkIfHasLogin(USER_MSG);
                        break;
                    case R.id.nav_logout:
                        AlertDialog isExit = new AlertDialog.Builder(MainActivity.this).create();
                        isExit.setMessage("确定要退出么？");
                        isExit.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                HttpUtil.get("/logout", new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        e.printStackTrace();
                                    }
                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String resBodyStr = response.body().string();
                                        Message msg = new Message();
                                        JSONObject res = JSONObject.parseObject(resBodyStr);
                                        if (res.getString("code").equals("error")) {
                                            msg.what = ERROR;
                                            errorInfo = res.getString("msg");
                                            handler.sendMessage(msg);
                                        } else {
                                            System.out.println("---------------------1");
                                            msg.what = SUCCESS;
                                            navType = LOGOUT;
                                            successInfo = res.getString("msg");
                                            handler.sendMessage(msg);
                                        }
                                    }
                                });
                            }
                        });
                        isExit.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.out.println("取消登出");
                            }
                        });
                        isExit.show();
                        break;
                    default:
                }
                return true;
            }
        });


        //设置底部菜单栏的监听
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_news);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_nav_news:
                        toolbar.setTitle("协会新闻");
                        switchContent(newsFragment);
                        break;
                    case R.id.bottom_nav_dynamic:
                        toolbar.setTitle("协会动态");
                        switchContent(dynamicFragment);
                        break;
                    case R.id.bottom_nav_notice:
                        toolbar.setTitle("协会公告");
                        switchContent(noticeFragment);
                        break;
                    case R.id.bottom_nav_training:
                        toolbar.setTitle("协会培训");
                        switchContent(trainingFragment);
                        break;
                    default:
                }
                return true;
            }
        });
        HttpUtil.get("/student/getUnReadMsgNum", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resBodyStr=response.body().string();
                System.out.println(resBodyStr);
                JSONObject res=JSONObject.parseObject(resBodyStr);
                if(res.getString("code")!=null && res.getString("code").equals("success")) {
                    messageUnReadNum = res.getInteger("data");
                    MenuItem menuItem = navigationView.getMenu().findItem(R.id.nav_message);
                    menuItem.setTitle("消息中心  ("+messageUnReadNum+")");
                }
            }
        });
    }
    public void switchContent(Fragment to) {
        if (currentFragment != to) {
            FragmentTransaction transaction = manager.beginTransaction();
            if (!to.isAdded()) {	// 先判断是否被add过
                transaction.hide(currentFragment).add(R.id.home_frame_layout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(currentFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            currentFragment = to;
        }
    }

    //监听标题栏的菜单item的选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //R.id.home修改导航按钮的点击事件为打开侧滑
            case android.R.id.home:
                //打开侧滑栏，注意要与xml中保持一致START
                mDrawerLayout.openDrawer(GravityCompat.START);
            default:
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==0 && resultCode==22){
            //更新用户名
            String userName=data.getStringExtra("userName");
            System.out.println(userName);
            if (userName != null) {
                userNameTextView.setText(userName);
                roleNameTextView.setText("学员");
            }
        }else if(requestCode==1 && resultCode==1){
            HttpUtil.get("/student/getUnReadMsgNum", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resBodyStr=response.body().string();
                    System.out.println(resBodyStr);
                    JSONObject res=JSONObject.parseObject(resBodyStr);
                    if(res.getString("code")!=null && res.getString("code").equals("success")) {
                        messageUnReadNum = res.getInteger("data");
                        Message message=new Message();
                        message.what=SUCCESS;
                        navType=UPDATE_MSG_NUM;
                        handler.sendMessage(message);
                    }
                }
            });
        }
        HttpUtil.get("/student/getUnReadMsgNum", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resBodyStr=response.body().string();
                System.out.println(resBodyStr);
                JSONObject res=JSONObject.parseObject(resBodyStr);
                if(res.getString("code")!=null && res.getString("code").equals("success")) {
                    messageUnReadNum = res.getInteger("data");
                    Message message=new Message();
                    message.what=SUCCESS;
                    navType=UPDATE_MSG_NUM;
                    handler.sendMessage(message);
                }
            }
        });
        super.onActivityResult(requestCode, resultCode, data);
    }

}
