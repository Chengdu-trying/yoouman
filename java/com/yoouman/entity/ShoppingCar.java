package com.yoouman.entity;

import java.util.List;

public class ShoppingCar {
	
	private List<Product> products_buy;		//购买商品表
	private int size;		//商品总数
	private double price;	//购买总价
	
	public List<Product> getProducts_buy() {
		return products_buy;
	}
	public void setProducts_buy(List<Product> products_buy) {
		this.products_buy = products_buy;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ShoppingCar() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShoppingCar(List<Product> products_buy, int size, double price) {
		super();
		this.products_buy = products_buy;
		this.size = size;
		this.price = price;
	}
	
	
}
