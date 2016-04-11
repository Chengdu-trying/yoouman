package com.yoouman.entity;

import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "product")
@JsonIgnoreProperties(value={"comments"})
public class Product {
	// 商品编号
	@Id
	@Column(name = "product_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pId;
	// 商品名
	@Column(name = "product_Name")
	private String pName;
	// 商品描述
	@Column(name = "product_Desc",columnDefinition="longtext")
	private String pDesc;
	// 商品类型
	@OneToOne(cascade = { CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "type_id")
	private PType pType;
	//商品原价
	@Column(name="oragn_price")
	private String oPrice;
	// 商品单价
	@Column(name = "product_Price")
	private double pPrice;
	// 商品被购买数量
	@Column(name = "product_Count")
	private int pBuyCount;
	//商品图片
	@Column(name="imgUrl")
	private String imgUrl;
	// 商品库存数量
	@Column(name = "product_SCount")
	private int pStockCount;
	// 商品评价列表
	@OneToMany(mappedBy="product",cascade={CascadeType.ALL})
	private Set<Comment> comments;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public PType getpType() {
		return pType;
	}

	public void setpType(PType pType) {
		this.pType = pType;
	}

	public double getpPrice() {
		return pPrice;
	}

	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}

	public int getpBuyCount() {
		return pBuyCount;
	}

	public void setpBuyCount(int pBuyCount) {
		this.pBuyCount = pBuyCount;
	}

	public String getoPrice() {
		return oPrice;
	}

	public void setoPrice(String oPrice) {
		this.oPrice = oPrice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getpStockCount() {
		return pStockCount;
	}

	public void setpStockCount(int pStockCount) {
		this.pStockCount = pStockCount;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}



	public Product(int pId, String pName, String pDesc, PType pType, String oPrice, double pPrice, int pBuyCount,
			String imgUrl, int pStockCount, Set<Comment> comments) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pDesc = pDesc;
		this.pType = pType;
		this.oPrice = oPrice;
		this.pPrice = pPrice;
		this.pBuyCount = pBuyCount;
		this.imgUrl = imgUrl;
		this.pStockCount = pStockCount;
		this.comments = comments;
	}

	public Product() {
		super();
	}

}
