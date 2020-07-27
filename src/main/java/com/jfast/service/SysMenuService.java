package com.jfast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jfast.core.paging.PageBean;
import com.jfast.pojo.SysMenu;
import com.jfast.pojo.SysMenuExample;
import com.jfast.service.base.IBaseService;

public interface SysMenuService extends IBaseService<SysMenu, SysMenuExample, Integer> {
	
	public PageBean<SysMenu> getSysMenuListByPage(SysMenu sysMenu, Integer page, Integer pageSize);
	
	@Transactional
	void deletes(List<SysMenu> sList);
}
