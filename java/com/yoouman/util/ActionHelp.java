package com.yoouman.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.regexp.internal.recompile;

public class ActionHelp {

	public static void alert(HttpServletResponse response,HttpServletRequest request,String msg,String location){
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>订单管理</TITLE></HEAD>");
		out.print("<SCRIPT TYPE='text/javascript'>alert('"+msg+"');");
		out.print("window.location.href='"+request.getContextPath()+"/"+location+"'</SCRIPT>");
		out.print("</SCRIPT>");
		out.println("  <BODY>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	/**
	 * 页面编码设置
	 * @param response
	 * @param request
	 */
	public static void setPageEcoding(HttpServletResponse response,HttpServletRequest request){
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
