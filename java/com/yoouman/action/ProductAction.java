package com.yoouman.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.tomcat.jni.Mmap;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;
import com.yoouman.util.UploadConfigurationRead;
@Controller("productAction")@Scope("singleton")  
public class ProductAction extends BaseAction{
	@Resource(name="productDao")
	private ProductDao dao;
	@Resource(name="mapper")
	private ObjectMapper mapper;
	
	
	public String getList(){
		// TODO 向页面发送数据
		setPageEcoding();
		List<Product> products=dao.getIndexlist();
		String string="";
		try {
			string = mapper.writeValueAsString(products);
			response.getWriter().print(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(string);
		session.setAttribute("products", products);
		return "none";
	}

	public String doInfo(){
		setPageEcoding();
		String pid=request.getParameter("pId");
		//查询单个产品
		System.out.println(pid);
		Product product=dao.getProductById(Integer.parseInt(pid));
		try {
			if (product==null) {
				response.getWriter().print(mapper.writeValueAsString("none"));
			}else{
				session.setAttribute("product", product);
				String json=mapper.writeValueAsString(product);
				response.getWriter().print(json);
				System.out.println(json);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
	}
	public String doHotInfo(){
		setPageEcoding();
		List<Product> list=dao.getListByType(6);
		try {
			response.getWriter().println(mapper.writeValueAsString(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
	}
	public String doSearchByKeyWordsForPage(){
		setPageEcoding();
		String count=UploadConfigurationRead.getInstance().getConfigItem("SearchShowCount").trim();
		String pageIndex=request.getParameter("pageIndex");
		String keywords=request.getParameter("keywords");
		Page<Product> page=null;
		System.out.println("keywords:"+pageIndex);
		if(keywords!="全部商品" && keywords != "" && keywords != null){
			page=dao.getPageByKeyWords("%"+keywords+"%",Integer.parseInt(count), Integer.parseInt(pageIndex));
		}else{
			page=dao.getPageForAllList(Integer.parseInt(count), Integer.parseInt(pageIndex));
		}
		try {
			response.getWriter().println(mapper.writeValueAsString(page));
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
