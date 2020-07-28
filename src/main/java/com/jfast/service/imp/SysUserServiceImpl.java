package com.jfast.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.core.paging.PageBean;
import com.jfast.core.paging.PageParam;
import com.jfast.core.paging.PageUtil;
import com.jfast.mapper.SysUserMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysUser;
import com.jfast.pojo.SysUserExample;
import com.jfast.service.SysUserService;
import com.jfast.service.imp.base.BaseService;
import com.jfast.util.MapContext;
import com.jfast.vo.SysUserVo;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseService<SysUser, SysUserExample, Integer> implements SysUserService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private SysUserMapper sysUserMapper;
	
	@Override
	public IBaseMapper<SysUser, SysUserExample, Integer> getMapper() {
		return sysUserMapper;
	}

	@Override
	public PageBean<SysUserVo> getSysUserListByPage(SysUserVo sysUser, Integer page, Integer pageSize) {
		MapContext map = MapContext.newOne();
		map.put("user", sysUser);
		PageParam pageParam = new PageParam();
		pageParam.setLimit(pageSize);
		pageParam.setPage(page);
		map.put(PageUtil.PAGEPARAM, pageParam);
		return (PageBean<SysUserVo>) PageUtil.getPage(sysUserMapper.selectByFilter(map), pageParam);
	}

}
