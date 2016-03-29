package com.yoouman.dao;

import java.util.List;

import com.yoouman.entity.User;


public class UserDao extends  BaseDao<User>{
	
	
	public UserDao() {
		super();	
		super.setT(new User());
		//super.setHibernateTemplate((HibernateTemplate) MyApplicationContext.getApplicationContext().getBean("hibernateTemplate"));
	}
	public List<User> getObjByLogin(User u){
		 List<User> users=getHibernateTemplate().findByExample(u);
		return users;
	}

	public int saveUserByRegin(User u){
		int result=(int) getHibernateTemplate().save(u);
		return result;
	}
	/**
	 * 更新用户头像
	 * @param imageUrl	头像地址
	 * @param user	当前用户
	 * @return	Int
	 */
	public int UpdateUserImage(User user){
		//s.saveOrUpdate(user);
		super.getHibernateTemplate().update(user);
		return 1;
	}
}
