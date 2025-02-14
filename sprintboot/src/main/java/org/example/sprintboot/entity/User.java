package org.example.sprintboot.entity;

import lombok.*;



//这里有两种方式可以创建 全参构造和无参构造,还有set和get方法
//第一种
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//第二种
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;
    private String role;

    private String token;
}
