package com.yoouman.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.yoouman.util.CustomDateSerializer;
import com.yoouman.util.JsonToObj;

@Entity
@Table(name = "orders")
public class Orders {
	// 排序编号
	@Id
	@Column(name = "order_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	// 订单编号
	@Column(name = "order_Num")
	private String orderNum;
	// 订单状态
	@Column(name = "order_Status")
	private int orderStatus;
	// 订单创建日期
	@Column(name = "createDate")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createDate;
	// 付款日期
	@Column(name = "payDate")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date payDate;
	// 完成收货日期
	@Column(name = "compDate")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date compDate;
	// 订单总价
	@Column(name = "totalPrice")
	private Double totalPrice;
	// 下单人
	@ManyToOne(cascade = { CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "user_Id")
	private User owner;
	// 订单 配送地址
	@Column(name = "order_location")
	private String location;
	// 订单商品列表
	@Column(name="products_str")
	private String products_str;
	//忽略到数据库
	@Transient
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getProducts_str() {
		return products_str;
	}

	public void setProducts_str(String products_str) {
		if(products_str!=null){
			this.products_str = products_str;
			//订单商品列表
			try {
				this.products=JsonToObj.jacksonToCollection(products_str, ArrayList.class, Product.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	
	public Date getCompDate() {
		return compDate;
	}

	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public Orders() {
		super();
	}

}
