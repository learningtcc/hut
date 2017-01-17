package com.hut.manage.dao.base;

import com.hut.common.struct.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jared on 2017/1/16.
 */
public interface BaseDao<T> {

    public void save(T entity);
    public void update(T entity);
    public void delete(T entity);
    public T findById(Serializable id);
    public List<T> findAll();
    public void executeUpdate(String queryName,Object...objects);
    public void pageQuery(PageBean pageBean);
    public void saveOrUpdate(T entity);
    public List<T> findByCriteria(DetachedCriteria dc);

}
