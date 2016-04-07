package com.yoouman.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
@Repository("baseDao")
public class BaseDao<T>{
	/*
	 * 通过注入hibernateTemplate
	 */
	@Resource
	protected HibernateTemplate hibernateTemplate;
	/**
	 * 全局集合查询
	 * @param clazz	反射类
	 * @param id	串行id
	 * @return	反射类实体集合
	 */
	public List<T> findBylist(Class<T> clazz,int id) {
		@SuppressWarnings("unchecked")
		List<T> list=(List<T>) hibernateTemplate.get(clazz, id);
		return list;
	}
	
}
