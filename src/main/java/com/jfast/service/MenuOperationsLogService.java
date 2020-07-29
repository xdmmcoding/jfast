package com.jfast.service;

import com.jfast.pojo.MenuOperationsLog;
import com.jfast.pojo.MenuOperationsLogExample;
import com.jfast.service.base.IBaseService;
import com.jfast.vo.UserVo;

public interface MenuOperationsLogService extends IBaseService<MenuOperationsLog, MenuOperationsLogExample, Long> {
	
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
	public void insertLog(UserVo user,String message);
}
