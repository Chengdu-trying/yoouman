package com.yoouman.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.yoouman.dao.CommentDao;
import com.yoouman.entity.Comment;
import com.yoouman.entity.Page;

@Repository("commentDao")
public class CommentDaoImpl extends HibernateTemplate implements CommentDao{
	@Override
	public int saveNewComment(Comment comment) {
		int result=(int)save(comment);
		return result;
	}

	@Override
	public List<Comment> findByProductId(int productId) {
		@SuppressWarnings("unchecked")
		List<Comment> comments=find("from Comment c where c.product.pId=?", productId);
		return comments;
	}


	@Override
	public List<Comment> findByUserId(int userId) {
		@SuppressWarnings("unchecked")
		List<Comment> comments=find("from Comment c where c.owner.userId=?",userId);		
		return comments;
	}

	@Override
	public int getCommentsCount(int productId) {
		long result=(long)find("select count(*) from Comment c where c.product.pId=?",productId).get(0);
		return Integer.parseInt(String.valueOf(result));
	}

	@Override
	public Page<Comment> getListForPageByPId(final int productId, final int pageIndex, int pageCount) {
		@SuppressWarnings("unchecked")
		final Page<Comment> page=new Page<Comment>();
		page.setPageCount(pageCount);
		@SuppressWarnings("unchecked")
		List<Comment> comments=execute(new HibernateCallback() {
			@Override
			public List<Comment> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Comment c where c.product.pId=? order by c.cId desc");
				query.setInteger(0, productId);
				page.setCount(query.list().size());
				page.setPageIndex(pageIndex);
				
				query.setFirstResult((page.getPageIndex()-1)*page.getPageCount()); 
				query.setMaxResults(page.getPageCount()); 
				List<Comment> list = query.list(); 
				return list;
			}
			
		});
		page.setList(comments);
		return page;
	}


}
