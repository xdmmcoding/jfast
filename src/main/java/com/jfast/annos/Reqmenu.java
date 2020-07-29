package com.jfast.annos;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @Description 菜单请求日志  
 * @ClassName   RequestLog  
 * @Date        2019年9月3日 下午5:21:48  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)  
@Documented
@Inherited
public @interface Reqmenu {
	
	/**
	 * 
     * @Description 菜单操作信息  
     * @Author      xd  
     * @Date        2019年8月29日 下午3:30:34  
     * @param @return 参数  
     * @return String[] 返回类型   
     * @throws
	 */
	String message() default "";
}
