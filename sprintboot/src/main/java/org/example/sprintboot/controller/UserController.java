package org.example.sprintboot.controller;

import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import org.apache.ibatis.session.SqlSessionException;
import org.example.sprintboot.common.Result;
import org.example.sprintboot.entity.User;
import org.example.sprintboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
/**
 * 总结：
 * requestBody 传的是一个对象，通常传的是json
 * pathVariable 传的是单个参数
 */

public class UserController {
    //下面这个是用来引进service文件下的UserService文件的
    @Autowired
    UserService userService;

    /**
     * 添加用户信息
     */
    @PostMapping("add")
    public Result add(@RequestBody User user){
        try{
            userService.insertUser(user);
        } catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                return Result.error("插入数据库错误:");
            }else{
                return Result.error("服务器内部错误");
            }
        }
        return Result.success(user);
    }

    @PutMapping("update")
    public Result update(@RequestBody User user){
        try{
            userService.updateUser(user);
        }catch(Exception e){
            if(e instanceof BadSqlGrammarException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else if(e instanceof DuplicateKeyException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else{
                System.out.println(e.getMessage());
                return Result.error("服务器内部错误");
            }
        }
        return  Result.success(user);
    }

    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Integer id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            if(e instanceof BadSqlGrammarException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else if(e instanceof DuplicateKeyException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else{
                System.out.println(e.getMessage());
                return Result.error("服务器内部错误");
            }
        }
        return Result.success("第"+id+"行删除成功");
    }

    @DeleteMapping("batchDel")
    public Result batchDel(@RequestBody List<Integer> ids){
        try{
            userService.batchDeleteUser(ids);
        }catch(Exception e){
            if(e instanceof BadSqlGrammarException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else if(e instanceof DuplicateKeyException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else{
                System.out.println(e.getMessage());
                return Result.error("服务器内部错误");
            }
        }
        return Result.success("第"+ids.toString()+"行删除成功");
    }

    @GetMapping("selectAll")
    public Result selectAll(){
        List<User>  data;
        try{
            data = userService.selectUser();
        }catch(Exception e){
            if(e instanceof BadSqlGrammarException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else if(e instanceof DuplicateKeyException){
                System.out.println(e.getMessage());
                return Result.error("插入数据库错误:");
            }else{
                System.out.println(e.getMessage());
                return Result.error("服务器内部错误");
            }
        }
        return Result.success(data);
    }

    @GetMapping("MultipleSelect")
    public Result  MultipleSelect(@RequestParam String username,@RequestParam String name,@RequestParam Integer page
    ,@RequestParam Integer pageSize){
        //加一个分页
        Map<String,Object> map = new HashMap<>();
        List<User>  data = userService.MultipleSelect(username,name,page,pageSize);
        Integer total = userService.selectUserCount(username,name);
        map.put("data",data);
        map.put("total",total);
        return Result.success(map);
    }
}


