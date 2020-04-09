package com.lutayy.campbackend.common.config;

import java.util.Arrays;
import java.util.List;

public class AuthorityParam {

    private final List<String> authorityList
            = Arrays.asList("member", "student", "training",
            "exam", "grade", "certificate", "activity", "vote",
            "index", "system", "order", "coupon");

    public static String getAuthority(int i) {
        if (i == 0)
            return "";
        else if (i > 12)
            return "";
        else
            return new AuthorityParam().authorityList.get(i-1);
    }

}
