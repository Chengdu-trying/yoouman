package com.yoouman.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

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
	public List<Product> getIndexlist(){
		List<Product> list = new ArrayList<Product>();
		list.addAll(getByType(1));
		list.addAll(getByType(2));
		list.addAll(getByType(3));
		list.addAll(getByType(4));
		list.addAll(getByType(5));
		list.addAll(getByType(6));
		return list;
	}
	public List<Product> getByType(int id){
		
		List<Product> t1=getHibernateTemplate().find("from Product p where p.pType.tId="+id);
		return t1.subList(0, 6);
	}

}
