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
	private ShoppingCar shoppingCar=new ShoppingCar();


	// 商品添加至购物车
	public String addProduct() {
		setPageEcoding();
		// 获取商品id
		String id = request.getParameter("productId");
		String pBuyCount=request.getParameter("pBuyCount");
		Product pro = productDao.getProductById(Integer.parseInt(id));
		pro.setpBuyCount(Integer.parseInt(pBuyCount));
		System.out.println("购买数量"+pro.getpBuyCount());
		if(shoppingCar.getProducts_all().size()>0){
			for (Product product : shoppingCar.getProducts_all()) {
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
		shoppingCar.getProducts_all().add(pro);
		shoppingCar.getProducts_buy().add(pro);
		try {
			System.err.println(mapper.writeValueAsString(shoppingCar));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("shoppingCar", shoppingCar);
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
			string = mapper.writeValueAsString(shoppingCar);
			response.getWriter().print(string);
			System.out.println(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("shoppingCar", shoppingCar);
		return Action.NONE;
	}

	// 删除
	public String delete() {
		setPageEcoding();
		// 获取商品id
		String id=request.getParameter("productId");
		for (Product product : shoppingCar.getProducts_all()) {
			if (product.getpId() == Integer.parseInt(id)) {
				int index1 = shoppingCar.getProducts_all().indexOf(product);
				int index2 = shoppingCar.getProducts_buy().indexOf(product);
				shoppingCar.getProducts_all().remove(index1);
				if(index2!=-1){
					shoppingCar.getProducts_buy().remove(index2);
				}
				System.out.println("删除成功!");
				session.setAttribute("shoppingCar", shoppingCar);
				return Action.SUCCESS;
			}
		}
		return Action.SUCCESS;
	}
	public String num(){
		setPageEcoding();
		String pid=request.getParameter("productId");
		String num = request.getParameter("num");
		for (Product product : shoppingCar.getProducts_buy()) {
			if (product.getpId() == Integer.parseInt(pid)) {	
				int index2 = shoppingCar.getProducts_buy().indexOf(product);
				if(index2!=-1){
					shoppingCar.getProducts_buy().get(index2).setpBuyCount(Integer.parseInt(num));
				}
				System.err.println("数量添加成功!");
				try {
					System.err.println(shoppingCar.getProducts_buy().get(index2).getpBuyCount());
					response.getWriter().println(mapper.writeValueAsString(shoppingCar.getProducts_buy()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "none";
			}
		}
		return "none";
	}
	
	public String check(){
		setPageEcoding();
		// 获取商品id
		String id=request.getParameter("productId");
		for (Product product : shoppingCar.getProducts_buy()) {
			if (product.getpId() == Integer.parseInt(id)) {
				int index2 = shoppingCar.getProducts_buy().indexOf(product);
				shoppingCar.getProducts_buy().remove(index2);
				shoppingCar.setSize(shoppingCar.getProducts_buy().size());
				shoppingCar.getPrice();
				System.err.println("勾选成功!");
				try {
					System.err.println(mapper.writeValueAsString(shoppingCar));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("shoppingCar", shoppingCar);
				return "none";
			}
		}
		return "none";
	}
	public String checkadd(){
		setPageEcoding();
		// 获取商品id
		String id = request.getParameter("productId");
		Product pro = productDao.getProductById(Integer.parseInt(id));
		if(shoppingCar.getProducts_buy().size()>0){
			for (Product product : shoppingCar.getProducts_buy()) {
				if (product.getpId() == pro.getpId()) {
					System.err.println("该商品已经在购买选单中!");
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
		shoppingCar.getProducts_buy().add(pro);
		shoppingCar.setSize(shoppingCar.getProducts_buy().size());
		shoppingCar.getPrice();
		try {
			System.err.println(mapper.writeValueAsString(shoppingCar));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("shoppingCar", shoppingCar);
		// 根据id获取商品信息
		try {
			response.getWriter().println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
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