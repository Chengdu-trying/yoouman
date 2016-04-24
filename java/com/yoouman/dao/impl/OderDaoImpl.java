package com.yoouman.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yoouman.dao.OrderDao;
import com.yoouman.entity.Orders;
import com.yoouman.entity.Page;
@Repository("orderDao")
public class OderDaoImpl extends HibernateTemplate implements OrderDao{

	@Override
	public List<Orders> getListByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getListByUserIdForPage(final int userId, final Page<Orders> page) {
		@SuppressWarnings("unchecked")
		List<Orders> list=execute(new HibernateCallback() {

			@Override
			public List<Orders> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Orders o where o.owner.userId=?");
				query.setInteger(0, userId);
				query.setFirstResult((page.getPageIndex()-1)*page.getPageCount()); 
				query.setMaxResults(page.getPageCount()); 
				List<Orders> list = query.list(); 
				return list;
			}
			
		});
		return list;
	}

	@Override
	public Orders getOrderByOrderId(int orderId) {
		Orders orders=(Orders) find("from Orders o where o.orderId=?",orderId).get(0);
		return orders;
	}

	@Override
	public Orders getOrderByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOrderNumByUserId(int userId) {
		long result=(long)find("select count(*) from Orders o where o.owner.userId=?",userId).get(0);
		return Integer.parseInt(String.valueOf(result));
	}

	@Override
	public int updateOrderByid(Orders order) {
		update(order);
		return 1;
	}

	@Override
	public int saveNewOrder(Orders order) {
		int  result=(int) save(order);
		return result;
	}

}
