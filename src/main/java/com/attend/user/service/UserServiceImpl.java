package com.attend.user.service;

import com.attend.common.util.SecurityUtils;
import com.attend.user.dao.UserMapper;
import com.attend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by
 * 白夜行 on 2018/4/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public int createUser(User user) {
        return userMapper.insertSelective(user);
    }

    public User findUserByUsername(String username) {
        return userMapper.selectByUserName(username);
    }

    public String insertUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        user.setPassword(SecurityUtils.encrptyPassword(user.getPassword()));
        userMapper.insertSelective(user);
        return "success";
    }
}
