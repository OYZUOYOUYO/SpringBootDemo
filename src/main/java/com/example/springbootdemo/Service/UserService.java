package com.example.springbootdemo.Service;

import com.example.springbootdemo.Bean.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    boolean addUser(User user);

    boolean deleteUser();

    boolean updateUser();

    List<User> queryUser();

}
