package com.yoouman.action;


import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.yoouman.dao.UserDao;
import com.yoouman.entity.User;
import com.yoouman.util.ImageHelper;
import com.yoouman.util.UploadConfigurationRead;
import com.opensymphony.xwork2.Action;
/*
 * 1.接收表单的值
 * 	a.属性传值
 * 	b.对象传值
 * 	c.模型传值
 */
public class UserAction extends BaseAction{
	private static final long serialVersionUID = 5457268965108906071L;
	@Resource(name="userDao")
	private UserDao dao;
	
	private String name;	//登录用户
	private String pwd;		//登录密码		/注册
	
	private String email;
	private String gender;
	private String birthday;
	
	private String x;		//图片剪切需求参数
	private String y;
	private String w;
	private String h;
	private String realW;	//图片实际尺寸
	private String realH;
	/*
	 * 不在方法里面设置参数，是为了跟Servlet划清界限，解耦和是其替代方式
	 */
	public String doLogin() throws Exception{
		System.out.println(name+"//"+pwd+"///"+realW);		
    	User user=new User();
		user.setUserEmail(name);
		user.setUserPwd(pwd);
		
		//ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
//		String string1=MD5Util.md5Encode(name);
//		String string2=MD5Util.md5Encode(pwd);
//		System.out.println(string1);
//		System.out.println(string2);
		User u=dao.getObjByLogin(user).get(0);
		System.out.println(u);
		if(u!=null){			
			session.setAttribute("user", u);
			System.out.println("Success/n用户名:"+u.getUserName());
			return Action.SUCCESS;
		}else{
			session.setAttribute("msg", "登陆失败");
			System.out.println("fail");
			
			return Action.LOGIN;
		}
	}
	
	public String saveUserByRegisted() throws NumberFormatException, ParseException{
		
		System.out.println(pwd+"///"+email+"///"+name);
		System.out.println(gender+"///"+birthday);
		User user=new User(name, email, pwd, new SimpleDateFormat("yyyy-MM-dd").parse(birthday), Integer.parseInt(gender));
		if(dao.saveUserByRegin(user)>0){
			session.setAttribute("user", user);
			return Action.SUCCESS;
		}
		return Action.ERROR;
	}
	/**
	 * 保存头像
	 * @return
	 * @throws IOException	流未找到
	 */
	public String updateImage() throws IOException{
		File file=(File) session.getAttribute("fileImage");	//得到源文件
		
		Double fileX=ImageIO.read(file).getWidth()/Double.parseDouble(realW);	//缩放比例X轴
		Double fileY=ImageIO.read(file).getHeight()/Double.parseDouble(realH);	//缩放比例Y轴
		
		int newX=(int) (Double.parseDouble(x)*fileX);
		int newY=(int) (Double.parseDouble(y)*fileY);
		int newW=(int) (Double.parseDouble(w)*fileX);
		int newH=(int) (Double.parseDouble(h)*fileY);		//通过缩放比例计算实际剪切尺寸
		
		UserDao dao=new UserDao();
		User user=(User) session.getAttribute("user");		//得到用户对象
		
		String src=UploadConfigurationRead.getInstance()
				.getConfigItem("uploadFilePath").trim()
				+"/"
				+(String)session.getAttribute("realName");		//剪切文件存放位置
		user.setHeaderImg(src);		//头像文件名称
	    String type=(String) session.getAttribute("type");		//文件后缀，根据不同图片格式调用不同图片文件流
	    //调用工具类剪切图片
		ImageHelper.cutImage(file.getAbsolutePath(),file.getAbsolutePath(),newX,newY,newW,newH,type);
		//向数据库中存入数据
		if (dao.UpdateUserImage(user)>0) {			
			return "SaveSUCCESS";
		}
		return Action.ERROR;
	}
	/**
	 * 得到用户json数据
	 * @return
	 * @throws IOException 
	 */
	public String doGetUserObj() throws IOException{
		ObjectMapper mapper=new ObjectMapper();
		User user=(User) session.getAttribute("user");
		response.setContentType("text/html;charset=utf-8");
		if(user!=null){
			try {
				String json=mapper.writeValueAsString(user);
				System.out.println(json);
				response.getWriter().print(json);
			} catch (JsonGenerationException e) {
				// json序列化异常
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// json映射异常
				e.printStackTrace();
			}
		}else{
			response.getWriter().print(0);
		}
		return Action.NONE;
	}
	
	public String getUserJson(){
		User user=(User) session.getAttribute("user");
		System.out.println(user.getUserEmail());
		return Action.SUCCESS;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	public String getRealW() {
		return realW;
	}
	public void setRealW(String realW) {
		this.realW = realW;
	}
	public String getRealH() {
		return realH;
	}
	public void setRealH(String realH) {
		this.realH = realH;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public UserDao getDao() {
		return dao;
	}
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
}
