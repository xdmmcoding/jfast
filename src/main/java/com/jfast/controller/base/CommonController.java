package com.jfast.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfast.component.CommonComponent;
import com.jfast.core.paging.PageBean;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.SysMenu;
import com.jfast.service.LoginLogService;
import com.jfast.util.RSAEncrypt;
import com.jfast.vo.ExportVo;
import com.jfast.vo.UserVo;

@Controller
public class CommonController{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	private CommonComponent commonComponent;
	@Autowired
	private LoginLogService loginLogService;
	/**
	 * 
     * @Description 进入首页  
     * @Author      xd  
     * @Date        2019年7月29日 下午3:59:40  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping("/")
	public String home(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("user",SecurityUtils.getSubject().getPrincipal());
		logger.info("{系统默认首页}");
		return "index";
	}
	
	/**
	 * 
     * @Description 进入首页  
     * @Author      xd  
     * @Date        2019年7月29日 下午3:59:40  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping("/index.html")
	public String index(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("user",SecurityUtils.getSubject().getPrincipal());
		logger.info("{系统默认首页}");
		return "index";
	}
	
	@RequestMapping("/home.html")
	public String uhome(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("uphnoe",((UserVo)SecurityUtils.getSubject().getPrincipal()).getPhone());
		request.setAttribute("username",((UserVo)SecurityUtils.getSubject().getPrincipal()).getUsername());
		return "home";
	}
	
	/**
	 * 
     * @Description 直接进入登陆页面  
     * @Author      xd  
     * @Date        2019年7月29日 下午3:59:52  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping("/login.html")
	public String login(HttpServletRequest request,HttpServletResponse response){
		logger.info("{系统登录页}");
		//获取登录信息
		Object value = SecurityUtils.getSubject().getPrincipal();;
		//如果session不为空，则可以浏览其他页面
  		if (value != null) return "index";
  		request.setAttribute("key", RSAEncrypt.PUBLIC_KEY);
  		return "login";
	}
	/**
	 * 
     * @Description 注册  
     * @Author      xd  
     * @Date        2019年11月1日 上午10:50:36  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping("/register.html")
	public String register(HttpServletRequest request,HttpServletResponse response){
		return "register";
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
	@RequestMapping("/repassword.html")
	public String repassword(HttpServletRequest request,HttpServletResponse response){
		return "repassword";
	}
	/**
	 * 
     * @Description 错误页面  
     * @Author      xd  
     * @Date        2019年11月5日 下午4:38:43  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping("/page/error.html")
	@ResponseBody
	public Object error(HttpServletRequest request,HttpServletResponse response,String code,String messgae){
		String ajaxHeader = request.getHeader("X-Requested-With");// ajax请求特有的请求头
		if ("XMLHttpRequest".equals(ajaxHeader)) {
			//设置跳转地址
        	response.setHeader("forward","true");
        	response.setHeader("code",code);
        	return ExportVo.fail(messgae);
		}
    	//进行页面跳转
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/error");
        modelAndView.addObject("code",code);
        return modelAndView;
	}
	/**
	 * 
     * @Description 获取自己的权限菜单  
     * @Author      xd  
     * @Date        2020年6月4日 下午2:12:03  
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/permission/menu.html",method=RequestMethod.POST)
    @ResponseBody
    public ExportVo<Object> getPermissionMenu(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return ExportVo.success(filterInfo(commonComponent.getpermissionmenu()));
    }
	/**
	 * 
     * @Description 登陆日志  
     * @Author      xd  
     * @Date        2020年8月4日 上午9:58:49  
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/loginlog",method=RequestMethod.POST)
    @ResponseBody
	public ExportVo<Object> getLoginLog(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageBean<LoginLog> pageList = loginLogService.getLoginLogListByPage(new LoginLog(), 1, 2);
		return ExportVo.success(pageList.getData());
    }
	@RequestMapping(value = "/refresh",method=RequestMethod.GET)
	@ResponseBody
	public void clearCache() {
        /*RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        //AccountAuthorizationRealm为在项目中定义的realm类
        WebAuthRealm shiroRealm = (WebAuthRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(subject.getPrincipal(), realmName);
        subject.runAs(principals);
        //用realm删除principle
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        //切换身份也就是刷新了
        subject.releaseRunAs();*/ 
    }
	/**
	 * 
     * @Description 过滤其他不必要的信息
     * @Author      xd  
     * @Date        2020年7月17日 上午10:15:33  
     * @param @param mList
     * @param @return 参数  
     * @return List<SysMenu> 返回类型   
     * @throws
	 */
	private List<SysMenu> filterInfo(List<SysMenu> mList){
		//过滤其他不必要的信息
		if(mList != null && !mList.isEmpty()){
			for(SysMenu m : mList){
				m.setPermissions("");
			}
		}
		return mList;
	}
}
