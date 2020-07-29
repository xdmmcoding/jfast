package com.jfast.core.mail;

import javax.mail.MessagingException;

import com.jfast.core.mail.send.MailSenders;
import com.jfast.util.SpringUtils;
/**
 * 
 * @Description 发送邮件工具类  
 * @ClassName   MailHelper  
 * @Date        2019年11月1日 下午2:21:13  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
public class MailHelper {
	/**
	 * 
     * @Description 带格式发送  
     * @Author      xd  
     * @Date        2019年12月19日 上午10:11:44  
     * @param @param to
     * @param @param subject
     * @param @param text
     * @param @throws MessagingException 参数  
     * @return void 返回类型   
     * @throws
	 */
	public static void sendhtml(String to,String subject,String text) throws MessagingException{
		SpringUtils.getBean(MailSenders.class).sendhtml(to, subject, text);
	}
	/**
	 * 
     * @Description 带附件发送  
     * @Author      xd  
     * @Date        2019年12月19日 上午10:12:07  
     * @param @param to
     * @param @param subject
     * @param @param content
     * @param @param path
     * @param @throws MessagingException 参数  
     * @return void 返回类型   
     * @throws
	 */
	public static void sendattachments(String to,String subject,String content,String path) throws MessagingException{
		SpringUtils.getBean(MailSenders.class).sendAttachmentsMail(to, subject, content, path);
	}
}
