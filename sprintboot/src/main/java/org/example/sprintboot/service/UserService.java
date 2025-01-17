package org.example.sprintboot.service;

import org.example.sprintboot.entity.User;
import org.example.sprintboot.exception.ServiceException;
import org.example.sprintboot.mapper.UserMapper;
import org.example.sprintboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void updateUser(User user){
        userMapper.update(user);
    }

    public void deleteUser(Integer id){
        userMapper.deleteById(id);
    }

    public void batchDeleteUser(List<Integer> ids){
        for(Integer id : ids){
            userMapper.deleteById(id);
        }
    }

    public List<User> selectUser(){
         return userMapper.findAll();
    }

    public List<User> MultipleSelect(String username,String name,Integer page,Integer pageSize){
        page = (page-1) * pageSize;
        return userMapper.MultipleSelect(username,name,page,pageSize);
    }

    public  Integer selectUserCount(String username,String name){
        return userMapper.countByUsername(username,name);
    }

    /**
    /**
     * 登入
     */
    public User login(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, password);
        if(user == null){
            throw new ServiceException("用户名或密码错误");
        }
        if(!user.getPassword().equals(username) && !user.getUsername().equals(username)){
            throw new ServiceException("用户名或密码错误");
        }
        //登入成功后生成一个token
        String token = TokenUtils.createToken(user.getId().toString(), user.getPassword());
        user.setToken(token);
        return user;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public User register(User user) {
        //先查询数据库是否已经存在该用户
        User info = userMapper.getUserName(user.getUsername());
        if(info != null){
            throw  new ServiceException("该用户名已存在");
        }
        //否则插入用户数据
        userMapper.insert(user);
        //返回user数据
        return user;
    }
}
