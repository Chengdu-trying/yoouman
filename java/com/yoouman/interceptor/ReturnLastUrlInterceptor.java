package com.yoouman.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.yoouman.entity.User;

public class ReturnLastUrlInterceptor implements WebRequestInterceptor{

	@Override
	public void preHandle(WebRequest request) throws Exception {
		// TODO Auto-generated method stub
		String loginLastUrl=request.getHeader("Referer");
		System.err.println(loginLastUrl);
		HttpSession session=((HttpServletRequest)request).getSession();
		System.out.println("拦截器2在这里");
		session.setAttribute("loginLastUrl", loginLastUrl);
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
