package com.hut.manage.dao.base.support;

import com.hut.common.struct.PageBean;
import com.hut.manage.dao.base.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by Jared on 2017/1/16.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

    //实体的类型
    private Class<T> entityClass;

    //注入sessionFactory对象
    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 在构造方法中动态获得实体类型
     */
    public BaseDaoImpl() {
        //获得父类（BaseDaoImpl）类型
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得父类上定义的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    public List<T> findAll() {
        String hql = "FROM " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    /**
     * 通用更新方法
     */
    public void executeUpdate(String queryName, Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        //命名查询
        Query query = session.getNamedQuery(queryName);
        //int length = objects.length; ？个数
        int i = 0;
        for (Object object : objects) {
            query.setParameter(i++, object);
        }
        //执行更新
        query.executeUpdate();
    }

    /**
     * 通用分页查询
     */
    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        //查询total---总记录数:   修改框架发出的sql形式 select count(id) from bc_staff;
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long total = list.get(0);
        pageBean.setTotal(total.intValue());
        //查询rows----当前页要展示的数据集合:    修改sql形式select * from ....
        detachedCriteria.setProjection(null);
        //修改映射关系将Staff类和bc_staff 表建立对应关系
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        //从哪开始查询
        int firstResult = (currentPage - 1) * pageSize;
        //查询几条数据
        int maxResults = pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult , maxResults );
        pageBean.setRows(rows);
    }

    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * 根据条件查询
     */
    public List<T> findByCriteria(DetachedCriteria dc) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
    }
}
