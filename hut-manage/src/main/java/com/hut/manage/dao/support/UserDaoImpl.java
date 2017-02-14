package com.hut.manage.dao.support;

import com.hut.common.struct.PageBean;
import com.hut.manage.dao.UserDao;
import com.hut.manage.pojos.ManageUser;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jared on 2017/1/12.
 */

@Repository
public class UserDaoImpl implements UserDao<ManageUser>{

    @Override
    public void save(ManageUser entity) {

    }

    @Override
    public void update(ManageUser entity) {

    }

    @Override
    public void delete(ManageUser entity) {

    }

    @Override
    public ManageUser findById(Serializable id) {
        return null;
    }

    @Override
    public List<ManageUser> findAll() {
        return null;
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {

    }

    @Override
    public void pageQuery(PageBean pageBean) {

    }

    @Override
    public void saveOrUpdate(ManageUser entity) {

    }

    @Override
    public List<ManageUser> findByCriteria(DetachedCriteria dc) {
        return null;
    }
}
