package com.example.lafsys.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.lafsys.entity.User;
import com.example.lafsys.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Slf4j
@Component
public class TokenUtils {
    @Resource
    private UserMapper userMapper;

    private static UserMapper staticUserMapper;

    @PostConstruct
    public void setUserService(){
        staticUserMapper = userMapper;
    }
    /*
     * 生成token
     */
    public static String genToken(String id, String sign){
       return JWT.create().withAudience(id)    // 将id保存在token里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),3)) //3小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以password作为token的密钥
    }

    /**
     * 获取当前登录的用户信息
     * @return user对象
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");

            if (StrUtil.isNotBlank(token)) {
                String id = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(id);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
