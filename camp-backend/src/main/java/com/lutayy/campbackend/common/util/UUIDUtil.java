package com.lutayy.campbackend.common.util;

import java.util.UUID;

public class UUIDUtil {

    public static String getMemberUUID(){
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString().replace("-", "");
        return "mb_"+id.substring(0,13);
    }

    //用于生成活动报名编号
    public static String getActivityApplyNumber(int activityId){
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString().replace("-", "");
        /* 报名编号格式:a活动编号_随机6位 */
        return "A"+activityId+"_"+id.substring(0, 6);
    }
    public static String getTrainingApplyNumber(int trainingId){
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString().replace("-", "");
        /* 报名编号格式:a活动编号_随机6位 */
        return "T"+trainingId+"_"+id.substring(0, 6);
    }

    public static void main(String[] args){
        System.out.println(getMemberUUID());
    }

    //用于生成Coupon优惠券的唯一码
    public static String createCoupenCode(int couponId){
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString().replace("-", "");

        return "C"+couponId+"_"+id.substring(0, 13);
    }
}
