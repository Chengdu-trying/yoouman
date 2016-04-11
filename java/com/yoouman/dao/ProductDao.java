package com.yoouman.dao;


import java.util.List;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;

public interface ProductDao{
	/**
	 * 根据商品类型id 查询商品列表
	 * @param tid	商品类型id
	 * @return
	 */
	List<Product> getListByType(int tId);
	/**
	 * 根据商品id查询某条具体商品信息
	 * @param productId		商品id
	 * @return	商品实体
	 */
	Product getProductById(int productId);
	/**
	 * 首页index.html商品列表查询
	 * 只查询每一种类型商品的前6条数据
	 * @return	30商品表集
	 */
	List<Product> getIndexlist();
	
	
	/**
	 * 按照商品类型id对该商品下的评论分页
	 * @param tid	商品类型id
	 * @param page	页面
	 * @return	该页评论
	 */
	List<Product> getListForPageBytId(int tid,Page page);


}
