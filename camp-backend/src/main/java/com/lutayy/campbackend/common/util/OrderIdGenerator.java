package com.lutayy.campbackend.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIdGenerator {

    public static String getUniqueId(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//毫秒级
        Random random=new Random();
        int r=(random.nextInt(900000)+100000);
        return df.format(new Date()).replaceAll("[[\\s-:punct:]]","")+r;
    }

    public static void main(String[] args){
        System.out.println(getUniqueId());
    }
}
