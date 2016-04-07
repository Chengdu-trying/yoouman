package com.yoouman.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yoouman.dao.BaseDao;
import com.yoouman.dao.CommentDao;
import com.yoouman.entity.Comment;
import com.yoouman.entity.Page;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDao<Comment> implements CommentDao{
	@Override
	public int saveNewComment(Comment comment) {
		int result=(int)hibernateTemplate.save(comment);
		return result;
	}

	@Override
	public List<Comment> findByProductId(int productId) {
		@SuppressWarnings("unchecked")
		List<Comment> comments=hibernateTemplate.find("from Comment c where c.product.pId=?", productId);
		return comments;
	}


	@Override
	public List<Comment> findByUserId(int userId) {
		@SuppressWarnings("unchecked")
		List<Comment> comments=hibernateTemplate.find("from Comment c where c.owner.userId=?",userId);
		
		return comments;
	}

	@Override
	public int getCommentsCount(int productId) {
		long result=(long) hibernateTemplate.find("select count(*) from Comment c where c.product.pId=?",productId).get(0);
		return Integer.parseInt(String.valueOf(result));
	}

	@Override
	public List<Comment> getListForPageByPId(int productId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
