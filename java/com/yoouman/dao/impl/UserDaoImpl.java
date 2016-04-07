package com.yoouman.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yoouman.dao.BaseDao;
import com.yoouman.dao.UserDao;
import com.yoouman.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@SuppressWarnings("unchecked")
	@Override
	public User login(String name, String pwd) {
		List<User> users= hibernateTemplate.find("from User u where u.userEmail=? and u.userPwd=?",new Object[]{name,pwd});		
		System.out.println(users.size()+"-------------");
		return (users==null || users.size()==0)?null:(User)users.get(0);
	}

	@Override
	public int saveUserByRegin(User user) {
		int result=(int)hibernateTemplate.save(user);
		return result;
	}

	@Override
	public int updateUserImage(User user) {
		hibernateTemplate.update(user);
		return 1;
	}

	
}
