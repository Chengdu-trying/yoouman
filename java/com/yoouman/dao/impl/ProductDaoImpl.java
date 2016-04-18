package com.yoouman.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;

@Repository("productDao")
public class ProductDaoImpl extends HibernateTemplate implements ProductDao{

	@Override
	public List<Product> getListByType(int tId) {
		@SuppressWarnings("unchecked")
		List<Product> list=find("from Product p where p.pType.tId="+tId);;
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		Product product=null;
		@SuppressWarnings("unchecked")
		List<Product> list=find("from Product p where p.pId=?", productId);
		if (list.size()>0) {
			product=list.get(0);
		}
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

	@Override
	public List<Product> getListForPageBytId(final int tid, final Page page) {
		@SuppressWarnings("unchecked")
		List<Product> list=execute(new HibernateCallback() {
			@Override
			public List<Product> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Product p where p.pType.tId=?");
				query.setInteger(0, tid);
				query.setFirstResult((page.getPageIndex()-1)*page.getPageCount()); 
				query.setMaxResults(page.getPageCount()); 
				List<Product> list = query.list(); 
				return list;
			}	
		});
		return null;
	}

	@Override
	public Page<Product> getPageByKeyWords(final String keywords,int pageCount,int pageIndex) {
		Page<Product> page=new Page<Product>();
		List<Product> list=find("from Product p where p.pName like ?",keywords);
		list.addAll(find("from Product p where p.pType.tId in (select tId from PType t where t.tDesc like ?)",keywords));
		page.setPageCount(pageCount);
		page.setCount(list.size());
		page.setPageIndex(pageIndex);
		int start=pageCount*(pageIndex-1);
		int end=pageCount*pageIndex;
		if(end<=list.size()){
			page.setList(list.subList(start,end));
		}else{
			page.setList(list.subList(start,list.size()));
		}
		return page;
	}

	@Override
	public Page<Product> getPageForAllList(int pageCount, final int pageIndex) {
		final Page<Product> page=new Page<>();
		page.setPageCount(pageCount);
	
		@SuppressWarnings("unchecked")
		List<Product> list=execute(new HibernateCallback() {
			@Override
			public List<Product> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Product");
				page.setCount(query.list().size());
				page.setPageIndex(pageIndex);
				query.setFirstResult((page.getPageIndex()-1)*page.getPageCount()); 
				query.setMaxResults(page.getPageCount()); 
				List<Product> list = query.list(); 
				return list;
			}	
		});
		page.setList(list);
		return page;
	}

}
