package com.example.springbootdemo.Mapper;

import com.example.springbootdemo.Bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface UserMapper {
    boolean addUser(@Param("User") User user);

    boolean deleteUser();

    boolean updateUser();

    List<User> queryUser();
}
