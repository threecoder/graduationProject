package com.lutayy.campbackend.controller;

import com.lutayy.campbackend.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;
}
