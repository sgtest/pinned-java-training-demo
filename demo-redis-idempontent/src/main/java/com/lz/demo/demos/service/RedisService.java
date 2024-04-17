package com.lz.demo.demos.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lizhi
 * @create 2024-01-31
 **/
@Service
public class RedisService {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 创建Redis的key并加上生效时长
     * @param key token的key
     * @param value token的value
     * @param time token的生效时长
     * @return 是否创建成功
     */
    public boolean setEx(String key,String value,Long time) {
        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(key,value,time,TimeUnit.SECONDS);
//        redisTemplate.expire(key,time, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * redis是否包含该token
     * @param key token
     * @return
     */
    public boolean exists(String key){
        if (StringUtils.isNotBlank(key)){
           return redisTemplate.hasKey(key);
        }
        return false;
    }

    /**
     * 删除redis的token
     * @param key token
     * @return
     */
    public boolean delete(String key){
        if (exists(key)){
            return redisTemplate.delete(key);
        }
        return false;
    }
}
