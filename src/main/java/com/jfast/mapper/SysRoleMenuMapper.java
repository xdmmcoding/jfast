package com.jfast.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysRoleMenu;
import com.jfast.pojo.SysRoleMenuExample;
import com.jfast.pojo.SysRoleMenuKey;

@Mapper
public interface SysRoleMenuMapper extends IBaseMapper<SysRoleMenu, SysRoleMenuExample, SysRoleMenuKey>{
	
}