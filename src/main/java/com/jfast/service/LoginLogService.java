package com.jfast.service;

import com.jfast.core.paging.PageBean;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.LoginLogExample;
import com.jfast.service.base.IBaseService;

public interface LoginLogService extends IBaseService<LoginLog, LoginLogExample, Long> {
	
	public PageBean<LoginLog> getLoginLogListByPage(LoginLog loginLog, Integer page, Integer pageSize);
}
