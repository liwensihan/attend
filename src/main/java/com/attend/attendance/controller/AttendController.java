package com.attend.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by
 * 白夜行 on 2018/4/27.
 */
@Controller
@RequestMapping("attend")
public class AttendController {

    @RequestMapping
    public String toAttend(){
        return "attend";
    }
}
