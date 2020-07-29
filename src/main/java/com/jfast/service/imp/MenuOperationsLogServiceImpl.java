package com.jfast.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfast.mapper.MenuOperationsLogMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.MenuOperationsLog;
import com.jfast.pojo.MenuOperationsLogExample;
import com.jfast.service.MenuOperationsLogService;
import com.jfast.service.imp.base.BaseService;
import com.jfast.vo.UserVo;

@Service("menuOperationsLogService")
public class MenuOperationsLogServiceImpl extends BaseService<MenuOperationsLog, MenuOperationsLogExample, Long> implements MenuOperationsLogService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private MenuOperationsLogMapper menuOperationsLogMapper;
	
	@Override
	public IBaseMapper<MenuOperationsLog, MenuOperationsLogExample, Long> getMapper() {
		return menuOperationsLogMapper;
	}
	
	/**
	 * 
     * @Description 插入菜单日志  
     * @Author      xd  
     * @Date        2019年11月6日 下午2:40:51  
     * @param @param user
     * @param @param message 参数  
     * @return void 返回类型   
     * @throws
	 */
	@Async("taskExecutor")
	@Transactional
	public void insertLog(UserVo user,String message){
		MenuOperationsLog log = new MenuOperationsLog();
		log.setMessage(message);
		log.setTime(new Date());
		log.setUid(user.getUid());
		log.setIp(user.getIp());
		log.setPhone(user.getPhone());
		this.create(log);
	}
}
