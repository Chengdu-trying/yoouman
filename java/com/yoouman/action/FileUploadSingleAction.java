package com.yoouman.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.http.fileupload.FileItem;

public class FileUploadSingleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String uploadFile() throws IOException{
		 String path = request.getSession().getServletContext().getRealPath("/image");
	        System.out.println(path);
	        File file = new File(path);
	        if (!file.exists())
	            file.mkdirs();
	        String fileName = "";// 文件名称

	        /**上传文件处理内容**/
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload sfu = new ServletFileUpload(factory);
	        sfu.setHeaderEncoding("UTF-8"); // 处理中文问题
	        sfu.setSizeMax(1024 * 1024); // 限制文件大小
	        try {
	        	System.out.println(request);
	            List fileItems = sfu.parseRequest(request); // 解码请求
	           
	            for (Object fd : fileItems) {
	            	System.out.println("into for");
	            	FileItem fi=(FileItem)fd;
	                fileName = UUID.randomUUID()+fi.getName().substring(fi.getName().lastIndexOf("."),fi.getName().length());
	                fi.write(new File(path, fileName));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        /**********************/

	        //获取图片url地址
	        String imgUrl = "image/" + fileName;
	        System.out.println(fileName+"s");
	        response.setContentType("text/text;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.print(imgUrl);  //返回url地址
	        out.flush();
	        out.close();
		return NONE;
	}

	
}
