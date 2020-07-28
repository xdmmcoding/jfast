package com.jfast.core.filter;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class InitServletContext implements ServletContextAware {
    /**
     * 在填充普通bean属性之后但在初始化之前调用
     * 类似于initializingbean的afterpropertiesset或自定义init方法的回调
     *
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
    	//servletContext.setAttribute("ctx", servletContext.getContextPath());
    }
}
