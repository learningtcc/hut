package com.hut.manage.service;

import com.hut.manage.pojos.ManageUser;

/**
 * Created by Jared on 2017/1/12.
 */
public interface UserService {

    ManageUser getUserByUserNameAndPassword(String userName, String password);

    ManageUser register(ManageUser model);
}
