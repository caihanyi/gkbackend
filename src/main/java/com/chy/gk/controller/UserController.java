package com.chy.gk.controller;

import com.chy.gk.model.uesr.User;
import com.chy.gk.service.UserService;
import com.chy.gk.service.VerificationCodeService;
import com.chy.gk.util.PhoneNumCheckUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class  UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;



    @PostMapping("/sign")
    public String signUser(@RequestBody Map<String, String> signInfo){
        String code = verificationCodeService.getVerificationCode(signInfo.get("phoneNum"));
        if(StringUtils.isEmpty(code) ||
                !code.equals(new SimpleHash("md5", signInfo.get("verificationCode"),  ByteSource.Util.bytes(PhoneNumCheckUtil.CODEKEY), 2).toHex())){
            return "验证码错误";
        }
        User user = new User();
        if(null != userService.getUserByName(signInfo.get("userName"))){
            return "该用户名已注册！";
        } else {
//            new SimpleHash(algorithmName, user.getPassword(),  ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
            user.setUserName(signInfo.get("userName"));
            user.setSalt(signInfo.get("userName"));
            user.setPhoneNum(signInfo.get("phoneNum"));
            String password = "";
            user.setPassword(new SimpleHash("md5", signInfo.get("password"),  ByteSource.Util.bytes(user.getSalt()), 2).toHex());
            if (null != userService.addUser(user)){
                return "注册成功！";
            } else {
                return "注册失败！";
            }
        }
    }

    @PostMapping("/loginByPhoneNum")
    @CrossOrigin
    public Map<String, Object> loginByPhoneNum(@RequestBody Map<String, String> phone){
        UsernamePasswordToken token = new UsernamePasswordToken(phone.get("phoneNum"), phone.get("verificationCode"));
        return executeShiroLogin(token);
    }

    @PostMapping("/login")
    @CrossOrigin
    public Map<String, Object> login(@RequestBody User user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        return executeShiroLogin(token);

    }

    public Map<String, Object> executeShiroLogin(UsernamePasswordToken token){
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            subject.login(token);
            Session session = subject.getSession();
            map.put("code", "200");
            map.put("msg", "登录成功！");
            map.put("session", session.getId());
            return map;
        } catch (IncorrectCredentialsException e) {
            map.put("code","401");
            map.put("msg","密码错误！");
            return map;
        } catch (LockedAccountException e) {
            map.put("code","401");
            map.put("msg","该用户已被禁用！");
            return map;
        } catch (AuthenticationException e) {
            map.put("code","401");
            map.put("msg","v！");
            return map;
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public VerificationCodeService getVerificationCodeService() {
        return verificationCodeService;
    }

    public void setVerificationCodeService(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }
}
