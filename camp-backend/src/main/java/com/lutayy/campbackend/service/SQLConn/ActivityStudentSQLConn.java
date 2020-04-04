package com.lutayy.campbackend.service.SQLConn;

import com.lutayy.campbackend.pojo.ActivityStudent;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActivityStudentSQLConn {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name="root";
    private static final String Pwd="123456";

    /**
     * 获取 包含对应学员的 由会员提交的 未支付的有效的订单的 订单号
     **/
    public static String getActivityOrderByStudentId(int studentId, int activityId){

        String orderId=null;

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select distinct o.order_key_id from activity_order o,activity_order_student a " +
                    "where o.order_key_id=a.order_key_id and a.student_id=" + studentId +
                    " and o.payment_state=0 and o.close=0 and o.order_type=0 and o.activity_id="+activityId;

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                orderId = rs.getString("order_key_id");
            }
            return orderId;
        } catch (SQLException e){
            e.printStackTrace();
            return orderId;
        }
    }
    /**
     * 获取某一活动会员名下的学员的报名信息
     **/
    public static List<Map<String,String>> getStudentActivityInfoFromMember(Integer memberId, int activityId){

//        List<ActivityStudent> activityStudents=new ArrayList<>();
        List<Map<String,String>> infos=new ArrayList<>();

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select distinct a.*,s.student_phone,s.student_name from " +
                    "(student s left join activity_student a on a.student_id=s.student_id) left join member_re_student m on m.student_id=s.student_id "+
                    "where a.activity_id=" + activityId +
                    " and m.member_key_id="+memberId;

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
//                ActivityStudent activityStudent=new ActivityStudent();
//                activityStudent.setApplyNumber(rs.getString("apply_number"));
//                activityStudent.setApplyTime(rs.getDate("apply_time"));
//                activityStudent.setStudentId(rs.getInt("student_id"));
//                activityStudent.setActivityId(rs.getInt("activity_id"));
//                activityStudent.setSeatId(rs.getInt("seat_id"));
//                activityStudent.setSeatNumber(rs.getString("seat_number"));
//                activityStudents.add(activityStudent);
                HashMap<String,String> info=new HashMap<>();
                info.put("name", rs.getString("student_name"));
                info.put("phone", rs.getString("student_phone"));
                info.put("seatNumber", rs.getString("seat_number"));
                info.put("applyNumber", rs.getString("apply_number"));
                info.put("applyTime", rs.getString("apply_time"));
                infos.add(info);
            }
            return infos;
        } catch (SQLException e){
            e.printStackTrace();
            return infos;
        }
    }
    /**
     * 获取某会员名下学员已报名的活动 id 数组
     **/
    public static List<Integer> getActivityIdByMemberId(Integer memberId){

        List<Integer> activityIds=new ArrayList<Integer>();

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select distinct a.activity_id from member_re_student m,activity_student a " +
                    "where m.member_key_id="+memberId+" and m.student_id=a.student_id";

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                int activityId=rs.getInt("activity_id");
                activityIds.add(activityId);
            }
            return activityIds;

        } catch (SQLException e){
            e.printStackTrace();
            return activityIds;
        }
    }
}
