package com.attend.attendance.controller;

import com.attend.attendance.entity.Attend;
import com.attend.attendance.service.AttendService;
import com.attend.attendance.vo.QueryCondition;
import com.attend.common.page.PageQueryBean;
import com.attend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by
 * 白夜行 on 2018/4/27.
 */
@Controller
@RequestMapping("attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @RequestMapping
    public String toAttend(){
        return "attend";
    }


    @RequestMapping("/sign")
    @ResponseBody
    public String signAttend(@RequestBody Attend attend) throws Exception {
        attendService.signAttend(attend);
        return "succ";
    }

    /**
     *
     * @param queryCondition
     * @return
     */
    @RequestMapping("/attendList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition queryCondition,HttpSession session){
        User user = (User) session.getAttribute("userinfo");
        queryCondition.setUserId(user.getId());
        PageQueryBean pageQueryBean = attendService.listAttend(queryCondition);
        return pageQueryBean;
    }
}
