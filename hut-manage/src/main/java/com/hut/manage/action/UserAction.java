package com.hut.manage.action;

import com.hut.manage.action.base.BaseAction;
import com.hut.manage.pojos.User;
import com.hut.manage.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by Jared on 2017/1/12.
 */

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    Logger logger = Logger.getLogger(UserAction.class);

    @Autowired
    private UserService userService;

    public void login(){

        String userName = model.getUserName();
        String password = model.getPassword();
        User u = userService.getUserByUserNameAndPassword(userName,password);
    }

    public void register(){

    }

}
