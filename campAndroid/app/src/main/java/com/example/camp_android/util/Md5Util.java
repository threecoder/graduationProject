package com.example.camp_android.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String digest(String source){
        byte[]hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(source.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        }

        StringBuilder hex = new StringBuilder(hash.length*2);
        for(byte b:hash){
            if((b & 0xFF)< 0x10){
                hex.append(0);
            }
            hex.append(Integer.toHexString(b & 0xff));
        }

        return hex.toString().toUpperCase();
    }

}
