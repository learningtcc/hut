package com.hut.manage.dao.support;

import com.hut.manage.dao.UserDao;
import com.hut.manage.dao.base.support.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Jared on 2017/1/12.
 */

@Repository
public class UserDaoImpl<T> extends BaseDaoImpl<T> implements UserDao<T>{

}
