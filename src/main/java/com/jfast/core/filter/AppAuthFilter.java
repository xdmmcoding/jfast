package com.jfast.core.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.jfast.util.JSONUtils;
import com.jfast.vo.ExportVo;

/**
 * 
 * @Description 验证App访问权限过滤器
 * @ClassName AuthFilter
 * @Date 2019年8月5日 下午4:55:55
 * @Author lixudong Copyright (c) All Rights Reserved, 2019.
 */
public class AppAuthFilter implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
		return true;
	}

	/**
	 * 
	 * @Description 设置错误信息 @Author lixudong @Date 2018年12月20日
	 *              下午2:06:14 @param @param request @param @param
	 *              response @param @param msg 参数 @return void 返回类型 @throws
	 */
	public void errorMsg(HttpServletRequest request, HttpServletResponse response, Integer code, String message) {
		// 重置response
		response.reset();
		// 设置编码格式
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(JSONUtils.toJson(ExportVo.fail(code, message)));
			pw.flush();
			pw.close();
		} catch (IOException e) {

		}
	}

	// System.out.println("Character Encoding: " + req.getCharacterEncoding());
	// System.out.println("Content Length: " + req.getContentLength());
	// //定义网络文件的类型和网页的编码，决定浏览器将以什么形式、什么编码读取这个文件，
	// System.out.println("path Info: " + req.getPathInfo());
	// //返回在URL中指定的任意附加路径信息，被子转换成一个实际路径
	// System.out.println("path Trans: " + req.getPathTranslated());
	// // 返回查询字符串，即URL中?后面的部份。
	// System.out.println("Query String: " + req.getQueryString());
	// // 如果用户通过鉴定，返回远程用户名，否则为null。
	// System.out.println("Remote User: " + req.getRemoteUser());
	// // 返回客户端的会话ID 
	// System.out.println("Session Id: " + req.getRequestedSessionId());
	// // 返回URL中一部分，从“/”开始，包括上下文，但不包括任意查询字符串。 
	// System.out.println("Request URI: " + req.getRequestURI());
	// // 返回请求URI上下文后的子串  
	// System.out.println("Servlet Path: " + req.getServletPath());
	// // request.getHeader()  获取HTTP头标指：如果其由请求给出，则名字应为大小写不敏感。   
	// // Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
	// System.out.println("Accept: " + req.getHeader("Accept"));
	// // localhost:8080
	// System.out.println("Host: " + req.getHeader("Host"));
	// // 点击超链接的页面url地址，如果地址栏直接输入则为null
	// System.out.println("Referer : " + req.getHeader("Referer"));
	// // Accept-Language : zh-CN,zh;q=0.8
	// System.out.println("Accept-Language : " +
	// req.getHeader("Accept-Language"));
	// // Accept-Encoding : gzip,deflate,sdch
	// System.out.println("Accept-Encoding : " +
	// req.getHeader("Accept-Encoding"));
	//
	// // User-Agent : Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.17 (KHTML,
	// like Gecko) Chrome...
	// System.out.println("User-Agent : " + req.getHeader("User-Agent"));
	// // Connection: keep-alive
	// System.out.println("Connection : " + req.getHeader("Connection"));
	// System.out.println("Content Type: "+ req.getContentType());
}