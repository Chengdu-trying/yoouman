package com.yoouman.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.annotation.Resource;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yoouman.dao.CommentDao;
import com.yoouman.entity.Comment;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;
import com.yoouman.entity.User;
@Controller("commentAction")@Scope("singleton")
public class CommentAction extends BaseAction{
	@Resource(name="commentDao")
	private CommentDao dao;
	@Resource(name="mapper")
	private ObjectMapper mapper;
	
	
	private String context;
	
	public String doInfo(){
		setPageEncoding();
		String pId=request.getParameter("pId");
		String pageIndex=request.getParameter("pageIndex");
		System.err.println("productId:"+pId+"pageIndex:"+pageIndex);	
		Page<Comment> page=dao.getListForPageByPId(Integer.parseInt(pId),Integer.parseInt(pageIndex),5);
		String string="";
		try {
			string = mapper.writeValueAsString(page);
			if(page.getList()!=null){
				response.getWriter().print(string);			
			}else{
				response.getWriter().print("none");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
	}
	public String saveNewComment(){
		setPageEncoding();
		System.out.println(context);
		try {
			if(context!=null){
				Comment comment=new Comment();
				comment.setcContent(context);
				comment.setcDate(new Date());
				comment.setOwner((User) session.getAttribute("user"));
				comment.setProduct((Product) session.getAttribute("product"));
				int result=dao.saveNewComment(comment);			
				if (result>0) {
					System.err.println();
						response.getWriter().print(mapper.writeValueAsString(comment));
					return "none";
				}else{
					response.getWriter().print("fail");
				}
			}else {
				response.getWriter().print("内容为空！发布失败。");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	/**
	 * 设置页面编码，防止中文乱码
	 * @throws UnsupportedEncodingException
	 */
	public void setPageEncoding(){
		response.setContentType("text/html");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
}
