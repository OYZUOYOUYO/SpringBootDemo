package com.example.springbootdemo.Controller;

import com.example.springbootdemo.Bean.User;
import com.example.springbootdemo.Service.UserService;
import com.example.springbootdemo.Utils.EncryptUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("register")
    public String register(User user,String again_password){
        if(user.getPassword().equals(again_password)){
            user.setPassword(EncryptUtil.stringMD5(again_password));
            if(userService.addUser(user)){
                return "redirect:index.html";
            }else {
                return "redirect:html/error.html";
            }
        }else {
            return "redirect:html/error.html";
        }
    }

    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(Integer page, Integer limit){
        List<User> temps = userService.queryUser();
        PageHelper.startPage(page,limit);
        List<User> users = userService.queryUser();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("count",temps.size());
        map.put("data", users);
        return map;
    }
}
