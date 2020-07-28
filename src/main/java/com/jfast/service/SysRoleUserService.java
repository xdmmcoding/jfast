package com.jfast.service;

import org.springframework.transaction.annotation.Transactional;

import com.jfast.pojo.SysRoleUser;
import com.jfast.pojo.SysRoleUserExample;
import com.jfast.pojo.SysRoleUserKey;
import com.jfast.service.base.IBaseService;

public interface SysRoleUserService extends IBaseService<SysRoleUser, SysRoleUserExample, SysRoleUserKey> {
	
	@Transactional
	public boolean setpermission(SysRoleUser sysRoleUser);
}
