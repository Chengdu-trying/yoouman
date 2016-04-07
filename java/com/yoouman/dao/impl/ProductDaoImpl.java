package com.yoouman.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yoouman.dao.BaseDao;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseDao<Product> implements ProductDao{

	@Override
	public List<Product> getListByType(int tId) {
		@SuppressWarnings("unchecked")
		List<Product> list=hibernateTemplate.find("from Product p where p.pType.tId="+tId);;
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		Product product=(Product)hibernateTemplate.find("from Product p where p.pId=?", productId).get(0);
		return product;
	}

	@Override
	public List<Product> getIndexlist() {
		List<Product> list = new ArrayList<Product>();
		list.addAll(getListByType(1).subList(0, 6));
		list.addAll(getListByType(2).subList(0, 6));
		list.addAll(getListByType(3).subList(0, 6));
		list.addAll(getListByType(4).subList(0, 6));
		list.addAll(getListByType(5).subList(0, 6));
		list.addAll(getListByType(6).subList(0, 6));
		return list;
	}

}
