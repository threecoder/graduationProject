package com.lutayy.campbackend.service.SQLConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponMemberSQLConn {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name="root";
    private static final String Pwd="123456";

    //由优惠券id获取会员列表（检索信息：名字、keyId）
    public static List<Integer> getMemberKeyIdListByCouponId(Integer couponId, Integer memberKeyId, String memberName,
                                                             Integer pageSize, Integer currentPage){
        List<Integer> memberIds=new ArrayList<>();

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select distinct m.member_key_id from member m inner join coupon_member cm " +
                    "on m.member_key_id=cm.member_key_id where cm.is_invalid=0 and cm.coupon_id="+couponId;
            if(memberKeyId!=null){
                sql += " and m.member_key_id="+memberKeyId;
            }
            if (memberName != null && !memberName.equals("")) {
                sql += " and m.member_name like '%" + memberName + "%'";
            }
            sql += " limit " + (currentPage - 1) * pageSize + "," + pageSize;
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                int mKid=rs.getInt("member_key_id");
                memberIds.add(mKid);
            }
            return memberIds;

        } catch (SQLException e){
            e.printStackTrace();
            return memberIds;
        }
    }

    public static int countMembersByCouponId(Integer couponId, Integer memberKeyId, String memberName){
        int sum=0;

        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select count(distinct m.member_key_id) c from member m inner join coupon_member cm " +
                    "on m.member_key_id=cm.member_key_id where cm.coupon_id="+couponId;
            if(memberKeyId!=null){
                sql += " and m.member_key_id="+memberKeyId;
            }
            if (memberName != null && !memberName.equals("")) {
                sql += " and m.member_name like '%" + memberName + "%'";
            }
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                sum=rs.getInt("c");
            }
            return sum;

        } catch (SQLException e){
            e.printStackTrace();
            return sum;
        }
    }
}
