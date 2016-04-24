package com.yoouman.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.yoouman.dao.OrderDao;
import com.yoouman.entity.Orders;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;
import com.yoouman.entity.ShoppingCar;
import com.yoouman.entity.User;
import com.yoouman.util.ActionHelp;
import com.yoouman.util.JsonToObj;

@Controller("orderAction")@Scope("singleton")  
public class OrderAction extends BaseAction{
	@Resource
	private OrderDao dao;
	@Autowired
	private ObjectMapper mapper;

	private String order;
	public String doLoadOrderList(){
		ActionHelp.setPageEcoding(response, request);
		User u=(User) session.getAttribute("user");
		if(u==null){
			ActionHelp.alert(response,request,"对不起！您还没有登录","Login.html");
			return "login";
		}
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
	
	public String saveNewOrder(){
		ActionHelp.setPageEcoding(response, request);
		System.err.println(order);
		User user=(User) session.getAttribute("user");
		ShoppingCar shoppingCar=(ShoppingCar) session.getAttribute("shoppingCar");
		if(shoppingCar!=null){
			try {
				System.err.println(mapper.writeValueAsString(shoppingCar));
				Orders orders=new Orders();
				orders.setLocation(user.getLocation());
				//订单号
				Date date=new Date();
				int d=(int)(Math.random()*1000000);
				orders.setCreateDate(date);	
				String order_num="YM"+date.getTime()+user.getUserId()+String.valueOf(d);
				orders.setOrderNum(order_num);
				orders.setOrderStatus(0);
				orders.setOwner(user);
				orders.setTotalPrice(shoppingCar.getPrice());
				if(shoppingCar.getProducts_buy()!=null && shoppingCar.getProducts_buy().size()>0){
					orders.setProducts_str(mapper.writeValueAsString(shoppingCar.getProducts_buy()));
				}
				int rusult=dao.saveNewOrder(orders);
				if(rusult>0){
					response.getWriter().print(1);
				}else{
					response.getWriter().print(0);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "none";
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	//打印request头
//	Enumeration names = request.getHeaderNames();
//    System.out.println("===================================================================");
//      while(names.hasMoreElements()){
//        String name = (String) names.nextElement();
//            System.err.println(name + ":" + request.getHeader(name));
//      }
//    System.err.println("now service sessionid :"+session.getId());
//    System.out.println("===================================================================");
//    Enumeration e = request.getParameterNames();
//    while(e.hasMoreElements()){
//      String name = (String) e.nextElement();
//      String value = request.getParameter(name);
//      System.err.println(name+"="+value);
//    }
}
