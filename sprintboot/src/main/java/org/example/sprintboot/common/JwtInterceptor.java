package org.example.sprintboot.common;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.sprintboot.entity.User;
import org.example.sprintboot.exception.ServiceException;
import org.example.sprintboot.mapper.UserMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper Usermapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            //从URL接收token
            token = request.getParameter("token");
        }
        //这是第二种方式通过自定义注解方式进行配置拦截：判断可以直接通过的方法，需要用到自定义注解AuthAccess
        if(handler instanceof HandlerMethod){
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if(annotation!= null){
                //可以直接通过的方法，不需要token验证
                return true;
            }
        }
        //执行认证
        if (StrUtil.isBlank(token)) {
           throw new ServiceException("401","请登录");
        }
        //获取token中的userID
        String userID;
        try {
            userID = JWT.decode(token).getAudience().get(0);
        }catch(Exception e){
            throw new ServiceException("401","请登录");
        }
        //根据token中的userID去数据库查询数据
        User user = Usermapper.selectById(Integer.valueOf(userID));
        if(user == null){
            throw new ServiceException("401","请重新登录");
        }

        //用户密码加签验证 token
        JWTVerifier jwtverifier = JWT.require(Algorithm.HMAC256(user.getPassword().getBytes())).build();
        try {
            jwtverifier.verify(token);//验证token
        }catch(Exception e){
            throw new ServiceException("401","请重新登录");
        }
        return true;
    }

}
