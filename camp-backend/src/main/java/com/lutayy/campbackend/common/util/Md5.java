package com.lutayy.campbackend.common.util;

import org.springframework.util.DigestUtils;

import java.util.Date;

public class Md5 {

    public static String digest(String source){
        String md5word = DigestUtils.md5DigestAsHex(source.getBytes());
        return md5word.toUpperCase();
    }
    public static void main(String[] args){
        Date date=new Date();
        String year=String.format("%tm", date);
        System.out.println(year);
    }
}
