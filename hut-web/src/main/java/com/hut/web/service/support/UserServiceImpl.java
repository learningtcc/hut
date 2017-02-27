package com.hut.web.service.support;

import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.common.service.RedisService;
import com.hut.common.utils.DigestUtils;
import com.hut.common.utils.JacksonUtils;
import com.hut.web.dao.UserMapper;
import com.hut.web.pojo.User;
import com.hut.web.service.UserService;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jared on 2017/1/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    //@Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public User login(String userName, String password) {

        try {
            String md5password = DigestUtils.StringToHexMd5(password);

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
            String md5password = DigestUtils.StringToHexMd5(user.getPassword());
            user.setPassword(md5password);
            user.setCreatedAt(new Date());
            userMapper.save(user);

            //发送消息
            //this.rabbitTemplate.sendAndReceive("sasa","ss");

            /*存到redis里*/
            //redisService.set("rabbitmqUser", JacksonUtils.toJsonString(user),60*60);

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
