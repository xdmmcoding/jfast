package com.jfast.core.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description 错误页面处理  
 * @ClassName   WebErrorPageResolver  
 * @Date        2019年11月6日 上午10:14:55  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
@Component
public class WebErrorPageResolver implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, "/page/error.html?code=403");
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/page/error.html?code=404");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/page/error.html?code=500");
        registry.addErrorPages(error403,error404,error500);
    }
}