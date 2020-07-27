package com.jfast.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfast.mapper.SysRoleUserMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysRoleUser;
import com.jfast.pojo.SysRoleUserExample;
import com.jfast.pojo.SysRoleUserKey;
import com.jfast.service.SysRoleUserService;
import com.jfast.service.imp.base.BaseService;

@Service("sysRoleUserService")
public class SysRoleUserServiceImpl extends BaseService<SysRoleUser, SysRoleUserExample, SysRoleUserKey> implements SysRoleUserService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private SysRoleUserMapper sysRoleUserMapper;
	
	@Override
	public IBaseMapper<SysRoleUser, SysRoleUserExample, SysRoleUserKey> getMapper() {
		return sysRoleUserMapper;
	}
	
	@Transactional
	@Override
	public boolean setpermission(SysRoleUser sysRoleUser) {
		SysRoleUserExample ex = new SysRoleUserExample();
		ex.createCriteria().andUidEqualTo(sysRoleUser.getUid());
		List<SysRoleUser> sList = this.getResult(ex);
		//如果存在已经配置的权限，先删除
		if(sList != null && !sList.isEmpty()){
			for(SysRoleUser s : sList){
				if(sysRoleUser.getUid().equals(s.getUid()))this.delete(s);
			}
		}
		//保存
		this.create(sysRoleUser);
		return true;
	}

}
