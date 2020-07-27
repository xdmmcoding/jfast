package com.jfast.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.core.paging.PageBean;
import com.jfast.core.paging.PageParam;
import com.jfast.core.paging.PageUtil;
import com.jfast.mapper.SysMenuMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.SysMenu;
import com.jfast.pojo.SysMenuExample;
import com.jfast.service.SysMenuService;
import com.jfast.service.imp.base.BaseService;
import com.jfast.util.MapContext;

@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseService<SysMenu, SysMenuExample, Integer> implements SysMenuService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public IBaseMapper<SysMenu, SysMenuExample, Integer> getMapper() {
		return sysMenuMapper;
	}

	@Override
	public PageBean<SysMenu> getSysMenuListByPage(SysMenu sysMenu, Integer page, Integer pageSize) {
		MapContext map = MapContext.newOne();
		map.put("menu", sysMenu);
		PageParam pageParam = new PageParam();
		pageParam.setLimit(pageSize);
		pageParam.setPage(page);
		map.put(PageUtil.PAGEPARAM, pageParam);
		return (PageBean<SysMenu>) PageUtil.getPage(sysMenuMapper.selectByFilter(map), pageParam);
	
	}

	@Override
	public void deletes(List<SysMenu> sList) {
		if(sList != null && !sList.isEmpty()){
			for(SysMenu m : sList){
				sysMenuMapper.deleteByPrimaryKey(m.getId());
			}
		}
	}

}
