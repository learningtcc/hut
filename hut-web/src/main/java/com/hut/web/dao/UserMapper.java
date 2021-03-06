package com.hut.web.dao;

import com.hut.web.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jared on 2016/12/16.
 */
public interface UserMapper {

    @Select("select * from user where userName =#{userName} and password=#{md5password}")
    User selectByUserNameAndPassword(@Param("userName") String userName,@Param("md5password") String md5password);

    @Insert("INSERT INTO `user` ( `createdAt`,`userName`, `password`, `phone`, `email` )" +
            " VALUES ( #{createdAt}, #{userName}, #{password}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void save(User user);

    @Select("select count(0) where userName =#{userName}")
    int selectByUserName(String userName);
}