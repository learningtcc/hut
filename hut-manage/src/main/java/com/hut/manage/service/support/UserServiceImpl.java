package com.hut.manage.service.support;

import com.hut.common.service.RedisService;
import com.hut.common.utils.DigestUtils;
import com.hut.manage.dao.UserDao;
import com.hut.manage.pojos.ManageUser;
import com.hut.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jared on 2017/1/12.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Override
    public ManageUser getUserByUserNameAndPassword(String userName, String password) {
        return null;
    }

    @Override
    public ManageUser register(ManageUser model) {

        try {
            String md5password = DigestUtils.StringToHexMd5(model.getPassword());
            model.setPassword(md5password);
            model.setCreatedAt(new Date());
            userDao.save(model);
            /*if (u!=null) {
                *//*从redis里取*//*
                String user = redisService.get("user");
                System.out.println(user);
                return u;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
