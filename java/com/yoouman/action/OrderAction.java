package com.yoouman.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.yoouman.dao.OrderDao;
import com.yoouman.entity.Orders;
import com.yoouman.entity.Page;
import com.yoouman.entity.User;

@Controller("orderAction")@Scope("singleton")  
public class OrderAction extends BaseAction{
	@Resource
	private OrderDao dao;
	
	public String doLoadOrderList(){
		User u=(User) session.getAttribute("user");
		Page<Orders> page=new Page<Orders>();
		page.setPageCount(5);
		page.setCount(dao.getOrderNumByUserId(u.getUserId()));
		List<Orders> orders=dao.getListByUserIdForPage(u.getUserId(), page);
		
		return "success";
	}
}
