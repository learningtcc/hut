package com.hut.manage.action.base;

import com.hut.common.struct.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 表现层通用抽取
 * 
 * @author jared
 * 
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	public static final String HOME = "home";
	public static final String LIST = "list";

	public PageBean pageBean = new PageBean();

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	// 创建一个离线条件查询对象，可以封装过滤条件: DetachedCriteria.forClass(Staff.class);
	DetachedCriteria detachedCriteria = null;

	// 定义模型对象
	protected T model;

	// 返回模型对象
	public T getModel() {
		return model;
	}

	/**
	 * 动态获得模型对象的类型，通过反射创建模型对象
	 */
	public BaseAction() {

		// 获得父类的类型（BaseAction） (ParameterizedType) this.getClass().getGenericSuperclass()
		ParameterizedType genericSuperclass = null;
		
		Type clazz = this.getClass().getGenericSuperclass();

		if(clazz instanceof ParameterizedType){
			genericSuperclass = (ParameterizedType) clazz;
		}else{
			//使用了shiro的注解，当前this为Action的代理对象
			genericSuperclass = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}
		
		// 获得BaseAction上声明的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		// 实体类型
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];

        // 初始化离线条件查询对象
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);

		// 通过反射创建model对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
