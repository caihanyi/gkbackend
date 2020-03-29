package com.chy.gk.controller;


import com.chy.gk.service.VerificationCodeService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PhoneController {

    @Resource
    private VerificationCodeService verificationCodeService;

    @Resource
    CacheManager cacheManager;

    @PostMapping("/getVerificationCode")
    public Map<String, Object> buildVerificationCode(@RequestBody Map<String, String> phone){
        Map<String, Object> map = new HashMap<String, Object>();
        String code = verificationCodeService.buildVerificationCode(phone.get("phoneNum"));
        if(null != code){
            if(null != verificationCodeService.getVerificationCode(phone.get("phoneNum"))) {//验证是否有存进缓存
                map.put("code", "200");
                map.put("msg", "验证码发送成功");
                return map;
//            }
            }

        }
        map.put("code","401");
        map.put("msg","验证码发送失败");
        return map;

    }

    public VerificationCodeService getVerificationCodeService() {
        return verificationCodeService;
    }

    public void setVerificationCodeService(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
