package com.lutayy.campbackend.service.SQLConn;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public static List<Integer> getTrainingIdByMemberId(Integer memberId){

        List<Integer> trainingIds=new ArrayList<Integer>();

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select distinct t.training_id from member_re_student m,training_re_student t " +
                    "where m.member_key_id='"+memberId+"' and m.student_id=t.student_id";

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
            String sql="select o.order_key_id from training_order o,training_order_student a " +
                    "where o.order_key_id=a.order_key_id and a.student_id=" + studentId +
                    " and o.payment_state=0 and o.close=0 and o.order_type=0 and o.training_id="+trainingId;

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

    //由培训id获取报名了培训的学员（包含检索条件：姓名，身份证，公司名称）
    public static JSONArray getStudentByTrainingId(Integer pageSize, Integer currentPage,
                                                   Integer trainingId, String studentName, String idCard, String company) {
        JSONArray array = new JSONArray();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql = "select s.student_idcard, s.student_name, s.student_phone, s.company, t_o.payment_state from training_order_student tos " +
                    "inner join student s on s.student_id=tos.student_id " +
                    "inner join training_order t_o on t_o.order_key_id=tos.order_key_id " +
                    "where t_o.close=0 and t_o.training_id="+trainingId;
            if(studentName!=null && !studentName.equals("")){
                sql+=" and s.student_name like '%" + studentName + "%'";
            }
            if(idCard!=null && !idCard.equals("")){
                sql+=" and s.student_idcard="+idCard;
            }
            if(company!=null && !company.equals("")){
                sql+=" and s.company like '%" + company + "%'";
            }
            sql += " order by r.begin_time DESC limit " + (currentPage - 1) * pageSize + "," + pageSize;

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                JSONObject object=new JSONObject();
                object.put("idNum", rs.getString("student_idcard"));
                object.put("name", rs.getString("student_name"));
                object.put("phone", rs.getString("student_phone"));
                object.put("company", rs.getString("company"));
                if(rs.getBoolean("payment_state")){
                    object.put("status", "是");
                }else {
                    object.put("status", "否");
                }
                array.add(object);
            }
            return array;

        } catch (SQLException e) {
            e.printStackTrace();
            return array;
        }
    }

    //由培训id获取报名了培训的学员（包含检索条件：姓名，身份证，公司名称）
    public static int countStudentByTrainingId(Integer trainingId, String studentName, String idCard, String company) {
        int sum=0;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql = "select count(s.student_id) c from student s inner join training_re_student r on s.student_id=r.student_id " +
                    "where r.is_invalid=0 and r.training_id="+trainingId;
            if(studentName!=null && !studentName.equals("")){
                sql+=" and s.student_name like '%" + studentName + "%'";
            }
            if(idCard!=null && !idCard.equals("")){
                sql+=" and s.student_idcard="+idCard;
            }
            if(company!=null && !company.equals("")){
                sql+=" and s.company like '%" + company + "%'";
            }

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getInt("c");
            }
            return sum;

        } catch (SQLException e) {
            e.printStackTrace();
            return sum;
        }
    }

    //由培训id获取没报名培训的学员（包含检索条件：姓名，身份证，公司名称）
    public static List<Integer> getStudentListInTraining(Integer trainingId) {
        List<Integer> studentList=new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql = "select s.student_id from student s inner join training_re_student r on s.student_id=r.student_id " +
                    "where r.is_invalid=0 and r.training_id="+trainingId;

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                studentList.add(rs.getInt("student_id"));
            }
            return studentList;

        } catch (SQLException e) {
            e.printStackTrace();
            return studentList;
        }
    }

}
