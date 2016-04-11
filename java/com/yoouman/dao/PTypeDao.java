package com.yoouman.dao;

import java.util.List;

import com.yoouman.entity.PType;

public interface PTypeDao {
	/**
	 * 查询所有商品类型
	 * @return
	 */
	List<PType> geTypes();
	/**
	 * 通过类型名称查询类型
	 * @param TName
	 * @return
	 */
	PType geTypeByTypeName(String TName);
}
