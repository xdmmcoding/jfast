package com.jfast.controller.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfast.ICONST;
import com.jfast.annos.Reqmenu;
import com.jfast.component.CommonComponent;
import com.jfast.core.paging.PageBean;
import com.jfast.core.paging.PageUtil;
import com.jfast.pojo.SysMenu;
import com.jfast.service.SysMenuService;
import com.jfast.vo.ExportVo;

@Controller
@RequestMapping("/menu")
@RequiresPermissions("shrio:menu")
public class MenuController{
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private CommonComponent commonComponent;
	/**
	 * 
     * @Description 角色设置页面  
     * @Author      xd  
     * @Date        2019年10月16日 上午9:28:46  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/info.html",method=RequestMethod.GET)
	@Reqmenu(message="管理体系-菜单管理页面")
	public String info(HttpServletRequest request,HttpServletResponse response){
		return "/system/menu";
	}
	
	@RequestMapping(value = "/get/all.html",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<SysMenu> getmeunlist(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer limit,SysMenu sysMenu) {
		List<SysMenu> list = sysMenuService.getResult(null);
		Map<String, String> umap = commonComponent.getDictByType(ICONST.SYS_MENU_U_TYPE);
		Map<String, String> mmap = commonComponent.getDictByType(ICONST.SYS_MENU_M_TYPE);
		if(list != null && !list.isEmpty()){
			for(SysMenu m : list){
				if(StringUtils.isNotEmpty(m.getmType()))m.setmTypeName(mmap.get(m.getmType()));
				if(StringUtils.isNotEmpty(m.getuType()))m.setuTypeName(umap.get(m.getuType()));
			}
		}
		return PageUtil.getPage(list, null);
	}
	/**
	 * 
     * @Description 分页信息  
     * @Author      xd  
     * @Date        2019年10月17日 下午3:05:59  
     * @param @param request
     * @param @param response
     * @param @param page
     * @param @param limit
     * @param @param appRegistered
     * @param @return 参数  
     * @return GridVo 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/pagination.html",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<SysMenu> pagination(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer limit,SysMenu sysMenu) {
		return sysMenuService.getSysMenuListByPage(sysMenu, page, limit);
	}
	/**
	 * 
     * @Description 提交  
     * @Author      xd  
     * @Date        2019年10月29日 下午5:07:21  
     * @param @param request
     * @param @param response
     * @param @param sysRole
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/submit.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-菜单管理页面新增，编辑菜单信息")
	@ResponseBody
	public ExportVo<Object> submit(HttpServletRequest request,HttpServletResponse response,SysMenu sysMenu){
		//判断是更新，还是插入
		if(sysMenu.getId() == null){
			sysMenu.setState("0");
			sysMenu.setCreateTime(new Date());
			try {
				sysMenuService.create(sysMenu);
			} catch (Exception e) {
				if(e instanceof DuplicateKeyException){
					return ExportVo.fail("菜单编码重复");
				}
				return ExportVo.fail("操作失败");
			}
		}else{
			sysMenu.setParentCode(null);
			sysMenu.setUpdateTime(new Date());
			sysMenuService.update(sysMenu);
		}
		return ExportVo.success(null);
	}
	/**
	 * 
     * @Description 是否启用  
     * @Author      xd  
     * @Date        2019年10月30日 上午9:05:33  
     * @param @param request
     * @param @param response
     * @param @param sysRole
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/isstart.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-菜单管理页面是否启用菜单")
	@ResponseBody
	public ExportVo<Object> isstart(HttpServletRequest request,HttpServletResponse response,SysMenu sysMenu){
		if(sysMenu != null && sysMenu.getId() != null){
			SysMenu up = new SysMenu();
			up.setId(sysMenu.getId());
			up.setState(sysMenu.getState());
			up.setUpdateTime(new Date());
			sysMenuService.update(up);
			return ExportVo.success(null);
		}
		return ExportVo.fail("操作失败");
	}
	/**
	 * 
     * @Description 删除  
     * @Author      xd  
     * @Date        2019年10月30日 上午9:18:24  
     * @param @param request
     * @param @param response
     * @param @param id
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/del.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-菜单管理页面删除菜单信息")
	@ResponseBody
	public ExportVo<Object> isstart(HttpServletRequest request,HttpServletResponse response,Integer id){
		if(id != null){
			SysMenu sysMenu = sysMenuService.getById(id);
			List<SysMenu> list = sysMenuService.getResult(null);
			List<SysMenu> child =  new ArrayList<SysMenu>();
			child.add(sysMenu);
			recursion(child, list, sysMenu.getCode());
			sysMenuService.deletes(child);
			return ExportVo.success(null);
		}
		return ExportVo.fail("操作失败");
	}
	/**
	 * 
     * @Description 递归找所有的儿子节点  
     * @Author      xd  
     * @Date        2020年6月10日 下午2:29:33  
     * @param @param child
     * @param @param mList
     * @param @param pid 参数  
     * @return void 返回类型   
     * @throws
	 */
	private void recursion(List<SysMenu> child,List<SysMenu> mList, String pid) {
		for (SysMenu m : mList) {
			if (StringUtils.isNotEmpty(pid) && pid.equals(m.getParentCode())) {
				//递归遍历下一级
				child.add(m);
				recursion(child,mList, m.getCode());
			}
		}
	}
}
