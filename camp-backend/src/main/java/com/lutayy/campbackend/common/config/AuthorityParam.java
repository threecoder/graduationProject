package com.lutayy.campbackend.common.config;

import com.lutayy.campbackend.dao.AdminReAuthorityMapper;
import com.lutayy.campbackend.pojo.AdminReAuthority;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class AuthorityParam {


    public static final int MEMBER = 1;

    public static final int STUDENT = 2;

    public static final int TRAINING = 3;

    public static final int EXAM = 4;

    public static final int GRADE = 5;

    public static final int CERTIFICATE = 6;

    public static final int ACTIVITY = 7;

    public static final int VOTE = 8;

    public static final int INDEX = 9;

    public static final int SYSTEM = 10;

    public static final int ORDER = 11;

    public static final int COUPON = 12;

    public static final List<String> authorityList
            = Arrays.asList("", "member", "student", "training",
            "exam", "grade", "certificate", "activity", "vote",
            "index", "system", "order", "coupon");

}
