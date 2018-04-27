package com.attend.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.attend.user.entity.User;
import com.attend.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by
 * 白夜行 on 2018/4/26.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String user(){
        User user = new User();
        user.setMobile("12345");
        user.setRealName("老李");
        user.setUsername("sihan");
        user.setPassword("123456");
        userService.createUser(user);
        return "user";
    }

    /**
     * 获取用户信息
     * @return 用户对象
     */
    @RequestMapping("/userinfo")
    @ResponseBody
    public User getUser(HttpSession session){
        User user = (User) session.getAttribute("userinfo");
        return user;
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
