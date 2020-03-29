package com.chy.gk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
public class CacheConfiguration extends CachingConfigurerSupport {
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisCacheWriter writer() {
        return RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
    }

    @Bean
    public CacheManager cacheManager() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        //验证码存储5分钟
        configurationMap.put("verificationCode", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60*5)).disableCachingNullValues());
        //普通缓存4小时
        configurationMap.put("normal", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(4)));

        return RedisCacheManager.builder(writer())
                .initialCacheNames(configurationMap.keySet())
                .withInitialCacheConfigurations(configurationMap)
                .build();
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
