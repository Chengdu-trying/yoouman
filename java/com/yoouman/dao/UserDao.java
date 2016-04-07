package com.yoouman.dao;

import com.yoouman.entity.User;


public interface UserDao{
	/**
	 * 登录方法
	 * @param name	用户Email
	 * @param pwd	用户密码
	 * @return 		用户对象
	 */
	User login(String name,String pwd);
	/**
	 * 注册方法
	 * @param user	用户对象
	 * @return	数据库插入值行号，>0 则注册成功
	 */
	int saveUserByRegin(User user);
	/**
	 * 更新用户头像/用户基本信息
	 * @param user	用户对象
	 * @return	
	 */
	int updateUserImage(User user);
	
}
