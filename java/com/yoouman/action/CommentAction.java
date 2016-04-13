package com.yoouman.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yoouman.dao.CommentDao;
import com.yoouman.entity.Comment;
import com.yoouman.entity.Product;
import com.yoouman.entity.User;
@Controller("commentAction")@Scope("singleton")
public class CommentAction extends BaseAction{
	@Resource(name="commentDao")
	private CommentDao dao;
	@Resource(name="mapper")
	private ObjectMapper mapper;
	
	
	private String context;
	
	public String doInfo() throws Exception{
		System.out.println("11111111111111111");
		setPageEncoding();
		String pId=request.getParameter("pId");
		System.out.println(pId);
		List<Comment> comments=dao.findByProductId(Integer.parseInt(pId));
		String string=mapper.writeValueAsString(comments);
		if(comments!=null){
			response.getWriter().print(string);
		}else{
			response.getWriter().print("none");
		}
		return "none";
	}
	public String saveNewComment() throws IOException{
		setPageEncoding();
		System.out.println(context);
		if(context!=null){
			Comment comment=new Comment();
			comment.setcContent(context);
			comment.setcDate(new Date());
			comment.setOwner((User) session.getAttribute("user"));
			comment.setProduct((Product) session.getAttribute("product"));
			int result=dao.saveNewComment(comment);
			if (result>0) {
				System.err.println();
				response.getWriter().print("success");
				return "none";
			}else{
				response.getWriter().print("fail");
			}
		}else {
			response.getWriter().print("内容为空！发布失败。");
		}
		return "fail";
	}
	/**
	 * 设置页面编码，防止中文乱码
	 * @throws UnsupportedEncodingException
	 */
	public void setPageEncoding() throws UnsupportedEncodingException{
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
}
