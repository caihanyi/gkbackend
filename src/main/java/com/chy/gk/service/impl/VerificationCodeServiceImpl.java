package com.chy.gk.service.impl;

import com.chy.gk.model.uesr.User;
import com.chy.gk.service.UserService;
import com.chy.gk.service.VerificationCodeService;
import com.chy.gk.util.PhoneNumCheckUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private UserService userService;

    @Resource
    CacheManager cacheManager;

    @Override
    @CachePut(value = "verificationCode")//用户点击获取验证码@CachePut 每次都重新缓存，每次生成的验证码不一样 ，如果每次生成的验证码一样则用@Cacheable，下次查询直接查询缓存
    public String buildVerificationCode(String phoneNum) {
//        User user = userService.getUserByPhoneNum(phoneNum);
//        if(null == user){
//            return null;
//        }
        String code  = "152362";
        //此处应该生成验证码并调用发送短信相关接口，这里直接写死
        //对验证码加密，加密规则与密码一致，方便shiro验证
        String newCode = new SimpleHash("md5", code,  ByteSource.Util.bytes(PhoneNumCheckUtil.CODEKEY), 2).toHex();
        return newCode;
    }

    @Override
    @Cacheable (value = "verificationCode")//校验验证码，直接从redis里获取，获取不到返回空
    public String getVerificationCode(String phoneNum) {
//        Cache cache = cacheManager.getCache("verificationCode");

//        Object code = cache.get(phoneNum).get();
//         String code = (String) cache.get(phoneNum).get();
        return null;
    }
}
