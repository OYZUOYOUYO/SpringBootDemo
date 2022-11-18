package com.example.springbootdemo.Controller;

import com.example.springbootdemo.Bean.User;
import com.example.springbootdemo.Service.UserService;
import com.example.springbootdemo.Utils.EncryptUtil;
import com.example.springbootdemo.Utils.IdentifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class HelloTestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "<h1>Hello Word!</h1>";
    }

//    @RequestMapping("{name}")
//    public String getName(@PathVariable("name") String name){
//        return name;
//    }

    @RequestMapping("/getUser")
    public String getUser(User user,String identifyCode,HttpSession session){
        if(this.checkLogin(user,identifyCode,session)){
            return "redirect:html/hello.html";
        }else {
            return "redirect:index.html";
        }
    }

    @RequestMapping("/login.do")
    public String login(){
        return "html/hello.html";
    }

    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/identifyImage")
    public void identifyImage(HttpServletResponse response, HttpSession session){
        //创建随机验证码
        IdentifyCodeUtils utils = new IdentifyCodeUtils();
        String identifyCode = utils.getIdentifyCode();
        //session存入验证码
        session.setAttribute("identifyCode", identifyCode);
        //根据验证码创建图片
        BufferedImage identifyImage = utils.getIdentifyImage(identifyCode);
        //回传给前端
        utils.responseIdentifyImg(identifyImage,response);

    }

    private boolean checkLogin(User user, String imgcode,HttpSession session){
        AtomicBoolean flag = new AtomicBoolean(false);
        List<User> users = userService.queryUser();
        String identifyCode = session.getAttribute("identifyCode").toString();
        String s = identifyCode.toLowerCase(Locale.ROOT);
        if(users.size()>0){
            users.forEach(each ->{
                if(identifyCode.equals(imgcode) || s.equals(imgcode)){
                    if(each.getName().equals(user.getName()) && each.getPassword().equals(EncryptUtil.stringMD5(user.getPassword()))){
                        flag.set(true);
                        return;
                    }
                }
                else  return;
            });
        }
        return flag.get();
    }
}
