package com.jfast.core.mail.send;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("mailSenders")
public class MailSenders {
	
	@Autowired(required= false)
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String from;
	
	/**
	 * 
     * @Description 发送邮件  
     * @Author      xd  
     * @Date        2019年11月1日 下午2:17:13  
     * @param @param to
     * @param @param subject
     * @param @param text 参数  
     * @return void 返回类型   
     * @throws
	 */
	public void send(String to,String subject,String text){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
	    mailMessage.setFrom(from);
	    mailMessage.setTo(to);
	    mailMessage.setSubject(subject);
	    mailMessage.setText(text);	 
	    javaMailSender.send(mailMessage);
	}
	/**
	 * 
     * @Description 发送邮件html  
     * @Author      xd  
     * @Date        2019年11月5日 下午4:10:53  
     * @param @param to
     * @param @param subject
     * @param @param text
     * @param @throws MessagingException 参数  
     * @return void 返回类型   
     * @throws
	 */
	public void sendhtml(String to,String subject,String text) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message,true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text,true);	 
		javaMailSender.send(message);
	}
	/**
	 * 
     * @Description 带附件的发送  
     * @Author      xd  
     * @Date        2019年11月26日 上午9:16:23  
     * @param @param to
     * @param @param subject
     * @param @param content
     * @param @param path
     * @param @throws MessagingException 参数  
     * @return void 返回类型   
     * @throws
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String path) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);
        FileSystemResource file=new FileSystemResource(new File(path));
        helper.addAttachment(file.getFilename(),file);
        javaMailSender.send(message);
    }
}
