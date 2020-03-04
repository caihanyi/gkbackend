package com.chy.gk.controller;

import com.chy.gk.model.uesr.User;
import com.chy.gk.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alibaba.druid.util.Utils.md5;

@RestController
public class  UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign")
    public String signUser(@RequestBody User user){
        if(null != userService.getUserByName(user.getUserName())){
            return "该用户名已注册！";
        } else {
//            new SimpleHash(algorithmName, user.getPassword(),  ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
            user.setSalt(user.getUserName());
            String password = "";
            user.setPassword(new SimpleHash("md5", user.getPassword(),  ByteSource.Util.bytes(user.getUserName()), 2).toHex());
            if (null != userService.addUser(user)){
                return "注册成功！";
            } else {
                return "注册失败！";
            }
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

        try {
            subject.login(token);
        }catch (Exception e){
            return "登录失败！";
        }
//        if(null != userService.getUserByName(user.getUserName())){
//            return "该用户名已注册！";
//        } else {
//            user.setSalt(user.getUserName());
//            user.setPassword(md5(md5(user.getPassword()+user.getSalt())));
//            if (null != userService.addUser(user)){
//                return "注册成功！";
//            } else {
//                return "注册失败！";
//            }
//        }
        return "denglu";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
