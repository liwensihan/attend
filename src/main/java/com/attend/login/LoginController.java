package com.attend.login;


import com.alibaba.fastjson.JSONObject;
import com.attend.common.util.SecurityUtils;
import com.attend.user.entity.User;
import com.attend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by
 * 白夜行 on 2018/4/26.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService userService;


    @RequestMapping("/index")
    public String login(){

        return "login";
    }

    @RequestMapping("/check")
    @ResponseBody
    public String checkLogin(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findUserByUsername(username);
        String result;
        if (user != null) {
            if (SecurityUtils.checkPassword(password,user.getPassword())){//将本次输入的密码加密后再和数据库中的加密过的密码相匹配
                //校验成功返回session
                request.getSession().setAttribute("userinfo",user);
                result="login_succ";
                return result;
            }else{
                result="login_fail";
                //失败返回login_fail
                return result;
            }
        }else{
            result="login_fail";
            //失败返回login_fail
            return result;
        }

    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String s =userService.insertUser(user);
        if (s.equals("success")){
            return "succ";
        }else{
            return "fail";
        }
    }
}
