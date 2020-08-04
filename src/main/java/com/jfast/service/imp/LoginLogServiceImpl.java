package com.jfast.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.core.paging.PageBean;
import com.jfast.core.paging.PageParam;
import com.jfast.core.paging.PageUtil;
import com.jfast.mapper.LoginLogMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.LoginLog;
import com.jfast.pojo.LoginLogExample;
import com.jfast.service.LoginLogService;
import com.jfast.service.imp.base.BaseService;
import com.jfast.util.MapContext;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseService<LoginLog, LoginLogExample, Long> implements LoginLogService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private LoginLogMapper loginLogMapper;
	
	@Override
	public IBaseMapper<LoginLog, LoginLogExample, Long> getMapper() {
		return loginLogMapper;
	}

	@Override
	public PageBean<LoginLog> getLoginLogListByPage(LoginLog loginLog, Integer page, Integer pageSize) {
		MapContext map = MapContext.newOne();
		map.put("log", loginLog);
		PageParam pageParam = new PageParam();
		pageParam.setLimit(pageSize);
		pageParam.setPage(page);
		map.put(PageUtil.PAGEPARAM, pageParam);
		return (PageBean<LoginLog>) PageUtil.getPage(loginLogMapper.selectByFilter(map), pageParam);
	}
}
