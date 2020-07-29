package com.jfast.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @Description aliJSON的通用API  
 * @ClassName   JsonUtils  
 * @Date        2018年11月28日 下午3:03:38  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2018.
 */
public class JSONUtils {

	/**
	 * 对象转为json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		if(obj!=null){
			return JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * json转对Object
	 * @param json
	 * @return
	 */
	public static Object parseObject(String json) {
		if(StringUtils.isNotEmpty(json)){
			return JSON.parse(json);
		}
		return null;
	}
	
	/**
	 * json转class类
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> T parse(String json,Class<T> cl) {
		if(StringUtils.isNotEmpty(json)){
			return JSON.parseObject(json,cl);
		}
		return null;
	}
	
	/**
	 * json转class类  可以转化有泛型的
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> T parse(String json,TypeReference<T> t) {
		if(StringUtils.isNotEmpty(json)){
//   		TypeReference<T> t=new TypeReference<T>(){};
			return JSON.parseObject(json,t);
		}
		return null;
	}
	
	/**
	 * json转list集合类
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> List<T> parseArray(String json,Class<T> cl) {
		if(StringUtils.isNotEmpty(json)){
			return JSON.parseArray(json,cl);
		}
		return null;
	}
}
