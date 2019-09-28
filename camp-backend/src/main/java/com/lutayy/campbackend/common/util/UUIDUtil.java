package com.lutayy.campbackend.common.util;

import java.util.UUID;

public class UUIDUtil {

    public static String getMemberUUID(){
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString().replace("-", "");
        return "mb_"+id.substring(0,13);
    }

    public static void main(String[] args){
        System.out.println(getMemberUUID());
    }
}
