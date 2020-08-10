package com.jfast.controller.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfast.annos.Reqmenu;
import com.jfast.component.SysUserComponent;
import com.jfast.core.paging.PageBean;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleExample;
import com.jfast.pojo.SysRoleUser;
import com.jfast.pojo.SysUser;
import com.jfast.service.SysRoleService;
import com.jfast.service.SysRoleUserService;
import com.jfast.service.SysUserService;
import com.jfast.vo.ExportVo;
import com.jfast.vo.SysUserVo;
import com.jfast.vo.UserVo;

@Controller
@RequestMapping("/personnel")
@RequiresPermissions("shrio:personnel")
public class PersonnelController{
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserComponent sysUserComponent;
	@Autowired
	private SysRoleUserService sysRoleUserService;
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 
     * @Description 人员设置页面  
     * @Author      xd  
     * @Date        2019年10月16日 上午9:28:46  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/info.html",method=RequestMethod.GET)
	@Reqmenu(message="管理体系-人员列表页面")
	public String info(HttpServletRequest request,HttpServletResponse response){
		return "/system/personnel";
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
	@Reqmenu(message="管理体系-人员列表页面列表信息查询")
	@ResponseBody
	public PageBean<SysUserVo> pagination(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer limit,SysUserVo sysUser) {
		PageBean<SysUserVo> pageList = sysUserService.getSysUserListByPage(sysUser, page, limit);
		filterInfo(pageList.getData());
		return pageList;
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
	@Reqmenu(message="管理体系-人员列表页面添加，修改人员信息")
	@ResponseBody
	public ExportVo<Object> submit(HttpServletRequest request,HttpServletResponse response,SysUser sysUser){
		//获取登录信息
		UserVo user = (UserVo)SecurityUtils.getSubject().getPrincipal();
		return sysUserComponent.submit(sysUser, user);
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
	@Reqmenu(message="管理体系-人员列表页面是否启用")
	@ResponseBody
	public ExportVo<Object> isstart(HttpServletRequest request,HttpServletResponse response,SysUser sysUser){
		if(sysUser != null && sysUser.getId() != null){
			SysUser up = new SysUser();
			up.setId(sysUser.getId());
			up.setUpdateDate(new Date());
			up.setStatus(sysUser.getStatus());
			sysUserService.update(up);
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
	@Reqmenu(message="管理体系-人员列表页面删除人员信息")
	@ResponseBody
	public ExportVo<Object> isstart(HttpServletRequest request,HttpServletResponse response,Integer id){
		if(id != null){
			sysUserService.delete(id);
			return ExportVo.success(null);
		}
		return ExportVo.fail("操作失败");
	}
	/**
	 * 
     * @Description 设置角色  
     * @Author      xd  
     * @Date        2019年10月30日 下午4:54:45  
     * @param @param request
     * @param @param response
     * @param @param roldid
     * @param @param uid
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/setrole.html",method=RequestMethod.POST)
	@Reqmenu(message="管理体系-人员列表页面设置角色")
	@ResponseBody
	public ExportVo<Object> setrole(HttpServletRequest request,HttpServletResponse response,SysRoleUser sysRoleUser){
		//获取登录信息
		UserVo user = (UserVo)SecurityUtils.getSubject().getPrincipal();
		//
		if(sysRoleUser != null && sysRoleUser.getRoleId()!= null && StringUtils.isNotEmpty(sysRoleUser.getUid())){
			sysRoleUser.setCreateTime(new Date());
			sysRoleUser.setOperator(user.getUid());
			sysRoleUser.setUpdateTime(new Date());
			sysRoleUserService.setpermission(sysRoleUser);
			return ExportVo.success(null);
		}
		return ExportVo.fail("操作失败");
	}
	/**
	 * 
     * @Description 角色下拉菜单  
     * @Author      xd  
     * @Date        2019年10月30日 下午4:47:26  
     * @param @param request
     * @param @param response
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/selected.html",method=RequestMethod.POST)
	@ResponseBody
	public ExportVo<Object> selected(HttpServletRequest request,HttpServletResponse response) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SysRoleExample ssex = new SysRoleExample();
		ssex.setOrderByClause("create_time desc");
		List<SysRole> ssList = sysRoleService.getResult(ssex);
		if(ssList != null && !ssList.isEmpty()){
			for(SysRole sysRole : ssList){
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("code", sysRole.getRoleId());
				map.put("name", sysRole.getRoleName());
				list.add(map);
			}
		}
		return ExportVo.success(list);
	}
	
	/**
	 * 
     * @Description 过滤其他不必要的信息
     * @Author      xd  
     * @Date        2020年7月17日 上午10:15:33  
     * @param @param mList
     * @param @return 参数  
     * @return List<SysMenu> 返回类型   
     * @throws
	 */
	private List<SysUserVo> filterInfo(List<SysUserVo> sList){
		//过滤其他不必要的信息
		if(sList != null && !sList.isEmpty()){
			for(SysUserVo s : sList){
				s.setPassword("");
			}
		}
		return sList;
	}
}
