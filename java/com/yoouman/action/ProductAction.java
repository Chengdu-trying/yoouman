package com.yoouman.action;

import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.Action;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Product;

public class ProductAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="productDao")
	private ProductDao dao;
	@Resource(name="mapper")
	private ObjectMapper mapper;
	
	
	public String getList() throws Exception {
		// TODO 向页面发送数据
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Product> products=dao.getIndexlist();
		String string=mapper.writeValueAsString(products);
		response.getWriter().print(string);
		System.out.println(string);
		session.setAttribute("products", products);
		return Action.NONE;
	}

	public String doInfo() throws Exception{
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String pid=request.getParameter("productId");
		System.out.println(pid);
		String json=mapper.writeValueAsString(dao.getProductById(Integer.parseInt(pid)));
		response.getWriter().print(json);
		System.out.println(json);
		return Action.NONE;
	}
	public ProductDao getDao() {
		return dao;
	}

	public void setDao(ProductDao dao) {
		this.dao = dao;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
}
