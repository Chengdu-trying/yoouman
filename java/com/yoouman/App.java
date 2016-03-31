package com.yoouman;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yoouman.dao.ProductDao;
import com.yoouman.dao.UserDao;
import com.yoouman.entity.PType;
import com.yoouman.entity.Product;
import com.yoouman.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    
    	ProductDao dao=(ProductDao) context.getBean("productDao");
    	List<Product> products=dao.getIndexlist();
    	System.out.println(products.size());
    	for (Product product : products) {
			System.out.println(product.getpType().gettName());
		}
//		Session session= hTemplate.getSessionFactory().openSession();
//    	List<User> users=(List<User>) session.createQuery("from bean.user");
//    	System.out.println(users.size());
//    	for (User user2 : users) {
//			System.out.println(user2.getUserName());
//		}
    	//User u=dao.getObjByLogin("242f329b91051bfd1d36df44ff2fdaae","e10adc3949ba59abbe56e057f20f883e");
    	//System.out.println(u.getUserName());
    }
}
