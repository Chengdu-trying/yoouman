package com.yoouman.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.org.apache.regexp.internal.recompile;
import com.yoouman.service.MatrixToImageWriter;

public class ActionHelp {

	public static String payFor(String url,String imgUrl){
		 System.err.println("into help"+imgUrl);
		 String text = url; // 二维码内容  
         int width = 300; // 二维码图片宽度  
         int height = 300; // 二维码图片高度  
         String format = "png";// 二维码的图片格式  
           
         Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>(); 
         hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
         hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码  
         hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数  
           
         BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         // 生成二维码  
         File outputFile = new File(imgUrl+"/youmanPay.png");
         try {
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return outputFile.getName();
	}
	
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
		if (!"".equals(location)&& location!=null) {		
			out.print("window.location.href='"+request.getContextPath()+"/"+location+"'</SCRIPT>");
		}
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
