package com.yoouman;






import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yoouman.dao.OrderDao;

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
    	System.out.println(dao.getOrderByOrderNum("YM14614817854655365046").getProducts_str());
//    	ProductDao dao=(ProductDao) context.getBean("productDao");
//    	ObjectMapper mapper=(ObjectMapper) context.getBean("mapper");
//    	System.err.println(cutAndAppend("动漫画集"));
//    	Page page=dao.getPageByKeyWords(cutAndAppend("动漫画集"), 12, 1);
//    	System.out.println(mapper.writeValueAsString(page));
    	
    	
    	
    	//2016年4月24日17:40:55
    	
    	
    	
    	
    	
    	
    	
    }
    public static String cutAndAppend(String str){
		char[] strs=str.trim().toCharArray();
		StringBuffer newStr = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			newStr.append("%"+strs[i]);
		}
		
		return newStr.append("%").toString();
		
	}
}
