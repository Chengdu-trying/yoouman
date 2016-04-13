package com.yoouman.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ObjectMapper mapper;
	
	public String doLoadOrderList(){
		User u=(User) session.getAttribute("user");
		Page<Orders> page=new Page<Orders>();
		page.setPageCount(5);
		page.setCount(dao.getOrderNumByUserId(u.getUserId()));
		List<Orders> orders=dao.getListByUserIdForPage(u.getUserId(), page);
		for (Orders orders2 : orders) {
			System.out.println(orders2.getOrderNum());
		}
		session.setAttribute("ordersList", orders);
		try {
			response.getWriter().print(mapper.writeValueAsString(orders));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
