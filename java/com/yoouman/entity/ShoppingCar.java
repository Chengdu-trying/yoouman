package com.yoouman.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8020827979748387652L;
	
	private List<Product> products_buy;		//购买商品表
	private List<Product> products_all;
	private int size;		//商品总数
	private float price;	//购买总价
	
	public List<Product> getProducts_all() {
		return products_all;
	}
	public void setProducts_all(List<Product> products_all) {
		this.products_all = products_all;
	}
	public List<Product> getProducts_buy() {
		return products_buy;
	}
	public void setProducts_buy(List<Product> products_buy) {
		this.products_buy = products_buy;
	}
	public int getSize() {
		if(products_all.size()>0){
			setSize(products_all.size());
		}
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getPrice() {
		if(products_buy.size()>0){
			float totalPrice=0;
			for (Product product : products_buy) {
				totalPrice+=Float.parseFloat(product.getpPrice())*product.getpBuyCount();
			}
			setPrice(totalPrice+8);
		}
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public ShoppingCar() {
		super();
		if(products_buy==null){
			products_buy=new ArrayList<Product>();
			products_all=new ArrayList<Product>();
		}
		// TODO Auto-generated constructor stub
	}
	public ShoppingCar(List<Product> products_buy, int size, float price) {
		super();
		this.products_buy = products_buy;
		this.size = size;
		this.price = price;
	}
	
	
}
