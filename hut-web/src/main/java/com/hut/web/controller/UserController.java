package com.hut.web.controller;

import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.web.pojo.User;
import com.hut.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Jared on 2017/1/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public User login(@Valid User user, BindingResult result, HttpServletRequest rq){
        if (!userNameIsExist(user.getUserName()))
            throw new MsgException(Msg.PARAMERROR,"用户名已存在");
        if (result.hasErrors())
            throw new MsgException(Msg.PARAMERROR,result.getFieldError().toString());
        User u = userService.login(user.getUserName(),user.getPassword());
        rq.getSession().setAttribute("User", user);
        return u;
    }

    @PostMapping("/register")
    @ResponseBody
    public User register(@Valid User user,BindingResult result,HttpServletRequest rq){
        if (result.hasErrors())
            throw new MsgException(Msg.PARAMERROR,result.getFieldError().toString());
        User u = userService.register(user);
        rq.getSession().setAttribute("User", user);
        return u;
    }

    private boolean userNameIsExist(String userName){
        boolean flag = userService.userNameIsExist(userName);
        return flag;
    }
}
