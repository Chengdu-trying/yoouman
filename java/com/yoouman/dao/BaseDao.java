package com.yoouman.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao<T> extends HibernateDaoSupport{
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public List<T> findBylist() {
		List<T> list=super.getHibernateTemplate().find("from "+t.getClass().getSimpleName());
		return list;
	}
}
