package com.hut.web.service;

/**
 * Created by Jared on 2017/1/17.
 */

public abstract class BaseService<T> {

    /*@Autowired
    protected Mapper<T> mapper;

    public T select(int id){
        T t = mapper.selectByPrimaryKey(id);
        return t;
    }

    public int save(T entity){
        int id = mapper.insert(entity);
        return id;
    }

    public int update(T t){
        int i = mapper.updateByPrimaryKey(t);
        return i;
    }

    public int delete(T t){
        return this.update(t);
    }

    *//**
     * 单表分页查询
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    public List<T> selectPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(null);
    }*/

}
