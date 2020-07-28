package com.jfast.service;

import com.jfast.core.paging.PageBean;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleExample;
import com.jfast.service.base.IBaseService;
import com.jfast.vo.RoleVo;

public interface SysRoleService extends IBaseService<SysRole, SysRoleExample, Integer> {
	
	public PageBean<RoleVo> getSysRoleListByPage(SysRole sysRole, Integer page, Integer pageSize);

}
