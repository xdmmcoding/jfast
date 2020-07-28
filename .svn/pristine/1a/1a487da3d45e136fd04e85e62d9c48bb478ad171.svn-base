package com.jfast.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.mapper.LoginLogMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.LoginLogExample;
import com.jfast.service.LoginLogService;
import com.jfast.service.imp.base.BaseService;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseService<LoginLog, LoginLogExample, Long> implements LoginLogService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private LoginLogMapper loginLogMapper;
	
	@Override
	public IBaseMapper<LoginLog, LoginLogExample, Long> getMapper() {
		return loginLogMapper;
	}
}
