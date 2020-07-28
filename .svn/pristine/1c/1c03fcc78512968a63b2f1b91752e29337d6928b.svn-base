package com.jfast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysMenu;
import com.jfast.pojo.SysMenuExample;
import com.jfast.util.MapContext;
@Mapper
public interface SysMenuMapper extends IBaseMapper<SysMenu, SysMenuExample, Integer>{
	//分页
	List<SysMenu> selectByFilter(MapContext map);
}