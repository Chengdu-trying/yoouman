package com.yoouman.dao;


import java.util.List;
import com.yoouman.entity.Comment;
import com.yoouman.entity.Page;

public interface CommentDao{
	/**
	 * 增加新评论
	 * @param comment	评论体
	 * @return	>0 则新增成功、否则失败
	 */
	int saveNewComment(Comment comment);
	/**
	 * 通过商品id查询该商品下所有评论
	 * @param productId	商品id
	 * @return	评论集合
	 */
	List<Comment> findByProductId(int productId);
	/**
	 * 按照商品id对该商品下的评论分页
	 * @param productId	商品id
	 * @param page	页面
	 * @return	该页评论
	 */
	Page<Comment> getListForPageByPId(int productId,int pageIndex,int pageCount);
	/**
	 * 通过用户id查询该用户所有评论
	 * @param userId	用户id
	 * @return	评论集合
	 */
	List<Comment> findByUserId(int userId);
	/**
	 * 查询商品下的评论数
	 * @param productId	商品id
	 * @return	int 评论总数
	 */
	int getCommentsCount(int productId);
}
