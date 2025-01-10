package org.example.sprintboot.controller;

import cn.hutool.core.util.StrUtil;
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
    public Result login(@RequestParam String username, @RequestParam String password) {
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error("用户名或密码错误");
        }

        User user = userService.login(username, password);

        // 登陆逻辑
        return Result.success(user);
    }

}
