package com.yoouman.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yoouman.entity.Product;

public class ProductDao extends BaseDao<Product>{
	
	

	public ProductDao() {
		super();
		super.setT(new Product());
		// TODO Auto-generated constructor stub
	}

	public List<Product> getListByType(int tId) {
		List<Product> list=super.getHibernateTemplate().find("from Product p where p.pType.tId="+tId);;
		return list;
	}
	public Product getProductById(int pid){
		Product product=(Product) getHibernateTemplate().find("from Product p where p.pId=?", pid).get(0);
		return product;
	}

}
