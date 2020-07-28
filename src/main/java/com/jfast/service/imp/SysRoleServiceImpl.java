package com.jfast.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.core.paging.PageBean;
import com.jfast.core.paging.PageParam;
import com.jfast.core.paging.PageUtil;
import com.jfast.mapper.SysRoleMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleExample;
import com.jfast.service.SysRoleService;
import com.jfast.service.imp.base.BaseService;
import com.jfast.util.MapContext;
import com.jfast.vo.RoleVo;

@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseService<SysRole, SysRoleExample, Integer> implements SysRoleService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public IBaseMapper<SysRole, SysRoleExample, Integer> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public PageBean<RoleVo> getSysRoleListByPage(SysRole sysRole, Integer page,Integer pageSize) {
		MapContext map = MapContext.newOne();
		map.put("role", sysRole);
		PageParam pageParam = new PageParam();
		pageParam.setLimit(pageSize);
		pageParam.setPage(page);
		map.put(PageUtil.PAGEPARAM, pageParam);
		return (PageBean<RoleVo>) PageUtil.getPage(sysRoleMapper.selectByFilter(map), pageParam);
	}

}
