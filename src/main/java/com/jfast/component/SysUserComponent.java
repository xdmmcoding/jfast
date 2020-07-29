package com.jfast.component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfast.ICONST;
import com.jfast.pojo.SysUser;
import com.jfast.service.SysUserService;
import com.jfast.util.MD5Utils;
import com.jfast.util.UUIDGenerator;
import com.jfast.vo.ExportVo;
import com.jfast.vo.UserVo;

@Component
public class SysUserComponent {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CommonComponent commonComponent;
	/**
	 * 
     * @Description 提交  
     * @Author      xd  
     * @Date        2020年7月23日 下午4:16:17  
     * @param @param sysUser
     * @param @param userVo
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	public ExportVo<Object> submit(SysUser sysUser,UserVo userVo){
		if(sysUser == null) return ExportVo.fail("操作失败");
		//查询管理学系统是否注册过
		SysUser sc = commonComponent.getSysUserByPhone(sysUser.getPhone());
		SysUser scu = commonComponent.getSysUserByPhone(sysUser.getUserName());
		if(sysUser.getId() != null){
			if((sc != null && sc.getId() != sysUser.getId()))return ExportVo.fail("手机号已存在");
			if((scu != null && scu.getId() != sysUser.getId()))return ExportVo.fail("用户名已存在");
			SysUser up = new SysUser();
			up.setId(sysUser.getId());
			up.setUserName(sysUser.getUserName());
			up.setPhone(sysUser.getPhone());
			up.setPassword(MD5Utils.MD5(sysUser.getPassword()));
			up.setDepartment(sysUser.getDepartment());
			up.setPosition(sysUser.getPosition());
			sysUserService.update(up);
			return ExportVo.success(null);
		}else{
			if(sc != null)return ExportVo.fail("手机号已存在");
			if(scu != null)return ExportVo.fail("用户名已存在");
			sysUser.setUid(UUIDGenerator.getUUID());
			sysUser.setOperator(userVo.getUid());
			sysUser.setCreateDate(new Date());
			sysUser.setStatus(ICONST.SYS_USER_STATUS_0);
			sysUser.setUpdateDate(new Date());
			sysUser.setPassword(MD5Utils.MD5(sysUser.getPassword()));
			sysUser.setType(ICONST.USER_TYPE_0);
			sysUserService.create(sysUser);
			return ExportVo.success(null);
		}
	}

}
