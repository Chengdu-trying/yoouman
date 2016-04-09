package com.yoouman.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.Action;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Product;

public class ShoppingCarAction extends BaseAction {
	@Resource(name = "productDao")
	private ProductDao productDao;
	@Resource(name = "mapper")
	private ObjectMapper mapper;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> shoppingList = new ArrayList<Product>();

	public List<Product> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<Product> shoppingList) {
		this.shoppingList = shoppingList;
	}

	// 商品添加至购物车
	public String add() {
		// 获取商品id
		String id = request.getParameter("productId");
		System.out.println(id);
		Product pro = productDao.getProductById(Integer.parseInt(id));
		System.out.println(pro.getpId());

		for (Product product : shoppingList) {
			if (product.getpId() == pro.getpId()) {
				System.out.println("该商品已经在购物车中!");
				return Action.SUCCESS;
			}
		}

		shoppingList.add(pro);
		// 根据id获取商品信息
		System.out.println("添加成功!");
		return Action.SUCCESS;
	}

	// 获取购物车
	public String getList() throws Exception {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		System.out.println("get");

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
		
		for (Product product : shoppingList) {
			if (product.getpId() == Integer.parseInt(id)) {
				int index = shoppingList.indexOf(product);
				shoppingList.remove(index);
				System.out.println("删除成功!");
				return Action.SUCCESS;
			}
		}
		return Action.SUCCESS;
	}
}
