package com.yoouman.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Product;
import com.yoouman.entity.ShoppingCar;

@Controller("shoppingCarAction")@Scope("singleton")  
public class ShoppingCarAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "productDao")
	private ProductDao productDao;
	@Resource(name = "mapper")
	private ObjectMapper mapper;

	private ShoppingCar shoppingList=new ShoppingCar();


	// 商品添加至购物车
	public String addProduct() {
		// 获取商品id
		String id = request.getParameter("productId");
		Product pro = productDao.getProductById(Integer.parseInt(id));
		for (Product product : shoppingList.getProducts_buy()) {
			if (product.getpId() == pro.getpId()) {
				System.out.println("该商品已经在购物车中!");
				return Action.SUCCESS;
			}
		}
		shoppingList.getProducts_buy().add(pro);
		// 根据id获取商品信息
		System.out.println("添加成功!");
		return Action.SUCCESS;
	}

	// 获取购物车
	public String getList() throws Exception {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String string = mapper.writeValueAsString(shoppingList);
		response.getWriter().print(string);
		System.out.println(string);
		session.setAttribute("shoppingList", shoppingList);
		return Action.NONE;
	}

	// 删除
	public String delete() {

		// 获取商品id
		String id = request.getParameter("productId");
		
		for (Product product : shoppingList.getProducts_buy()) {
			if (product.getpId() == Integer.parseInt(id)) {
				int index = shoppingList.getProducts_buy().indexOf(product);
				shoppingList.getProducts_buy().remove(index);
				System.out.println("删除成功!");
				return Action.SUCCESS;
			}
		}
		return Action.SUCCESS;
	}
}