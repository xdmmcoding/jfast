package com.jfast.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfast.ICONST;
import com.jfast.pojo.Dictionary;
import com.jfast.pojo.DictionaryExample;
import com.jfast.pojo.SysMenu;
import com.jfast.pojo.SysMenuExample;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleMenu;
import com.jfast.pojo.SysRoleMenuExample;
import com.jfast.pojo.SysRoleUser;
import com.jfast.pojo.SysRoleUserExample;
import com.jfast.pojo.SysUser;
import com.jfast.pojo.SysUserExample;
import com.jfast.service.DictionaryService;
import com.jfast.service.SysMenuService;
import com.jfast.service.SysRoleMenuService;
import com.jfast.service.SysRoleService;
import com.jfast.service.SysRoleUserService;
import com.jfast.service.SysUserService;
import com.jfast.vo.UserVo;

@Component
public class CommonComponent {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysRoleUserService sysRoleUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private DictionaryService dictionaryService;
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
	public SysUser getSysUserByPhone(String phone){
		SysUserExample suex = new SysUserExample();
		suex.createCriteria().andPhoneEqualTo(phone);
		List<SysUser> uList = sysUserService.getResult(suex);
		if(uList != null && !uList.isEmpty()) return uList.get(0);
		return null;
	}
	/**
	 * 
	 * @Description 根据用户名查询管理系统的用户  
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
	
	/***
	 * 
     * @Description 根据用户类型查询菜单  
     * @Author      xd  
     * @Date        2019年10月12日 下午2:27:34  
     * @param @param type
     * @param @return 参数  
     * @return List<SysMenu> 返回类型   
     * @throws
	 */
	//@Cacheable(value = "ceshiCache",key = "'menuByType'+#type")
	public List<SysMenu> getMenuList(){
		SysMenuExample smex = new SysMenuExample();
		smex.setOrderByClause("sort asc");
		return sysMenuService.getResult(smex);
	}
	/**
	 * 
     * @Description 验证手机验证码  
     * @Author      xd  
     * @Date        2019年10月29日 上午8:49:04  
     * @param @param phone
     * @param @param vcode
     * @param @return 参数  
     * @return boolean 返回类型   
     * @throws
	 */
	public boolean verifyVcode(String phone,String vcode){
		return true;
	}
	
	/**
	 * 
     * @Description 查询超级管理员  
     * @Author      xd  
     * @Date        2020年5月18日 下午4:04:51  
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	public String getSuperAdministrator(){
		SysUserExample suex = new SysUserExample();
		suex.createCriteria().andStatusEqualTo("2");
		List<SysUser> uList = sysUserService.getResult(suex);
		if(uList != null && !uList.isEmpty()) return uList.get(0).getPhone();
		return "";
	}
	/**
	 * 
     * @Description 查询用户权限菜单 
     * @Author      xd  
     * @Date        2020年7月23日 下午4:02:58  
     * @param @return
     * @param @throws Exception 参数  
     * @return List<SysMenu> 返回类型   
     * @throws
	 */
	public List<SysMenu> getpermissionmenu() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		//获取登录用户信息
		UserVo user = (UserVo)subject.getPrincipal();
		//查询菜单
		SysMenuExample smex = new SysMenuExample();
		List<String> values = new ArrayList<>();
		values.add("1");
		values.add("2");
		smex.createCriteria().andMTypeIn(values).andUTypeEqualTo(user.getType());
		smex.setOrderByClause("sort asc");
		List<SysMenu> mList =sysMenuService.getResult(smex);
		//权限过滤
		return permissionsfilter(mList, user);
    }
	/**
	 * 
     * @Description 过滤菜单权限  
     * @Author      xd  
     * @Date        2019年11月1日 上午10:26:37  
     * @param @param mList
     * @param @param user
     * @param @return 参数  
     * @return List<SysMenu> 返回类型   
     * @throws
	 */
	private List<SysMenu> permissionsfilter(List<SysMenu> mList,UserVo user){
		List<SysMenu> cList = new ArrayList<SysMenu>();
		Map<String,String> map = new HashMap<String,String>();
		SysUser suser = getSysUserByPhone(user.getPhone());
		//超级管理员
		if(ICONST.USER_TYPE_1.equals(suser.getType())){
			return mList;
		}
		//用户没有启用
		if(ICONST.USER_STATUS_F_1.equals(suser.getStatus())){
			return cList;
		}
		//查询用户角色
		SysRoleUserExample srex = new SysRoleUserExample();
		srex.createCriteria().andUidEqualTo(user.getUid());
		List<SysRoleUser> sreList = sysRoleUserService.getResult(srex);
		if(sreList != null && !sreList.isEmpty()){
			for(SysRoleUser u :sreList){
				SysRole sysRole = sysRoleService.getById(u.getRoleId());
				if(!ICONST.ROLE_STATUS_0.equals(sysRole.getRoleStatus())) continue;
				//查询改角色下的菜单
				SysRoleMenuExample srmx = new SysRoleMenuExample();
				srmx.createCriteria().andRoleIdEqualTo(u.getRoleId());
				List<SysRoleMenu> srmList = sysRoleMenuService.getResult(srmx);
				if(srmList != null && !srmList.isEmpty()){
					for(SysRoleMenu m :srmList ){
						map.put(m.getMenuCode(), m.getMenuCode());
					}
				}
			}
		}
		//过滤菜单
		if(mList != null && !mList.isEmpty()){
			for(SysMenu m : mList){
				if((StringUtils.isNotEmpty(m.getCode())&&
						m.getCode().equals(map.get(m.getCode()))&& "0".equals(m.getState()))||"1".equals(m.getState())) cList.add(m);
			}
		}
		return cList;
	}
	/**
	 * 
     * @Description 获取用户权限  
     * @Author      xd  
     * @Date        2020年6月4日 下午3:20:51  
     * @param @param user
     * @param @return 参数  
     * @return Set<String> 返回类型   
     * @throws
	 */
	public Set<String> getpermissions(UserVo user){
		//查询菜单
		List<SysMenu> mList = getMenuList();
		Set<String> permissions = new HashSet<>();
		Map<String,String> map = new HashMap<String,String>();
		SysUser suser = getSysUserByPhone(user.getPhone());
		//超级管理员
		if(ICONST.USER_TYPE_1.equals(suser.getType())){
			if(mList != null && !mList.isEmpty()){
				for(SysMenu m : mList){
					permissions.add(m.getPermissions());
				}
			}
		}
		//用户没有启用
		if(ICONST.SYS_USER_STATUS_F_1.equals(suser.getStatus())){
			return permissions;
		}
		//查询用户角色
		SysRoleUserExample srex = new SysRoleUserExample();
		srex.createCriteria().andUidEqualTo(user.getUid());
		List<SysRoleUser> sreList = sysRoleUserService.getResult(srex);
		if(sreList != null && !sreList.isEmpty()){
			for(SysRoleUser u :sreList){
				SysRole sysRole = sysRoleService.getById(u.getRoleId());
				if(!"0".equals(sysRole.getRoleStatus())) continue;
				//查询改角色下的菜单
				SysRoleMenuExample srmx = new SysRoleMenuExample();
				srmx.createCriteria().andRoleIdEqualTo(u.getRoleId());
				List<SysRoleMenu> srmList = sysRoleMenuService.getResult(srmx);
				if(srmList != null && !srmList.isEmpty()){
					for(SysRoleMenu m :srmList ){
						map.put(m.getMenuCode(), m.getMenuCode());
					}
				}
			}
		}
		//过滤菜单
		if(mList != null && !mList.isEmpty()){
			for(SysMenu m : mList){
				if((StringUtils.isNotEmpty(m.getCode())&&
						m.getCode().equals(map.get(m.getCode()))&& "0".equals(m.getState()))||"1".equals(m.getState())){
					if(StringUtils.isNotEmpty(m.getPermissions()))permissions.add(m.getPermissions());
				}
			}
		}
		return permissions;
	}
	/**
	 * 
     * @Description 查询字典名称  
     * @Author      xd  
     * @Date        2020年6月10日 下午2:42:07  
     * @param @param type
     * @param @return 参数  
     * @return Map<String,String> 返回类型   
     * @throws
	 */
	public Map<String, String> getDictByType(String type) {
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isEmpty(type))return map;
		DictionaryExample dictex = new DictionaryExample();
		dictex.createCriteria().andDictTypeEqualTo(type);
		List<Dictionary> dictList = dictionaryService.getResult(dictex);
		if(dictList != null && !dictList.isEmpty()){
			for(Dictionary dict : dictList){
				map.put(dict.getDictCode(), dict.getDictNameCn());
			}
		}
		return map;
	}
}
