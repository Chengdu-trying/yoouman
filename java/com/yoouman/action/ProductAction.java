package com.yoouman.action;

import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Product;
@Controller("productAction")@Scope("singleton")  
public class ProductAction extends BaseAction{
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
		return "none";
	}

	public String doInfo() throws Exception{
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String pid=request.getParameter("pId");
		//查询单个产品
		System.out.println(pid);
		Product product=dao.getProductById(Integer.parseInt(pid));
		if (product==null) {
			response.getWriter().print(mapper.writeValueAsString("none"));
		}else{
			session.setAttribute("product", product);
			String json=mapper.writeValueAsString(product);
			response.getWriter().print(json);
			System.out.println(json);
		}
		return "none";
	}
	
}
