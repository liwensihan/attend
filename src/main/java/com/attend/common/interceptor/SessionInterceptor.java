package com.attend.common.interceptor;

import com.attend.user.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by
 * 白夜行 on 2018/4/27.
 */
public class SessionInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        if (uri.indexOf("login")>=0) {
            return true;
        }

        HttpSession session = request.getSession();
       User user = (User) session.getAttribute("userinfo");
        if (user != null) {
            return true;
        }
        request.getRequestDispatcher("/login/index").forward(request,response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) throws Exception {

    }
}
