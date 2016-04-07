package com.yoouman.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.yoouman.util.JsonToObj;

@Entity
@Table(name="uac_user")
@JsonIgnoreProperties(value={"userPwd","products","list_product","orders","comments"})
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2533025785429130375L;
	//用户编号
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)	//针对不同数据库自增长
	private int userId;
	//昵称
	@Column(name="user_name")
	private String userName;
	//登录用户名/电子邮箱
	@Column(name="user_email")
	private String userEmail;
	//用户密码
	@Column(name="user_pwd")
	private String userPwd;
	//头像地址
	@Column(name="user_hederImg")
	private String headerImg;
	//出生日期
	@Column(name="user_borndate")
	@Temporal(TemporalType.DATE)
	private Date borndate;
	//性别	1：男  0：女  2:不详
	@Column(name="user_gender")
	private int gender;
	//地址
	@Column(name="user_location")
	private String location;
	//个人订单列表
	@OneToMany(mappedBy="owner",cascade={CascadeType.ALL})
	private Set<Orders> orders;
	//个人评论列表
	@OneToMany(mappedBy="owner",cascade={CascadeType.ALL})
	private Set<Comment> comments;
	//个人商品收藏列表id列表
	@Column(name="products",columnDefinition="longtext")
	private String products;
	
	@Transient	//忽略
	private List<Product> list_product;
	
	
	public List<Product> getList_product() {
		return list_product;
	}

	public void setList_product(List<Product> list_product) {
		this.list_product = list_product;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String userEmail, String userPwd, String headerImg, Date borndate,
			int gender, String location, Set<Orders> orders, Set<Comment> comments, String products) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.headerImg = headerImg;
		this.borndate = borndate;
		this.gender = gender;
		this.location = location;
		this.orders = orders;
		this.comments = comments;
		this.products = products;
	}

	public User(String userName, String userEmail, String userPwd, Date borndate, int gender) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.borndate = borndate;
		this.gender = gender;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public Date getBorndate() {
		return borndate;
	}

	public void setBorndate(Date borndate) {
		this.borndate = borndate;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getProducts() {
		return products;
	}

	/**
	 * 对str字符串进行加工
	 * @param products
	 */
	public void setProducts(String products) {		
		if(products!=null){
			this.products = products;
			//订单商品列表
			try {
				this.list_product=JsonToObj.jacksonToCollection(products, ArrayList.class, Product.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}