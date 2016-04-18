package com.yoouman;



import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yoouman.dao.CommentDao;
import com.yoouman.dao.OrderDao;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Orders;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;
import com.yoouman.util.MD5Util;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    	
    	
    	CommentDao dao=(CommentDao) context.getBean("commentDao");
    	Page page=dao.getListForPageByPId(2, 1, 8);
    	ObjectMapper mapper=(ObjectMapper) context.getBean("mapper");
    	System.out.println(mapper.writeValueAsString(page));
    }
}
