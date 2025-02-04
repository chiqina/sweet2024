package org.example.sprintboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.sprintboot.entity.User;
import org.example.sprintboot.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller

public class TokenUtils {
    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;
    @PostConstruct
    public void setUserService(){
        staticUserMapper = userMapper;
    }

    /**
     * 生成token
     * @param userId
     * @param sign
     * @return
     */
    public static String createToken(String userId ,String sign){
        return JWT.create().withAudience(userId) //将userid 存入到Audience这个数组中
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //设置token过期时间
                .sign(Algorithm.HMAC256(sign)); //以password作为 token秘钥
    }

    /**
     * 获取用户信息
     * @return
     */
    public static User getCurrentUser(){
        try{
           HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
           String token = request.getHeader("token");
           if(StrUtil.isNotBlank(token)){
               String userId = JWT.decode(token).getAudience().get(0);
               return staticUserMapper.selectById(Integer.valueOf(userId));
           }

        }catch(Exception e){
            return null;
        }
        return null;
    }

}
