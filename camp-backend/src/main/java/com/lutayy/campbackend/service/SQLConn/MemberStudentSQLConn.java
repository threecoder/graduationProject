package com.lutayy.campbackend.service.SQLConn;


import com.lutayy.campbackend.pojo.Student;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberStudentSQLConn {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name = "root";
    private static final String Pwd = "123456";

    public static List<Student> getStudentsFromMemberReStudent(Integer memberId, String phone, String idNum, String name,
                                                               Integer currentPage, Integer pageSize) {
        List<Student> students = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();

            String sql = "select distinct s.* from member_re_student r,student s " +
                    "where r.student_id=s.student_id";
            if(memberId!=null){
                sql += "  and r.member_key_id=" + memberId;
            }
            if (phone != null && !phone.equals("")) {
                sql += " and s.student_phone like '%" + phone + "%'";
            }
            if (idNum != null && !idNum.equals("")) {
                sql += " and s.student_idcard='" + idNum + "'";
            }
            if (name != null && !name.equals("")) {
                sql += " and s.student_name like '%" + name + "%'";
            }
            sql += " limit " + (currentPage - 1) * pageSize + "," + pageSize;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student();
                student.setCompany(rs.getString("company"));
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentPassword(rs.getString("student_password"));
                student.setStudentIdcard(rs.getString("student_idcard"));
                student.setStudentPhone(rs.getString("student_phone"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentEmail(rs.getString("student_email"));
                student.setStudentPosition(rs.getString("student_position"));
                student.setStudentCountry(rs.getString("student_country"));
                student.setStudentProvince(rs.getString("student_province"));
                student.setStudentCity(rs.getString("student_city"));
                student.setStudentArea(rs.getString("student_area"));
                student.setStudentAddress(rs.getString("student_address"));
                student.setEnterTime(rs.getDate("enter_time"));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return students;
        }
    }

    public static int countStudentsFromMemberReStudent(Integer memberId, String phone, String idNum, String name) {
        int sum = 0;

        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(URL, Name, Pwd);
            statement = conn.createStatement();

            String sql = "select count(*) c from member_re_student r inner join student s " +
                    "where r.student_id=s.student_id and r.member_key_id=" + memberId;
            if (phone != null && !phone.equals("")) {
                sql += " and s.student_phone like '%" + phone + "%'";
            }
            if (idNum != null && !idNum.equals("")) {
                sql += " and s.student_idcard='" + idNum + "'";
            }
            if (name != null && !name.equals("")) {
                sql += " and s.student_name like '%" + name + "%'";
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
}
