package com.jfast.core.config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jfast.ICONST;
import com.jfast.core.filter.AppAuthFilter;
import com.jfast.core.filter.DateFormatConverter;
import com.jfast.core.shiro.WebAuthRealm;

import net.sf.ehcache.CacheManager;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{
	/**
     * 
     * @Description ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 				注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 				初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * 				Filter Chain定义说明
     * 					1、一个URL可以配置多个Filter，使用逗号分隔
     * 					2、当设置多个过滤器时，全部验证通过，才视为通过
     * 					3、部分过滤器可指定参数，如perms，roles  
     * @Author      xd  
     * @Date        2020年5月15日 上午10:24:18  
     * @param @param securityManager
     * @param @return 参数  
     * @return ShiroFilterFactoryBean 返回类型   
     * @throws
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> filterChainDefinitioinMap = new LinkedHashMap<String, String>();
        //配置退出过滤器，其中的具体的退出代码shiro已经实现
        filterChainDefinitioinMap.put("/logout/out.html", "logout");
        //过滤连定义，从上向下顺序执行，一般将/**放在最为下边 ！！！！！
        //authc：所有的url都必须认证通过才可以访问；anon:所有的url都可以匿名访问
        filterChainDefinitioinMap.put("/register.html", "anon");
        filterChainDefinitioinMap.put("/login.html", "anon");
        filterChainDefinitioinMap.put("/repassword.html", "anon");
        filterChainDefinitioinMap.put("/api/**", "anon");
        filterChainDefinitioinMap.put("/static/**", "anon");
        filterChainDefinitioinMap.put("/login/**", "anon");
        filterChainDefinitioinMap.put(ICONST.SYS_PATH_ERROR, "anon");
        filterChainDefinitioinMap.put("/page/error.html", "anon");
        filterChainDefinitioinMap.put("/html/**","anon");
        //swagger文档访问路径过滤
        filterChainDefinitioinMap.put("/doc.html","anon");
        filterChainDefinitioinMap.put("/swagger-resources/**","anon");
        filterChainDefinitioinMap.put("/webjars/**","anon");
        filterChainDefinitioinMap.put("/v2/*","anon");
        //权限验证
        filterChainDefinitioinMap.put("/**", "authc");
        //如果不设置默认会自动寻找web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/page/error.html?code=-1");//此处应是url并非静态资源位置
        //登陆成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/error.html?code=403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitioinMap);
        return shiroFilterFactoryBean;

    }
	
	/**
	 * 自定义拦截器   
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**===========================================**系统对外接口访问权限控制**===========================================*/
		List<String> appExcludepatterns = new ArrayList<String>();
		//配置拦截器，依次为权限处理类，拦截路径，不需要拦截的路径
		registry.addInterceptor(new AppAuthFilter()).addPathPatterns("/api/**").excludePathPatterns(appExcludepatterns);
	}
	//==============================================================shrio配置，start==================================================================
	/**
	 * 
     * @Description 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了所以我们需要修改下doGetAuthenticationInfo中的代码;）  
     * @Author      xd  
     * @Date        2020年5月15日 上午10:23:42  
     * @param @return 参数  
     * @return HashedCredentialsMatcher 返回类型   
     * @throws
	 */
    @Bean(name="hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 
     * @Description 身份认证realm;(账号密码校验；权限等)  
     * @Author      xd  
     * @Date        2020年5月15日 上午10:23:55  
     * @param @return 参数  
     * @return WebAuthRealm 返回类型   
     * @throws
     */
    @Bean(name="mywebAuthRealm")
    public WebAuthRealm mywebAuthRealm() {
    	WebAuthRealm mywebAuthRealm = new WebAuthRealm();
    	mywebAuthRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    	//启用授权缓存，即缓存AuthorizationInfo信息，默认false
    	mywebAuthRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
    	mywebAuthRealm.setAuthorizationCacheName("shrioCache");
        return mywebAuthRealm;
    }
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
    	DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    	// 设置session过期时间3600s
    	sessionManager.setGlobalSessionTimeout(1800000L);
    	sessionManager.setSessionIdCookie(sessionIdCookie());
    	return sessionManager;
    }
    private SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("USERSESSIONID");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1800 * 10);
        return cookie;
    }
    
    @Bean(name="ehCacheManager")
    public EhCacheManager ehCacheManager() {  
        CacheManager cacheManager = CacheManager.getCacheManager("myEhcache");
        if(cacheManager == null){
            try {
                CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache.xml"));
				cacheManager = CacheManager.getCacheManager("myEhcache");
            } catch (IOException e) {
                throw new RuntimeException("initialize cacheManager failed");
            }
        }
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }
    
    @Bean(name="securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(mywebAuthRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }
    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    //==============================================================shrio配置 ，end==================================================================
	/**
	 * 
     * @Description 解决上传文件控制层接收为null的方法  
     * @Author      xd  
     * @Date        2019年8月13日 上午11:30:00  
     * @param @return 参数  
     * @return CommonsMultipartResolver 返回类型   
     * @throws
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		//解决上传文件控制层接收为null的方法
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
    }
	
    
    /**
     * 
     * @Description springboot处理controller层日期格式转化    
     * @Author      xd  
     * @Date        2019年11月4日 下午4:49:13  
     * @param @return 参数  
     * @return DateFormatConverter 返回类型   
     * @throws
     */
    @Bean(name = "dateFormatConverter")
	public DateFormatConverter dateFormatConverter() {
		//日期格式化
    	DateFormatConverter dateFormatConverter = new DateFormatConverter();
		return dateFormatConverter;
    }
    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true)
                .allowedHeaders("token","Origin", "X-Requested-With", "Content-Type", "Accept");
    }
}