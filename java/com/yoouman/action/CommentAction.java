package com.yoouman.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;

import com.yoouman.dao.CommentDao;
import com.yoouman.entity.Comment;

public class CommentAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="commentDao")
	private CommentDao dao;
	@Resource(name="mapper")
	private ObjectMapper mapper;
	private int productId;
	
	public String doInfo() throws Exception{
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Comment> comments=dao.findByProductId(productId);
		String string=mapper.writeValueAsString(comments);
		if(comments!=null){
			System.out.println(string);
			response.getWriter().print(string);
		}else{
			response.getWriter().print("none");
		}
		return "none";
	}

	public CommentDao getDao() {
		return dao;
	}

	public void setDao(CommentDao dao) {
		this.dao = dao;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
