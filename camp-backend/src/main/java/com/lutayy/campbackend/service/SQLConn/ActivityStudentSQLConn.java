package com.lutayy.campbackend.service.SQLConn;

import org.springframework.stereotype.Component;

import java.sql.*;

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
            String sql="select o.activity_order_id from activity_order o,activity_order_student a " +
                    "where o.activity_order_id=a.activity_order_id and a.student_id=" + studentId +
                    " and o.payment_state=0 and o.close=0 and o.order_type=0 and o.activity_id="+activityId;

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                orderId = rs.getString("activity_order_id");
            }
            return orderId;
        } catch (SQLException e){
            e.printStackTrace();
            return orderId;
        }
    }
}
