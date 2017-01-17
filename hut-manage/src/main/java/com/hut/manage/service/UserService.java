package com.hut.manage.service;

import com.hut.manage.pojos.User;

/**
 * Created by Jared on 2017/1/12.
 */
public interface UserService {

    User getUserByUserNameAndPassword(String userName, String password);
}
