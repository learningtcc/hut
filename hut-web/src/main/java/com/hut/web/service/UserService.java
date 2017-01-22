package com.hut.web.service;

import com.hut.web.pojo.User;

/**
 * Created by Jared on 2017/1/17.
 */
public interface UserService {

    User login(String userName, String password);

    User register(User user);

    boolean userNameIsExist(String userName);
}
