package com.jfast.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.LoginLogExample;

@Mapper
public interface LoginLogMapper extends IBaseMapper<LoginLog, LoginLogExample, Long>{
	
}