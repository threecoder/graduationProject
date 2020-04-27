package com.lutayy.campbackend.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StrUtil {

    //以placeholder为切割点，分割字符串
    public static List<String> cutStringByPlaceHolder(String str, String placeholder) {
        List<String> result = new ArrayList<>();
        int strLenth = str.length();
        int holderLenth = placeholder.length();
        int record = 0;
        for (int i = 0; i < strLenth; i++) {
            if (str.charAt(i) == placeholder.charAt(0)) {
                if (str.substring(i, i + holderLenth).equals(placeholder)) {
                    result.add(str.substring(record, i));
                    record = i + holderLenth;
                    i = i + holderLenth - 1;
                }
            }
        }
        if(record<strLenth-1){
            result.add(str.substring(record));
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(cutStringByPlaceHolder("<p>这是</p><p>@图#片$占#位@@图#片$占#位@@图#片$占#位@</p><p>新闻@图#片$占#位@</p><p>内容@图#片$占#位@</p>", "@图#片$占#位@"));
    }
}
