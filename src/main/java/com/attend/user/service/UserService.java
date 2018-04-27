package com.attend.user.service;

import com.attend.user.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by
 * 白夜行 on 2018/4/26.
 */
public interface UserService {
    int createUser(User user);

    User findUserByUsername(String username);

    String insertUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
