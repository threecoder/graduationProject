package com.lutayy.campbackend.service.SQLConn;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.pojo.Training;
import com.lutayy.campbackend.pojo.Vote;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class VoteSQLConn {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name = "root";
    private static final String Pwd = "123456";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    //会员role=0，学员role=1
    public static int countHasVoted(int role, String name, Integer isFinish,int id) {
        int sum = 0;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            if (role == 0) {
                sql = "select count(distinct v.vote_id) c from vote v inner join vote_option_member om on v.vote_id=om.vote_id where om.member_key_id="+id;
            } else {
                sql = "select count(distinct v.vote_id) c from vote v inner join vote_option_student os on v.vote_id=os.vote_id where os.student_id="+id;;
            }
            if (name != null) {
                sql += (" and v.vote_content like '%" + name + "%' ");
            }
            if (isFinish != null) {
                if(isFinish.equals(0)){
                    sql += (" and v.end_time>'" + simpleDateFormat.format(new Date()) + "'");
                }else {
                    sql += (" and v.end_time<'" + simpleDateFormat.format(new Date()) + "'");
                }
            }
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                sum = rs.getInt("c");
            }
            return sum;

        } catch (SQLException e) {
            e.printStackTrace();
            return sum;
        }
    }

    public static List<Vote> getHasVotedList(int role, String name, Integer isFinish, int id, Integer currentPage, Integer pageSize) {
        List<Vote> voteList=new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            if (role == 0) {
                sql = "select distinct v.* from vote v inner join vote_option_member om on v.vote_id=om.vote_id where om.member_key_id="+id;
            } else {
                sql = "select distinct v.* from vote v inner join vote_option_student os on v.vote_id=os.vote_id where os.student_id="+id;;
            }
            if (name != null) {
                sql += (" and v.vote_content like '%" + name + "%' ");
            }
            if (isFinish != null) {
                if(isFinish.equals(0)){
                    sql += (" and v.end_time>'" + simpleDateFormat.format(new Date()) + "'");
                }else {
                    sql += (" and v.end_time<'" + simpleDateFormat.format(new Date()) + "'");
                }
            }
            sql+=(" limit "+(currentPage-1)+","+pageSize);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Vote vote=new Vote();
                vote.setVoteId(rs.getInt("vote_id"));
                vote.setVoteContent(rs.getString("vote_content"));
//                vote.setOptionalNum(rs.getByte("optional_num"));
//                vote.setOptionalSum(rs.getByte("optional_sum"));
                vote.setVoteType(rs.getByte("vote_type"));
                vote.setEndTime(rs.getTimestamp("end_time"));
                voteList.add(vote);
            }
            return voteList;

        } catch (SQLException e) {
            e.printStackTrace();
            return voteList;
        }
    }
}
