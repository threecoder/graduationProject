package com.example.camp_android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.camp_android.R;
import com.example.camp_android.usermodel.MyActivityActivity;


import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import androidx.viewpager.widget.ViewPager;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<JSONObject> dataList;
    //设置种类
    private final int NEWS = 1;
    private final int DYNAMIC = 2;
    private final int NOTICE = 3;
    private final int TRAINING = 4;
    private final int MY_TRAINING = 5;
    private final int MY_ACTIVITY = 6;
    private final int MY_EXAM = 7;
    private final int MY_ORDER = 8;
    private final int MY_VOTE = 9;
    private final int MY_CER = 10;
    private final int MY_MSG = 11;

    public void frashDataList(List<JSONObject> dataList){
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    public ListViewAdapter(Context context, List<JSONObject> dataList){
        this.context=context;
        this.dataList=dataList;
    }

    @Override
    public int getCount() {
        return dataList==null?0:dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position).getString("activityType").equals("news"))
            return NEWS;
        else if(dataList.get(position).getString("activityType").equals("dynamic"))
            return DYNAMIC;
        else if(dataList.get(position).getString("activityType").equals("notice"))
            return NOTICE;
        else if(dataList.get(position).getString("activityType").equals("training"))
            return TRAINING;
        else if(dataList.get(position).getString("activityType").equals("myTraining"))
            return MY_TRAINING;
        else if(dataList.get(position).getString("activityType").equals("myActivity"))
            return MY_ACTIVITY;
        else if(dataList.get(position).getString("activityType").equals("myExam"))
            return MY_EXAM;
        else if(dataList.get(position).getString("activityType").equals("myOrder"))
            return MY_ORDER;
        else if(dataList.get(position).getString("activityType").equals("myVote"))
            return MY_VOTE;
        else if(dataList.get(position).getString("activityType").equals("myCer"))
            return MY_CER;
        else if(dataList.get(position).getString("activityType").equals("myMsg"))
            return MY_MSG;
        else
            return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == NEWS || getItemViewType(i) == DYNAMIC) {
            News_ViewHolder news_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.news_item, null);
                news_viewHolder = new News_ViewHolder(view);
                view.setTag(news_viewHolder);
            } else {
                news_viewHolder = (News_ViewHolder) view.getTag();
            }
            news_viewHolder.news_id=dataList.get(i).getInteger("id");
            news_viewHolder.newsName.setText(dataList.get(i).getString("title"));
            news_viewHolder.newsDesc.setText(dataList.get(i).getString("desc"));
            news_viewHolder.newsTime.setText(dataList.get(i).getString("date"));
            Glide.with(context)
                    .load(dataList.get(i).getString("url") == null ? null : dataList.get(i).getString("url").replace("localhost", "10.0.2.2"))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(news_viewHolder.img);
        } else if (getItemViewType(i) == NOTICE) {
            Notice_ViewHolder notice_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.notice_item, null);
                notice_viewHolder = new Notice_ViewHolder(view);
                view.setTag(notice_viewHolder);
            } else {
                notice_viewHolder = (Notice_ViewHolder) view.getTag();
            }
            notice_viewHolder.news_id=dataList.get(i).getInteger("id");
            notice_viewHolder.noticeName.setText(dataList.get(i).getString("title"));
            notice_viewHolder.noticeTime.setText(dataList.get(i).getString("date"));
        } else if(getItemViewType(i)==TRAINING){
            Training_ViewHolder training_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.training_item, null);
                training_viewHolder = new Training_ViewHolder(view);
                view.setTag(training_viewHolder);
            } else {
                training_viewHolder = (Training_ViewHolder) view.getTag();
            }
            training_viewHolder.trainingName.setText(dataList.get(i).getString("title"));
            training_viewHolder.trainingDesc.setText(dataList.get(i).getString("abstract"));
            training_viewHolder.trainingLevel.setText(dataList.get(i).getString("level"));
            training_viewHolder.trainingNums.setText(dataList.get(i).getString("viewers"));
            training_viewHolder.trainingTime.setText(dataList.get(i).getString("postTime"));
        } else if(getItemViewType(i)==MY_TRAINING){
            My_Training_ViewHolder my_training_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_training_item, null);
                my_training_viewHolder = new My_Training_ViewHolder(view);
                view.setTag(my_training_viewHolder);
            } else {
                my_training_viewHolder = (My_Training_ViewHolder) view.getTag();
            }
            my_training_viewHolder.myTrainingName.setText(dataList.get(i).getString("name"));
            StringBuffer desc=new StringBuffer();
            desc.append("\t");
            JSONArray descArray = dataList.get(i).getJSONArray("introduction");
            for(int o=0;o<descArray.size();o++) {
                desc.append(descArray.getString(o)+"\n\t");
            }
            my_training_viewHolder.myTrainingDesc.setText(desc.toString());
            my_training_viewHolder.myTrainingTime.setText(dataList.get(i).getString("date"));
            my_training_viewHolder.myTrainingAddress.setText(dataList.get(i).getString("address"));
            my_training_viewHolder.myTrainingFee.setText(dataList.get(i).getString("fee"));
            my_training_viewHolder.training_id = dataList.get(i).getInteger("id");
            my_training_viewHolder.contacts = dataList.get(i).getString("contacts");
            my_training_viewHolder.phone = dataList.get(i).getString("phone");
        } else if(getItemViewType(i)==MY_ACTIVITY){
            My_Activity_ViewHolder my_activity_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_activity_item, null);
                my_activity_viewHolder = new My_Activity_ViewHolder(view);
                view.setTag(my_activity_viewHolder);
            } else {
                my_activity_viewHolder = (My_Activity_ViewHolder) view.getTag();
            }
            my_activity_viewHolder.myActivityName.setText(dataList.get(i).getString("name"));
            StringBuffer desc=new StringBuffer();
            desc.append("\t");
            JSONArray descArray = dataList.get(i).getJSONArray("introduce");
            for(int o=0;o<descArray.size();o++) {
                desc.append(descArray.getString(o)+"\n\t");
            }
            my_activity_viewHolder.myActivityDesc.setText(desc.toString());
            my_activity_viewHolder.myActivityTime.setText(dataList.get(i).getString("date"));
            my_activity_viewHolder.myActivityAddress.setText(dataList.get(i).getString("address"));
            my_activity_viewHolder.myActivityFee.setText(dataList.get(i).getString("fee"));
            my_activity_viewHolder.activity_id = dataList.get(i).getInteger("id");
            my_activity_viewHolder.contacts = dataList.get(i).getString("contacts");
            my_activity_viewHolder.phone = dataList.get(i).getString("phone");
        }else if(getItemViewType(i)==MY_EXAM){
            My_Exam_ViewHolder my_exam_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_exam_item, null);
                my_exam_viewHolder = new My_Exam_ViewHolder(view);
                view.setTag(my_exam_viewHolder);
            } else {
                my_exam_viewHolder = (My_Exam_ViewHolder) view.getTag();
            }
            my_exam_viewHolder.examId = dataList.get(i).getInteger("examId");
            my_exam_viewHolder.myExamName.setText(dataList.get(i).getString("examName"));
            my_exam_viewHolder.myExamLength.setText(dataList.get(i).getString("min")+"分钟");
            my_exam_viewHolder.myExamTime.setText(dataList.get(i).getString("startTime")+"----"+dataList.get(i).getString("endTime"));
            my_exam_viewHolder.myExamTrainingName.setText(dataList.get(i).getString("belong"));
            my_exam_viewHolder.myExamGrade.setText(dataList.get(i).getString("grade"));
        } else if(getItemViewType(i)==MY_ORDER){
            My_Order_ViewHolder my_order_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_order_item, null);
                my_order_viewHolder = new My_Order_ViewHolder(view);
                view.setTag(my_order_viewHolder);
            } else {
                my_order_viewHolder = (My_Order_ViewHolder) view.getTag();
            }
            my_order_viewHolder.orderNum = dataList.get(i).getString("orderNum");
            my_order_viewHolder.orderType = dataList.get(i).getString("orderType");
            my_order_viewHolder.myOrderNum.setText(dataList.get(i).getString("orderNum"));
            my_order_viewHolder.myOrderType.setText(dataList.get(i).getString("orderType"));
            my_order_viewHolder.myOrderMan.setText(dataList.get(i).getString("builder"));
            my_order_viewHolder.myOrderBussiness.setText(dataList.get(i).getString("businessName"));
            my_order_viewHolder.myOrderCreateTime.setText(dataList.get(i).getString("buildTime"));
            my_order_viewHolder.myOrderEndTime.setText(dataList.get(i).getString("endTime"));
            my_order_viewHolder.myOrderPayTime.setText(dataList.get(i).getString("payTime"));
            my_order_viewHolder.myOrderFee.setText(dataList.get(i).getString("price"));
            my_order_viewHolder.myOrderStatus.setText(dataList.get(i).getString("status"));
            if(dataList.get(i).getString("status").equals("已支付") || dataList.get(i).getString("status").equals("订单关闭")){
                my_order_viewHolder.canPayTag.setVisibility(View.GONE);
                my_order_viewHolder.clickable=false;
            }else {
                my_order_viewHolder.clickable=true;
            }
        }
        else if(getItemViewType(i)==MY_VOTE){
            My_Vote_ViewHolder my_vote_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_vote_item, null);
                my_vote_viewHolder = new My_Vote_ViewHolder(view);
                view.setTag(my_vote_viewHolder);
            } else {
                my_vote_viewHolder = (My_Vote_ViewHolder) view.getTag();
            }
            my_vote_viewHolder.voteId = dataList.get(i).getInteger("id");
            my_vote_viewHolder.myVoteName.setText(dataList.get(i).getString("name"));
            my_vote_viewHolder.myVoteType.setText(dataList.get(i).getString("type"));
            my_vote_viewHolder.myVoteEndTime.setText(dataList.get(i).getString("time"));
        }
        else if(getItemViewType(i)==MY_CER){
            My_Cer_ViewHolder my_cer_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_certificate_item, null);
                my_cer_viewHolder = new My_Cer_ViewHolder(view);
                view.setTag(my_cer_viewHolder);
            } else {
                my_cer_viewHolder = (My_Cer_ViewHolder) view.getTag();
            }
            my_cer_viewHolder.cerId = dataList.get(i).getInteger("cerId");
            my_cer_viewHolder.cerName.setText(dataList.get(i).getString("cerName"));
            my_cer_viewHolder.cerTraining.setText(dataList.get(i).getString("trainingName"));
            my_cer_viewHolder.cerBeginTime.setText(dataList.get(i).getString("beginTime"));
            my_cer_viewHolder.cerEndTime.setText(dataList.get(i).getString("endTime"));
            if(dataList.get(i).getString("aORi").equals("invalid")){
                my_cer_viewHolder.cerName.setTextColor(Color.parseColor("#808080"));
            }
        }
        else if(getItemViewType(i)==MY_MSG){
            My_Msg_ViewHolder my_msg_viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.my_message_item, null);
                my_msg_viewHolder = new My_Msg_ViewHolder(view);
                view.setTag(my_msg_viewHolder);
            } else {
                my_msg_viewHolder = (My_Msg_ViewHolder) view.getTag();
            }
            my_msg_viewHolder.msgId=dataList.get(i).getInteger("id");
            my_msg_viewHolder.myMsgName.setText(dataList.get(i).getString("title"));
            my_msg_viewHolder.myMsgContent.setText(dataList.get(i).getString("shortMsg")+"...");
            my_msg_viewHolder.myMsgTime.setText(dataList.get(i).getString("time"));
            my_msg_viewHolder.myMsgType.setText(dataList.get(i).getString("type"));
            String read = dataList.get(i).getString("read");
            my_msg_viewHolder.myMsgReadOrNot.setText(read);
            if(read.equals("已读"))
                my_msg_viewHolder.myMsgReadOrNot.setTextColor(Color.parseColor("#808080"));
        }
        return view;
    }


    // news 和 dynamic 都用这个
    public final class News_ViewHolder{
        ImageView img;
        TextView newsName, newsDesc, newsTime;
        int news_id;

        News_ViewHolder(View view){
            img=(ImageView) view.findViewById(R.id.news_image);
            newsName=(TextView) view.findViewById(R.id.news_name);
            newsDesc=(TextView) view.findViewById(R.id.news_desc);
            newsTime=(TextView) view.findViewById(R.id.news_time);
        }

        public int getNews_id() {
            return news_id;
        }

    }
    public final class Notice_ViewHolder{
        TextView noticeName, noticeTime;
        int news_id;

        Notice_ViewHolder(View view){
            noticeName=(TextView) view.findViewById(R.id.notice_name);
            noticeTime=(TextView) view.findViewById(R.id.notice_time);
        }
        public int getNews_id() {
            return news_id;
        }
    }

    private final class Training_ViewHolder{
        TextView trainingName, trainingDesc, trainingLevel, trainingNums, trainingTime;
        int trainingId;

        Training_ViewHolder(View view){
            trainingName=(TextView) view.findViewById(R.id.training_name);
            trainingDesc=(TextView) view.findViewById(R.id.training_desc);
            trainingLevel=(TextView) view.findViewById(R.id.training_level);
            trainingNums=(TextView) view.findViewById(R.id.training_nums);
            trainingTime=(TextView) view.findViewById(R.id.training_time);
        }
        public int getTrainingId() {
            return trainingId;
        }
    }

    public final class My_Training_ViewHolder{
        TextView myTrainingName, myTrainingDesc, myTrainingTime, myTrainingAddress, myTrainingFee;
        int training_id;
        String contacts, phone;

        My_Training_ViewHolder(View view){
            myTrainingName=(TextView) view.findViewById(R.id.my_training_name);
            myTrainingDesc=(TextView) view.findViewById(R.id.my_training_desc);
            myTrainingTime=(TextView) view.findViewById(R.id.my_training_time);
            myTrainingAddress=(TextView) view.findViewById(R.id.my_training_address);
            myTrainingFee=(TextView) view.findViewById(R.id.my_training_fee);
        }
        public int getTraining_id() {
            return training_id;
        }

        public TextView getMyTrainingName() {
            return myTrainingName;
        }

        public TextView getMyTrainingDesc() {
            return myTrainingDesc;
        }

        public TextView getMyTrainingTime() {
            return myTrainingTime;
        }

        public TextView getMyTrainingAddress() {
            return myTrainingAddress;
        }

        public TextView getMyTrainingFee() {
            return myTrainingFee;
        }

        public String getContacts() {
            return contacts;
        }

        public String getPhone() {
            return phone;
        }
    }

    public final class My_Activity_ViewHolder{
        TextView myActivityName, myActivityDesc, myActivityTime, myActivityAddress, myActivityFee;
        int activity_id;
        String contacts, phone;

        My_Activity_ViewHolder(View view){
            myActivityName=(TextView) view.findViewById(R.id.my_activity_name);
            myActivityDesc=(TextView) view.findViewById(R.id.my_activity_desc);
            myActivityTime=(TextView) view.findViewById(R.id.my_activity_time);
            myActivityAddress=(TextView) view.findViewById(R.id.my_activity_address);
            myActivityFee=(TextView) view.findViewById(R.id.my_activity_fee);
        }

        public TextView getMyActivityName() {
            return myActivityName;
        }

        public TextView getMyActivityDesc() {
            return myActivityDesc;
        }

        public TextView getMyActivityTime() {
            return myActivityTime;
        }

        public TextView getMyActivityAddress() {
            return myActivityAddress;
        }

        public TextView getMyActivityFee() {
            return myActivityFee;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public String getContacts() {
            return contacts;
        }

        public String getPhone() {
            return phone;
        }
    }

    public final class My_Exam_ViewHolder {
        TextView myExamName, myExamLength, myExamTime, myExamTrainingName, myExamGrade;
        int examId;

        My_Exam_ViewHolder(View view){
            myExamName = (TextView) view.findViewById(R.id.my_exam_name);
            myExamLength = (TextView) view.findViewById(R.id.my_exam_length);
            myExamTime = (TextView) view.findViewById(R.id.my_exam_time);
            myExamTrainingName = (TextView) view.findViewById(R.id.my_exam_training_name);
            myExamGrade = (TextView) view.findViewById(R.id.my_exam_grade);
        }
    }

    public final class My_Order_ViewHolder {
        String orderNum, orderType;
        TextView myOrderNum, myOrderType, myOrderMan, myOrderBussiness, myOrderCreateTime, myOrderEndTime, myOrderPayTime, myOrderFee, myOrderStatus;
        TextView canPayTag;
        boolean clickable;
        My_Order_ViewHolder(View view){
            myOrderNum = (TextView) view.findViewById(R.id.my_order_num);
            myOrderType = (TextView) view.findViewById(R.id.my_order_type);
            myOrderMan = (TextView) view.findViewById(R.id.my_order_man);
            myOrderBussiness = (TextView) view.findViewById(R.id.my_order_bussiness);
            myOrderCreateTime = (TextView) view.findViewById(R.id.my_order_create_time);
            myOrderEndTime = (TextView) view.findViewById(R.id.my_order_end_time);
            myOrderPayTime = (TextView) view.findViewById(R.id.my_order_pay_time);
            myOrderFee = (TextView) view.findViewById(R.id.my_order_fee);
            myOrderStatus = (TextView) view.findViewById(R.id.my_order_status);
            canPayTag = (TextView) view.findViewById(R.id.can_pay_tag);
        }

        public boolean isClickable() {
            return clickable;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public String getOrderType() {
            return orderType;
        }
    }

    public final class My_Vote_ViewHolder{
        int voteId;
        TextView myVoteName, myVoteType, myVoteEndTime;
        My_Vote_ViewHolder(View view){
            myVoteName = (TextView) view.findViewById(R.id.my_vote_name);
            myVoteType = (TextView) view.findViewById(R.id.my_vote_type);
            myVoteEndTime = (TextView) view.findViewById(R.id.my_vote_end_time);
        }

        public int getVoteId() {
            return voteId;
        }
    }

    public final class My_Cer_ViewHolder{
        int cerId;
        TextView cerName, cerTraining, cerBeginTime, cerEndTime;
        My_Cer_ViewHolder(View view){
            cerName = (TextView) view.findViewById(R.id.my_cer_name);
            cerTraining = (TextView) view.findViewById(R.id.my_cer_training);
            cerBeginTime = (TextView) view.findViewById(R.id.my_cer_begin_time);
            cerEndTime = (TextView) view.findViewById(R.id.my_cer_end_time);
        }
        public int getCerId() {
            return cerId;
        }
    }

    public final class My_Msg_ViewHolder{
        int msgId;
        TextView myMsgName, myMsgContent, myMsgTime, myMsgType, myMsgReadOrNot;
        My_Msg_ViewHolder(View view){
            myMsgName = (TextView) view.findViewById(R.id.my_msg_name);
            myMsgContent = (TextView) view.findViewById(R.id.my_msg_content);
            myMsgTime = (TextView) view.findViewById(R.id.my_msg_time);
            myMsgType = (TextView) view.findViewById(R.id.my_msg_type);
            myMsgReadOrNot = (TextView) view.findViewById(R.id.my_msg_read_or_not);
        }
        public int getMsgId() {
            return msgId;
        }
    }
}
