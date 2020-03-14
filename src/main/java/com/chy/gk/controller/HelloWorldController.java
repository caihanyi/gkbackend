package com.chy.gk.controller;


import com.chy.gk.model.uesr.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HelloWorldController {

    @PostMapping("/hello")
    public String hello(){
        Subject subject = SecurityUtils.getSubject();
        User user= (User)subject.getPrincipal();
        return user.getUserName();
    }
}
