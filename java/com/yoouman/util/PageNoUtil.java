package com.yoouman.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class PageNoUtil {
	static Logger log=Logger.getLogger(PageNoUtil.class.getName());
/**
     * @param             session :一个会话
     * @param            hql:是需要执行的hql语句，
     * @param            offset 设置开始位置
     * @param              length:读取记录条数
     * return             返回结果集List<?>表示一个泛型的List
     */
    public static List<?> getList( Session session , String hql , int offset, int length){
    	 Query q = session.createQuery(hql);
         q.setFirstResult(offset);
         q.setMaxResults(length);
         log.info(hql);
         List<?> list = q.list();
         log.info("取到的每页的size"+list.size());
         return list;
    }
}
