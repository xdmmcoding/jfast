package com.jfast.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfast.mapper.SysRoleMenuMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysRoleMenu;
import com.jfast.pojo.SysRoleMenuExample;
import com.jfast.pojo.SysRoleMenuKey;
import com.jfast.service.SysRoleMenuService;
import com.jfast.service.imp.base.BaseService;

@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseService<SysRoleMenu, SysRoleMenuExample, SysRoleMenuKey> implements SysRoleMenuService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Override
	public IBaseMapper<SysRoleMenu, SysRoleMenuExample, SysRoleMenuKey> getMapper() {
		return sysRoleMenuMapper;
	}

	@Transactional
	@Override
	public boolean setauthority(Integer roleId,List<SysRoleMenu> mList) {
		//先删除
		if(roleId != null){
			SysRoleMenuExample srmex = new SysRoleMenuExample();
			srmex.createCriteria().andRoleIdEqualTo(roleId);
			List<SysRoleMenu> sList = this.getResult(srmex);
			if(sList != null && !sList.isEmpty()){
				for(SysRoleMenu m : sList){			
					delete(m);
				}
			}
		}
		//再插入
		if(mList != null && !mList.isEmpty()){
			for(SysRoleMenu m : mList){			
				create(m);
			}
		}
		return true;
	}
}
