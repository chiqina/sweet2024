package org.example.sprintboot.controller;

import cn.hutool.core.util.StrUtil;
import org.example.sprintboot.common.AuthAccess;
import org.example.sprintboot.common.Result;
import org.example.sprintboot.entity.User;
import org.example.sprintboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class WebController {
    @Resource
    UserService userService;

    @PostMapping("/")
    public Result hello() {
        return Result.success("success");
    }

    @PostMapping("/login")
    @AuthAccess
    public Result login(@RequestBody User user) {
        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("参数错误，请重新输入");
        }

        User userinfo = userService.login(user.getUsername(), user.getPassword());

        // 登陆逻辑
        return Result.success(userinfo);
    }

    @PostMapping("/register")
    @AuthAccess
    public Result register(@RequestBody User user) {
        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("参数错误，请重新输入");
        }

        User  resoult = userService.register(user);
        return Result.success(resoult);
    }

}
