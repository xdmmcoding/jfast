package com.jfast.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.ICONST;
import com.jfast.core.shiro.api.UserInformation;
import com.jfast.pojo.SysUser;
import com.jfast.pojo.SysUserExample;
import com.jfast.service.SysUserService;
import com.jfast.vo.UserVo;

@Service("userInformationService")
public class UserInformationServiceImpl implements UserInformation {
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserVo findByUser(String username) {
		//再判断管理系统用户
    	SysUser sysUser = getSysUserByusername(username);
    	if(sysUser != null){
    		UserVo userVo = new UserVo();
    		userVo.setId(sysUser.getId());
    		userVo.setUsername(username);
    		userVo.setPhone(sysUser.getPhone());
    		userVo.setPassword(sysUser.getPassword());
        	userVo.setType(ICONST.USER_TYPE_MANAGER);
        	userVo.setUid(sysUser.getUid());
        	userVo.setStatus(sysUser.getStatus());
        	userVo.setLocked(sysUser.getLocked());
        	userVo.setLockedTime(sysUser.getLockedTime());
        	return userVo;
    	}
    	//用户不存在
		return null;
	}
	
	/**
	 * 
     * @Description 根据手机号查询管理系统的用户  
     * @Author      xd  
     * @Date        2019年10月11日 下午5:25:10  
     * @param @param phone
     * @param @return 参数  
     * @return SysUser 返回类型   
     * @throws
	 */
	public SysUser getSysUserByusername(String username){
		SysUserExample suex = new SysUserExample();
		suex.createCriteria().andUserNameEqualTo(username);
		List<SysUser> uList = sysUserService.getResult(suex);
		if(uList != null && !uList.isEmpty()) return uList.get(0);
		return null;
	}
}
