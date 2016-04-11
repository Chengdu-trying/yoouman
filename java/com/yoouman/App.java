package com.yoouman;



import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yoouman.dao.CommentDao;
import com.yoouman.dao.OrderDao;
import com.yoouman.dao.ProductDao;
import com.yoouman.dao.UserDao;
import com.yoouman.entity.Comment;
import com.yoouman.entity.Orders;
import com.yoouman.entity.PType;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;
import com.yoouman.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    	
    	OrderDao dao=(OrderDao) context.getBean("orderDao");
    	Page<Orders> page=new Page<>();
    	page.setPageCount(5);
    	page.setCount(dao.getOrderNumByUserId(1));
    	System.out.println(dao.getListByUserIdForPage(1, page).get(0).getOrderNum());
//    	CommentDao dao=(CommentDao) context.getBean("commentDao");
//    	Page<Comment> page=new Page<Comment>();
//    	int count1=dao.getCommentsCount(2);
//    	System.out.println(count1);
//    	page.setPageCount(10);
//    	page.setCount(count1);
//    	page.setPageIndex(1);
//    	List<Comment> comments=dao.getListForPageByPId(2, page);
//    	for (Comment comment : comments) {
//			System.out.println(comment.getcContent());
//		}
    }
}
