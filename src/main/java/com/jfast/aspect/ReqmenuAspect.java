package com.jfast.aspect;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfast.annos.Reqmenu;
import com.jfast.service.MenuOperationsLogService;
import com.jfast.vo.UserVo;

@Aspect
@Component
public class ReqmenuAspect {
	
	//
	private static final Logger logger = LoggerFactory.getLogger(ReqmenuAspect.class);
    @Autowired
    private MenuOperationsLogService mLogService;
	
	@Pointcut("@annotation(com.jfast.annos.Reqmenu)")
    public void annotation(){}
    
	@Before(value="annotation()")
    public void before(JoinPoint joinPoint){
    	try{
    		//获取登录信息
    		UserVo user = (UserVo)SecurityUtils.getSubject().getPrincipal();
    		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Reqmenu reqmenu = method.getAnnotation(Reqmenu.class);
            if (reqmenu != null) {
            	mLogService.insertLog(user, reqmenu.message());
            }
    	}catch (Throwable e) {
    		logger.error("==========================[ReqmenuAspect：插入菜单操作日志失败]=============================");	
    		e.printStackTrace();
	    }
    }
}
