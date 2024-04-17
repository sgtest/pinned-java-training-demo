package com.lz.demo.demos.service;

import com.lz.demo.demos.dto.User;
import com.lz.demo.demos.exception.IdempotentException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author lizhi
 * @create 2024-01-31
 **/
@Service
public class TokenService {

    @Autowired
    RedisService redisService;

    /**
     * 创建一个token，设置过期时间为5s
     * (可以利用权限验证框架获取用户的id为key)
     * @return
     */
    public String createToken(){
        String uuid = UUID.randomUUID().toString();
        redisService.setEx(uuid,uuid,10000L);
        return uuid;
    }

    /**
     * 检查token是否存在，如果存在就删除
     * @param token
     * @return
     * @throws IdempotentException
     */
    public boolean checkToken(String token) throws IdempotentException {
        if (StringUtils.isBlank(token)){
            throw new IdempotentException("token 不存在");
        }
        if (!redisService.exists(token)){
            throw new IdempotentException("重复操作");
        }

        boolean remove=redisService.delete(token);
        if (!remove){
            throw new IdempotentException("重复操作");
        }
        return true;
    }


    public boolean checkToken(HttpServletRequest request) throws IdempotentException {
        String token=request.getHeader("token");
        if (StringUtils.isBlank(token)){
            token=request.getParameter("token");
        }
        return checkToken(token);
    }
}
