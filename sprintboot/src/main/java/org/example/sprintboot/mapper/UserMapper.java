package org.example.sprintboot.mapper;

import org.apache.ibatis.annotations.*;
import org.example.sprintboot.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into `user`(username,password,name,phone,email,address,avatar) " +
            "values(#{username},#{password},#{name},#{phone},#{email},#{address},#{avatar})")
     void insert(User user);

    @Update("update `user` set username=#{username},password=#{password},name=#{name},phone=#{phone},email=#{email}" +
            ",address=#{address},avatar=#{avatar} where id=#{id}")
    void update(User user);

    @Delete("delete from `user` where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from  `user`")
    List<User> findAll();

    @Select("SELECT * FROM `user` WHERE username LIKE CONCAT('%', #{username}, '%') AND name = #{name} order by id desc " +
            "limit #{page},#{pageSize}")
    //@Select("select * from `user` where username = #{username} and name = #{name}")
    List<User> MultipleSelect(@Param("username") String username, @Param("name") String name,@Param("page") Integer page
            ,@Param("pageSize") Integer pageSize);

    @Select("SELECT count(*) FROM `user` WHERE username LIKE CONCAT('%', #{username}, '%') AND name = #{name}")
    Integer countByUsername(@Param("username") String username, @Param("name") String name);

    @Select("Select * FROM `user` where username = #{username} and password = #{password} LIMIT 1")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
