package com.jfast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleExample;
import com.jfast.util.MapContext;
import com.jfast.vo.RoleVo;

@Mapper
public interface SysRoleMapper extends IBaseMapper<SysRole, SysRoleExample, Integer>{
	//分页
	List<RoleVo> selectByFilter(MapContext map);
}