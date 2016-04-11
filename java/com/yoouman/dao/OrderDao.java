package com.yoouman.dao;

import java.util.List;

import com.yoouman.entity.Orders;
import com.yoouman.entity.Page;

public interface OrderDao {
	/**
	 * 通过用户id查询所有订单列表
	 * @param userId	用户id
	 * @return	订单列表
	 */
	List<Orders> getListByUserId(int userId);
	/**
	 * 通过用户id分页查询订单列表
	 * @param userId	用户id
	 * @param page	页面参数
	 * @return	该页列表
	 */
	List<Orders> getListByUserIdForPage(int userId,Page<Orders> page);
	
	/**
	 * 通过订单id查询某个订单详细信息
	 * @param orderId	订单id
	 * @return	订单实例
	 */
	Orders getOrderByOrderId(int orderId);
	/**
	 * 通过订单编号查询订单详细信息
	 * @param orderNum	订单编号
	 * @return	订单实例
	 */
	Orders getOrderByOrderNum(String orderNum);
	/**
	 * 查询用户的订单数
	 * @param userId
	 * @return size	订单数量
	 */
	int getOrderNumByUserId(int userId);
	
}
