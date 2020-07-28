package com.jfast.service;

import java.util.List;

import com.jfast.pojo.SysRoleMenu;
import com.jfast.pojo.SysRoleMenuExample;
import com.jfast.pojo.SysRoleMenuKey;
import com.jfast.service.base.IBaseService;

public interface SysRoleMenuService extends IBaseService<SysRoleMenu, SysRoleMenuExample, SysRoleMenuKey> {
	
	public boolean setauthority(Integer roleId,List<SysRoleMenu> mList); 
}
