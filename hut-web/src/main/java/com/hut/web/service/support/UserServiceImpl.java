package com.hut.web.service.support;

import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.common.service.RedisService;
import com.hut.common.utils.JacksonUtils;
import com.hut.web.dao.UserMapper;
import com.hut.web.pojo.User;
import com.hut.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Date;

/**
 * Created by Jared on 2017/1/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public User login(String userName, String password) {

        try {
            byte[] digest = MessageDigest.getInstance("md5").digest(password.getBytes("utf8"));
            String md5password = new String(digest, "utf8");

            User u = userMapper.selectByUserNameAndPassword(userName,md5password);
            if (u!=null) {
                /*存到redis里*/
                /*redisService.set("user", JacksonUtils.toJsonString(u),60*60);*/
                return u;
            }
            throw new MsgException(Msg.UNKNOW,"登录失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MsgException(Msg.UNKNOW,"登录失败");
    }

    @Override
    public User register(User user) {
        try {
            byte[] digest = MessageDigest.getInstance("md5").digest(user.getPassword().getBytes("utf8"));
            String md5password = new String(digest, "utf8");
            user.setPassword(md5password);
            user.setCreatedAt(new Date());

            userMapper.save(user);
            //rabbitTemplate
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MsgException(Msg.UNKNOW,"注册失败");
    }

    @Override
    public boolean userNameIsExist(String userName) {
        int count = userMapper.selectByUserName(userName);
        return count>0?true:false;
    }
}
