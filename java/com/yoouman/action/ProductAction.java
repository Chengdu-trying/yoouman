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
import com.yoouman.util.ActionHelp;
import com.yoouman.util.UploadConfigurationRead;
@Controller("productAction")@Scope("singleton")  
public class ProductAction extends BaseAction{
	@Resource(name="productDao")
	private ProductDao dao;
	@Resource(name="mapper")
	private ObjectMapper mapper;
	
	
	public String getList(){
		// TODO 向页面发送数据
		ActionHelp.setPageEcoding(response,request);
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
		ActionHelp.setPageEcoding(response,request);
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
		ActionHelp.setPageEcoding(response,request);
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
		ActionHelp.setPageEcoding(response,request);
		String count=UploadConfigurationRead.getInstance().getConfigItem("SearchShowCount").trim();
		String pageIndex=request.getParameter("pageIndex");
		String keywords=request.getParameter("keywords");
		Page<Product> page=null;
		System.err.println("keywords:"+keywords);
		if( !keywords.equals("全部商品") && !keywords.equals("") && keywords != null){
			page=dao.getPageByKeyWords(cutAndAppend(keywords),Integer.parseInt(count), Integer.parseInt(pageIndex));
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
	
	public String cutAndAppend(String str){
		char[] strs=str.trim().toCharArray();
		StringBuffer newStr = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			newStr.append("%"+strs[i]);
		}	
		return newStr.append("%").toString();	
	}
}
