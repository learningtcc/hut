package com.hut.manage.service.support;

import com.hut.manage.dao.UserDao;
import com.hut.manage.pojos.User;
import com.hut.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jared on 2017/1/12.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUserNameAndPassword(String userName, String password) {
        return null;
    }
}
