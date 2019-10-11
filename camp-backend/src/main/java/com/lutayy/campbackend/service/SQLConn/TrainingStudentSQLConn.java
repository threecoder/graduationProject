package com.lutayy.campbackend.service.SQLConn;


import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingStudentSQLConn {

    private static final String URL="jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name="root";
    private static final String Pwd="123456";

    /**
     * 获取某会员名下学员已报名的培训 id 数组
     **/
    public static List<Integer> getTrainingIdByMemberId(String memberId){

        List<Integer> trainingIds=new ArrayList<Integer>();

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select t.training_id from member_re_student m,training_re_student t " +
                    "where m.member_id='"+memberId+"' and m.student_id=t.student_id and t.is_apply_success=1";

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                int trainingId=rs.getInt("training_id");
                trainingIds.add(trainingId);
            }
            return trainingIds;

        } catch (SQLException e){
            e.printStackTrace();
            return trainingIds;
        }
    }

    /**
     * 获取 包含对应学员的 由会员提交的 未支付的有效的订单的 订单号
     **/
    public static String getTrainingOrderByStudentId(int studentId, int trainingId){

        String orderId=null;

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select o.training_order_id from training_order o,training_order_student a " +
                    "where o.training_order_id=a.training_order_id and a.student_id=" + studentId +
                    " and o.payment_state=0 and o.close=0 and o.order_type=0 and o.training_id="+trainingId;

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                orderId = rs.getString("training_order_id");
            }
            return orderId;
        } catch (SQLException e){
            e.printStackTrace();
            return orderId;
        }
    }
}
