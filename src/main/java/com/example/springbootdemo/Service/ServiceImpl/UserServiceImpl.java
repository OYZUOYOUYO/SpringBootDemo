package com.example.springbootdemo.Service.ServiceImpl;

import com.example.springbootdemo.Bean.User;
import com.example.springbootdemo.Mapper.UserMapper;
import com.example.springbootdemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public boolean updateUser() {
        return false;
    }

    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
