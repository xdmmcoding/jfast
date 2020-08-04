package com.jfast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.LoginLogExample;
import com.jfast.util.MapContext;

@Mapper
public interface LoginLogMapper extends IBaseMapper<LoginLog, LoginLogExample, Long>{
	//分页
	List<LoginLog> selectByFilter(MapContext map);
}