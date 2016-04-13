package com.yoouman.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.yoouman.dao.UserDao;
import com.yoouman.entity.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateTemplate implements UserDao{

	@SuppressWarnings("unchecked")
	@Override
	public User login(String name, String pwd) {
		List<User> users= find("from User u where u.userEmail=? and u.userPwd=?",new Object[]{name,pwd});		
		return (users==null || users.size()==0)?null:(User)users.get(0);
	}

	@Override
	public int saveUserByRegin(User user) {
		int result=(int)save(user);
		return result;
	}

	@Override
	public int updateUserImage(User user) {
		update(user);
		return 1;
	}

	
}
