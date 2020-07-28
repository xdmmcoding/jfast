package com.jfast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysUser;
import com.jfast.pojo.SysUserExample;
import com.jfast.util.MapContext;
import com.jfast.vo.SysUserVo;

@Mapper
public interface SysUserMapper extends IBaseMapper<SysUser, SysUserExample, Integer>{
	//分页
	List<SysUserVo> selectByFilter(MapContext map);
}