package com.yoouman;



import java.io.File;
import java.util.Hashtable;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yoouman.dao.CommentDao;
import com.yoouman.dao.OrderDao;
import com.yoouman.dao.ProductDao;
import com.yoouman.entity.Orders;
import com.yoouman.entity.Page;
import com.yoouman.entity.Product;
import com.yoouman.service.MatrixToImageWriter;
import com.yoouman.util.ActionHelp;
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
