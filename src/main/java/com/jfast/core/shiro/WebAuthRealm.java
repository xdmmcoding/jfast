package com.jfast.core.shiro;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfast.ICONST;
import com.jfast.component.CommonComponent;
import com.jfast.core.shiro.api.UserInformation;
import com.jfast.util.MD5Utils;
import com.jfast.vo.UserVo;

/**
 * 
 * @Description 身份验证核心类  
 * @ClassName   WebAuthRealm  
 * @Date        2020年5月15日 下午1:50:43  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2020.
 */
public class WebAuthRealm extends AuthorizingRealm {
	
    @Autowired
    private UserInformation userInformation;
    @Autowired
    private CommonComponent commonComponent;
    /**
     * 认证信息（身份验证）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入账号
        String username = (String) authenticationToken.getPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro也是有时间间隔机制，2分钟内不会重复执行该方法
        UserVo userVo = userInformation.findByUser(username);
        //用户是否存在
        if (userVo == null) {
        	throw new UnknownAccountException();
        }
        //用户是否被禁用
        if(ICONST.USER_STATUS_F_1.equals(userVo.getStatus())){
        	throw new DisabledAccountException();
        }
        //处理验证码登录认证问题，具体的验证码验证在登录出处理
        String password = userVo.getPassword();
        String verifitype = (String)SecurityUtils.getSubject().getSession().getAttribute("verifitype");
        if(ICONST.LOGIN_TYPE_1.equals(verifitype))password = MD5Utils.MD5(String.copyValueOf((char[])authenticationToken.getCredentials()));
        //加密方式，交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        userVo.setPassword("");//处理敏感信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userVo,//用户名
        		password,//密码
                null,//salt=username+salt
                getName()//realm name
        );
        return authenticationInfo;
    }

    /**
     * 权限信息:
     * 		1、如果用户正常退出，缓存自动清空；
     * 		2、如果用户非正常退出，缓存自动清空；
     * 		3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserVo userVo = (UserVo)principalCollection.getPrimaryPrincipal();
        //Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        //赋能用户权限
        permissions.addAll(commonComponent.getpermissions(userVo));
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;

    }

}