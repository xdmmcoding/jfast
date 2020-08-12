package com.jfast.controller.base;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfast.E;
import com.jfast.ICONST;
import com.jfast.core.exception.KException;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.SysUser;
import com.jfast.service.LoginLogService;
import com.jfast.util.Common;
import com.jfast.util.RSAEncrypt;
import com.jfast.vo.ExportVo;
import com.jfast.vo.LoginVo;
import com.jfast.vo.UserVo;

@Controller
@RequestMapping("/login")
public class LoginController{
	@Autowired
	private LoginLogService loginLogService;
	
	@RequestMapping(value = "/login.html",method=RequestMethod.POST)
    @ResponseBody
    public ExportVo<Object> login(HttpServletRequest request,HttpServletResponse response,LoginVo loginVo) throws Exception {
        try{
        	Subject subject = SecurityUtils.getSubject();
        	String password = loginVo.getPassword();
        	//是否为短信验证码登录
        	if(ICONST.LOGIN_TYPE_1.equals(loginVo.getType())){
        		//校验验证码
        		verfiycode(loginVo);
        		subject.getSession().setAttribute("verifitype",loginVo.getType());
        		password = loginVo.getVerfiy();
        	}
        	//密码解密
        	//password = AESUtil.decryptBase64(password, AESUtil.PASSWORD);
        	password = RSAEncrypt.decrypt(password, RSAEncrypt.PRIVATE_KEY);
        	UsernamePasswordToken userToken = new UsernamePasswordToken(loginVo.getUsername(),password);
            subject.login(userToken);
            if(subject.isAuthenticated()){          	
            	insertLog(request, (UserVo)subject.getPrincipal());
            }
            return ExportVo.success(null);
        }catch (UnknownAccountException e){
        	//用户不存在
        	return ExportVo.fail(E.LOGIN_USER_NOT_EXIST.getCode(),E.LOGIN_USER_NOT_EXIST.getMessage());
        }catch (IncorrectCredentialsException e){
        	return ExportVo.fail(E.LOGIN_PASSWORD_INCORRECT.getCode(),E.LOGIN_PASSWORD_INCORRECT.getMessage());
		}catch (LockedAccountException e){
			return ExportVo.fail(E.LOGIN_USER_IS_LOCKED.getCode(),E.LOGIN_USER_IS_LOCKED.getMessage());
		}catch (DisabledAccountException e){
			return ExportVo.fail(E.LOGIN_USER_NOT_ACTIVATED.getCode(),E.LOGIN_USER_NOT_ACTIVATED.getMessage());
		}
    }
	/**
	 * 发送短信验证码
	 * @param request
	 * @param response
	 * @param loginVo
	 * @return
	 */
	@RequestMapping(value = "/sendSms.html",method=RequestMethod.POST)
	@ResponseBody
	public ExportVo<Object> sendSms(HttpServletRequest request,HttpServletResponse response,String phone,String tranType,String step,String oldPhone){
		return ExportVo.fail(null);
		
	}
	/**
	 * 
     * @Description 注册  
     * @Author      xd  
     * @Date        2019年11月1日 上午11:13:45  
     * @param @param request
     * @param @param response
     * @param @param loginVo
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/register.html",method=RequestMethod.POST)
	@ResponseBody
	public ExportVo<Object> register(HttpServletRequest request,HttpServletResponse response,LoginVo loginVo){
		return ExportVo.success(null);
	}
	/**
	 * 
     * @Description 重置密码  
     * @Author      xd  
     * @Date        2019年11月1日 上午10:50:43  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/repassword.html",method=RequestMethod.POST)
	@ResponseBody
	public ExportVo<Object> repassword(HttpServletRequest request,HttpServletResponse response,LoginVo loginVo){
		return ExportVo.fail(E.LOGIN_USER_NOT_EXIST.getCode(),E.LOGIN_USER_NOT_EXIST.getMessage());
	}
	
	/**
     * 
     * @Description 登出  
     * @Author      xd  
     * @Date        2019年10月10日 上午9:37:42  
     * @param @param request
     * @param @param response
     * @param @param user
     * @param @return
     * @param @throws Exception 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
     */
    @RequestMapping(value = "/out.html",method = RequestMethod.POST)
    @ResponseBody
    public ExportVo<Object> loginout(HttpServletRequest request,HttpServletResponse response,SysUser user) throws Exception {
    	Subject subject = SecurityUtils.getSubject();
        subject.logout();
    	return ExportVo.success(null);
    }
    /**
     * 
     * @Description 插入登录日志  
     * @Author      xd  
     * @Date        2019年11月5日 上午11:37:30  
     * @param @param request
     * @param @param sysUser 参数  
     * @return void 返回类型   
     * @throws
     */
    private void insertLog(HttpServletRequest request,UserVo userVo){
    	try {
    		LoginLog log = new LoginLog();
    		log.setTime(new Date());
    		log.setUid(userVo.getUid());
			log.setIp(Common.getIpAddress(request));
			log.setPhone(userVo.getPhone());
			loginLogService.create(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void  verfiycode(LoginVo loginVo) throws Exception{
    	throw new KException(E.LOGIN_VERFIY_CODE_ERROR.getCode(), E.LOGIN_VERFIY_CODE_ERROR.getMessage());
    }
    
//	private int lockedUser(SysUser user,Integer locked) {
//		SysUser sysUser = new SysUser();
//		sysUser.setId(user.getId());
//		sysUser.setLocked(locked);
//		if(user.getLockedTime()==null){
//			sysUser.setLockedTime(new Date());
//		}
//		return sysUserService.update(sysUser);
//	}
}
