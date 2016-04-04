package com.yoouman.dao;

import java.util.List;

import com.yoouman.entity.Comment;

public class CommentDao extends BaseDao<Comment>{

	public CommentDao() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	public int saveNewComment(Comment comment){
		int result=(int) getHibernateTemplate().save(comment);
		return result;
	}
	public List<Comment> findByProductId(int productId){
		List<Comment> comments=getHibernateTemplate().find("from Comment c where c.product.pId=?", productId);
		return comments;
	}
	
	public List<Comment> findByUserId(int userId){
		List<Comment> comments=getHibernateTemplate().find("from Comment c where c.owner.userId=?",userId);
		
		return comments;
	}
}
