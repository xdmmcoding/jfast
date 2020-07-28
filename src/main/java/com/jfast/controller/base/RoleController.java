package com.jfast.controller.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfast.annos.Reqmenu;
import com.jfast.component.CommonComponent;
import com.jfast.component.SysRoleComponent;
import com.jfast.core.paging.PageBean;
import com.jfast.pojo.SysMenu;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleMenu;
import com.jfast.service.SysRoleService;
import com.jfast.vo.ExportVo;
import com.jfast.vo.GridVo;
import com.jfast.vo.RoleVo;
import com.jfast.vo.UserVo;

@Controller
@RequestMapping("/role")
@RequiresPermissions("shrio:role")
public class RoleController{
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleComponent sysRoleComponent;
	@Autowired
	private CommonComponent commonComponent;
	/**
	 * 
     * @Description 角色设置页面  
     * @Author      lixudong  
     * @Date        2019年10月16日 上午9:28:46  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/info.html",method=RequestMethod.GET)
	@Reqmenu(message="管理体系-角色列表页面")
	public String info(HttpServletRequest request,HttpServletResponse response){
		return "/system/role";
	}
	/**
	 * 
     * @Description 分页信息  
     * @Author      lixudong  
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
	@Reqmenu(message="管理体系-角色列表页面列表信息查询")
	@ResponseBody
	public GridVo pagination(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer limit,SysRole sysRole) {
		PageBean<RoleVo> pageList = sysRoleService.getSysRoleListByPage(sysRole, page, limit);
		return new GridVo(pageList.getTotal(),pageList.getItems());
	}
	/**
	 * 
     * @Description 提交  
     * @Author      lixudong  
     * @Date        2019年10月29日 下午5:07:21  
     * @param @param request
     * @param @param response
     * @param @param sysRole
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/submit.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-角色列表页面新增，编辑角色信息")
	@ResponseBody
	public ExportVo<Object> submit(HttpServletRequest request,HttpServletResponse response,SysRole sysRole){
		//获取登录信息
		UserVo user = (UserVo)SecurityUtils.getSubject().getPrincipal();
		return sysRoleComponent.submit(sysRole, user);
	}
	/**
	 * 
     * @Description 是否启用  
     * @Author      lixudong  
     * @Date        2019年10月30日 上午9:05:33  
     * @param @param request
     * @param @param response
     * @param @param sysRole
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/isstart.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-角色列表页面是否启用角色")
	@ResponseBody
	public ExportVo<Object> isstart(HttpServletRequest request,HttpServletResponse response,SysRole sysRole){
		if(sysRole != null && sysRole.getRoleId() != null){
			SysRole up = new SysRole();
			up.setRoleId(sysRole.getRoleId());
			up.setUpdateTime(new Date());
			up.setRoleStatus(sysRole.getRoleStatus());
			sysRoleService.update(up);
			return ExportVo.success(null);
		}
		return ExportVo.fail("操作失败");
	}
	/**
	 * 
     * @Description 删除  
     * @Author      lixudong  
     * @Date        2019年10月30日 上午9:18:24  
     * @param @param request
     * @param @param response
     * @param @param id
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/del.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-角色列表页面删除角色信息")
	@ResponseBody
	public ExportVo<Object> isstart(HttpServletRequest request,HttpServletResponse response,Integer id){
		if(id != null){
			sysRoleService.delete(id);
			return ExportVo.success(null);
		}
		return ExportVo.fail("操作失败");
	}
	
	/**
	 * 
     * @Description 权限菜单列表  
     * @Author      lixudong  
     * @Date        2019年10月30日 上午9:52:44  
     * @param @param request
     * @param @param response
     * @param @param page
     * @param @param limit
     * @param @param sysRole
     * @param @return 参数  
     * @return GridVo 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/getauthoritys.html",method=RequestMethod.POST)
	@ResponseBody
	public ExportVo<Object> getauthoritys(HttpServletRequest request,HttpServletResponse response,SysRole sysRole) {
		List<SysMenu> cList = commonComponent.getMenuList();
		List<SysMenu> mList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		//
		List<SysRoleMenu> sList = sysRoleComponent.getSysRoleMenuList(sysRole.getRoleId());
		List<String> ids = new ArrayList<>();
		if(cList != null && !cList.isEmpty()){
			for(SysMenu m : cList){
				if(!"1".equals(m.getState())){
					//是否选中
					if(checked(cList,sList, m.getCode()))ids.add(m.getCode());
					if("-1".equals(m.getState())){
						m.LAY_CHECKED = true;
					}
					mList.add(m);
				}
			}
		}
		//需要权限的菜单树
		map.put("tdata", mList);
		//已授权
		String[] strings = new String[ids.size()];
		ids.toArray(strings);
		map.put("cdata", strings);
		return ExportVo.success(map);
	}
	/**
	 * 
     * @Description 配置权限菜单  
     * @Author      lixudong  
     * @Date        2019年10月30日 上午10:51:50  
     * @param @param request
     * @param @param response
     * @param @param roleIdAuth
     * @param @param codes
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/setauthority.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-角色列表页面配置角色菜单")
	@ResponseBody
	public ExportVo<Object> setauthority(HttpServletRequest request,HttpServletResponse response,Integer roleIdAuth,@RequestParam(value = "codes[]",defaultValue = "") String[] codes){
		//获取登录信息
		UserVo user = (UserVo)SecurityUtils.getSubject().getPrincipal();
		if(roleIdAuth == null)return ExportVo.fail("操作失败");
		//保存
		//if(codes == null || codes.length==0)return ExportVo.fail("请选择要配置的权限菜单");
		//
		return sysRoleComponent.setauthority(roleIdAuth, codes, user);
	}
	/**
	 * 
     * @Description 判断是否选中  
     * @Author      lixudong  
     * @Date        2019年10月30日 下午2:09:42  
     * @param @param sList
     * @param @param code
     * @param @return 参数  
     * @return boolean 返回类型   
     * @throws
	 */
	private boolean checked(List<SysMenu> cList,List<SysRoleMenu> sList,String code){
		//是否是父节点，剔除
		for(SysMenu m : cList){
			if(code.equals(m.getParentCode())){
				return false;
			}
		}
		if(sList != null && !sList.isEmpty()){
			for(SysRoleMenu m:sList){
				if(m.getMenuCode().equals(code))return true;
			}
		}
		return false;
	}
}
