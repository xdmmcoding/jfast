package com.jfast.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfast.E;
import com.jfast.vo.ExportVo;

/**
 * 
 * @Description 统一异常处理（Controller切面方式实现）
 *      		1、@ControllerAdvice：扫描所有Controller；
 *      		2、@ControllerAdvice(annotations=RestController.class)：扫描指定注解类型的Controller；
 *      		3、@ControllerAdvice(basePackages={"com.aaa","com.bbb"})：扫描指定package下的Controller  
 * @ClassName   WebExceptionResolver  
 * @Date        2018年12月26日 下午5:09:57  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2018.
 */
@ControllerAdvice
public class WebExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);
    
    /**
     * 
     * @Description shrio权限不足处理  
     * @Author      xd  
     * @Date        2020年5月18日 下午2:03:46  
     * @param @param req
     * @param @param e
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
     */
    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    @ResponseBody
    public Object unauthorizedHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)  {
    	String ajaxHeader = request.getHeader("X-Requested-With");// ajax请求特有的请求头
		if ("XMLHttpRequest".equals(ajaxHeader)) {
			//设置跳转地址
        	response.setHeader("forward","true");
        	response.setIntHeader("code",E.E_403.getCode());
        	return ExportVo.fail(E.E_403.getCode(), E.E_403.getMessage());
		}
    	//进行页面跳转
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/error");
        modelAndView.addObject("code",E.E_403.getCode());
        return modelAndView;
    }
    /**
     * 
     * @Description 仅可以处理  Controller方法跑出的异常，如：解析参数异常，方法内部出现的异常，无法处理404异常
     * @Author      xd  
     * @Date        2018年12月27日 上午8:48:33  
     * @param @param e
     * @param @return 参数  
     * @return Object 返回类型   
     * @throws
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
    	logger.error("WebExceptionResolver:{}", e);
    	//处理异常信息
        if (e instanceof KException) {
            return  ExportVo.fail(((KException) e).getCode(), e.getMessage());
        }else {
        	//设置跳转地址
        	response.setHeader("forward","true");
        	response.setIntHeader("code",E.E_500.getCode());
        	return ExportVo.fail(E.E_500.getCode(), E.E_500.getMessage());
        }
    }
}