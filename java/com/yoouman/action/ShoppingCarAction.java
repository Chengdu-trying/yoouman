package com.yoouman.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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
		setPageEcoding();
		// 获取商品id
		String id = request.getParameter("productId");
		String pBuyCount=request.getParameter("pBuyCount");
		Product pro = productDao.getProductById(Integer.parseInt(id));
		pro.setpBuyCount(Integer.parseInt(pBuyCount));
		System.out.println("购买数量"+pro.getpBuyCount());
		if(shoppingList.getProducts_buy().size()>0){
			for (Product product : shoppingList.getProducts_buy()) {
				if (product.getpId() == pro.getpId()) {
					System.out.println("该商品已经在购物车中!");
					try {
						response.getWriter().print("repeat");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return "none";
				}
			}
		}
		shoppingList.getProducts_buy().add(pro);
		// 根据id获取商品信息
		try {
			response.getWriter().println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
	}
	// 获取购物车
	public String getList(){
		setPageEcoding();
		String string;
		try {
			string = mapper.writeValueAsString(shoppingList);
			response.getWriter().print(string);
			System.out.println(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("shoppingList", shoppingList);
		return Action.NONE;
	}

	// 删除
	public String delete() {
		setPageEcoding();
		// 获取商品id
		String id=request.getParameter("productId");
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
	
	public void setPageEcoding(){
		response.setContentType("text/html");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
	}
	
}