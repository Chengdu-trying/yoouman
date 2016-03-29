package com.yoouman.action;

import com.opensymphony.xwork2.Action;

public class TestAction implements Action{

	public String hello() {
		
		System.out.println(6666);
		return Action.SUCCESS;
		
	}
	
	@Override
	public String execute(){
		System.out.println(6666);
		return Action.SUCCESS;
	}
}
