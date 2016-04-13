package com.yoouman.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yoouman.entity.User;

public class UserInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session=invocation.getInvocationContext().getSession();
		User user=(User) session.get("user");
		System.out.println("拦截器在这里");
		if (user!=null) {
			return invocation.invoke();
		}
		return "login";
	}


}
