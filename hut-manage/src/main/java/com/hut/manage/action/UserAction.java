package com.hut.manage.action;

import com.hut.manage.action.base.BaseAction;
import com.hut.manage.pojos.ManageUser;
import com.hut.manage.service.UserService;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by Jared on 2017/1/12.
 */

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<ManageUser> {

    Logger logger = Logger.getLogger(UserAction.class);

    @Autowired
    private UserService userService;

    public String login() throws IOException {

        String userName = model.getUserName();
        String password = model.getPassword();
        ManageUser u = userService.getUserByUserNameAndPassword(userName,password);
        ServletActionContext.getResponse().getWriter().print(111);
        return null;
    }

    public String register(){

        ManageUser user = userService.register(model);
        return "login";
    }

}
