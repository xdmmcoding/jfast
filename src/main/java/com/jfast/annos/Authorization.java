package com.jfast.annos;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @Description 鉴权认证  
 * @ClassName   Authorization  
 * @Date        2019年3月6日 下午5:13:10  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)  
@Documented
public @interface Authorization {
	
}
