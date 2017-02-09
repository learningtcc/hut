package com.hut.web.service.support;

import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.common.service.RedisService;
import com.hut.common.utils.JacksonUtils;
import com.hut.common.utils.Utils;
import com.hut.web.dao.UserMapper;
import com.hut.web.pojo.User;
import com.hut.web.service.UserService;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static sun.java2d.cmm.ColorTransform.In;

/**
 * Created by Jared on 2017/1/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RedisService redisService;

    @Override
    public User login(String userName, String password) {

        try {
            byte[] digest = MessageDigest.getInstance("md5").digest(password.getBytes("utf8"));
            String md5password = Utils.toMd5String(digest);

            User u = userMapper.selectByUserNameAndPassword(userName,md5password);
            if (u!=null) {
                /*从redis里取*/
                String user = redisService.get("user");
                System.out.println(user);
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
            String md5password = Utils.toMd5String(digest);
            user.setPassword(md5password);
            user.setCreatedAt(new Date());
            userMapper.save(user);

            //rabbitTemplate
            Connection connection = connectionFactory.newConnection();
            connection.
            /*存到redis里*/
            for(int i=0;i<4;i++){
            redisService.set("ab"+i, JacksonUtils.toJsonString(user),60*60);
            }
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
