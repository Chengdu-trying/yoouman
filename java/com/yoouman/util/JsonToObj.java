package com.yoouman.util;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;


public class JsonToObj {


private static ObjectMapper jacksonMapper = new ObjectMapper();

public static String objectToJackson(Object obj) throws Exception {
		return jacksonMapper.writeValueAsString(obj);
	}
public static <T> T jacksonToCollection(String src,Class<?> collectionClass, Class<?>... valueType)
			throws Exception {
	jacksonMapper.enableDefaultTyping();
		JavaType javaType= jacksonMapper.getTypeFactory().constructParametricType(collectionClass, valueType); 
		return (T)jacksonMapper.readValue(src, javaType);
	}

}
