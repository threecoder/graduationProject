package com.lutayy.campbackend.service.SQLConn;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateSQLConn {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name = "root";
    private static final String Pwd = "123456";
    //会员获取名下学员的证书
    public static List<Integer> memberGetCerficateId(Integer memberId, String cerName, String trainingName,
                                        Integer pageSize, Integer currentPage){
        List<Integer> idList=new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            sql = "select c.certificate_id from certificate c " +
                    "join training t on c.training_id=t.training_id " +
                    "join member_re_student r on c.student_id=r.student_id " +
                    "where r.member_key_id="+memberId+" and c.is_invalid=0";
            if (cerName != null && !cerName.equals("")) {
                sql += (" and c.certificate_name like '%" + cerName + "%'");
            }
            if (trainingName != null && !trainingName.equals("")) {
                sql += (" and t.training_name like '%" + trainingName + "%'");
            }
            sql += (" limit " + (currentPage - 1) + "," + pageSize);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                idList.add(rs.getInt("certificate_id"));
            }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return idList;
        }
    }

    public static int memberCountCerficateId(Integer memberId, String cerName, String trainingName){
        int sum=0;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            sql = "select count(c.certificate_id) cou from certificate c " +
                    "join training t on c.training_id=t.training_id " +
                    "join member_re_student r on c.student_id=r.student_id " +
                    "where r.member_key_id="+memberId+" and c.is_invalid=0";
            if (cerName != null && !cerName.equals("")) {
                sql += (" and c.certificate_name like '%" + cerName + "%'");
            }
            if (trainingName != null && !trainingName.equals("")) {
                sql += (" and t.training_name like '%" + trainingName + "%'");
            }
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sum=rs.getInt("cou");
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
            return sum;
        }
    }

    //学员获取自己的有效证书
    public static List<Integer> studentGetCerficateId(Integer studentId, String cerName, String trainingName,
                                                     Integer pageSize, Integer currentPage){
        List<Integer> idList=new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            if(trainingName != null && !trainingName.equals("")) {
                sql = "select c.certificate_id from certificate c " +
                        "join training t on c.training_id=t.training_id " +
                        "where c.is_invalid=0 and c.student_id=" + studentId;
            }else {
                sql = "select c.certificate_id from certificate c " +
                        "where c.is_invalid=0 and c.student_id=" + studentId;
            }
            if (cerName != null && !cerName.equals("")) {
                sql += (" and c.certificate_name like '%" + cerName + "%'");
            }
            if (trainingName != null && !trainingName.equals("")) {
                sql += (" and t.training_name like '%" + trainingName + "%'");
            }
            sql += (" limit " + (currentPage - 1) + "," + pageSize);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                idList.add(rs.getInt("certificate_id"));
            }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return idList;
        }
    }

    public static int studentCountCerficateId(Integer studentId, String cerName, String trainingName){
        int sum=0;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            if(trainingName != null && !trainingName.equals("")) {
                sql = "select count(c.certificate_id) cou from certificate c " +
                        "join training t on c.training_id=t.training_id " +
                        "where c.is_invalid=0 and c.student_id=" + studentId;
            } else {
                sql = "select count(c.certificate_id) cou from certificate c " +
                        "where c.is_invalid=0 and c.student_id=" + studentId;
            }
            if (cerName != null && !cerName.equals("")) {
                sql += (" and c.certificate_name like '%" + cerName + "%'");
            }
            if (trainingName != null && !trainingName.equals("")) {
                sql += (" and t.training_name like '%" + trainingName + "%'");
            }
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sum=rs.getInt("cou");
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
            return sum;
        }
    }

    //学员获取自己的无效证书
    public static List<Integer> studentGetOverDueCerficateId(Integer studentId, String cerName, String trainingName,
                                                      Integer pageSize, Integer currentPage){
        List<Integer> idList=new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            if(trainingName != null && !trainingName.equals("")) {
                sql = "select c.certificate_id from certificate c " +
                        "join training t on c.training_id=t.training_id " +
                        "where c.is_invalid=1 and c.student_id=" + studentId;
            }else {
                sql = "select c.certificate_id from certificate c " +
                        "where c.is_invalid=1 and c.student_id=" + studentId;
            }
            if (cerName != null && !cerName.equals("")) {
                sql += (" and c.certificate_name like '%" + cerName + "%'");
            }
            if (trainingName != null && !trainingName.equals("")) {
                sql += (" and t.training_name like '%" + trainingName + "%'");
            }
            sql += (" limit " + (currentPage - 1) + "," + pageSize);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                idList.add(rs.getInt("certificate_id"));
            }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return idList;
        }
    }

    public static int studentCountOverDueCerficateId(Integer studentId, String cerName, String trainingName){
        int sum=0;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();
            String sql;
            if(trainingName != null && !trainingName.equals("")) {
                sql = "select count(c.certificate_id) cou from certificate c " +
                        "join training t on c.training_id=t.training_id " +
                        "where c.is_invalid=1 and c.student_id=" + studentId;
            } else {
                sql = "select count(c.certificate_id) cou from certificate c " +
                        "where c.is_invalid=1 and c.student_id=" + studentId;
            }
            if (cerName != null && !cerName.equals("")) {
                sql += (" and c.certificate_name like '%" + cerName + "%'");
            }
            if (trainingName != null && !trainingName.equals("")) {
                sql += (" and t.training_name like '%" + trainingName + "%'");
            }
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sum=rs.getInt("cou");
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
            return sum;
        }
    }
}
