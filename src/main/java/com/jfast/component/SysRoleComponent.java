package com.jfast.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.TypeReference;
import com.jfast.ICONST;
import com.jfast.pojo.SysMenu;
import com.jfast.pojo.SysMenuExample;
import com.jfast.pojo.SysRole;
import com.jfast.pojo.SysRoleExample;
import com.jfast.pojo.SysRoleMenu;
import com.jfast.pojo.SysRoleMenuExample;
import com.jfast.service.SysMenuService;
import com.jfast.service.SysRoleMenuService;
import com.jfast.service.SysRoleService;
import com.jfast.util.JSONUtils;
import com.jfast.vo.ExportVo;
import com.jfast.vo.UserVo;

@Component
public class SysRoleComponent {
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 
     * @Description 查询学科名是否重复  
     * @Author      xd  
     * @Date        2019年10月23日 上午10:03:35  
     * @param @param subjectName
     * @param @return 参数  
     * @return boolean 返回类型   
     * @throws
	 */
	public SysRole getSysRoleByNameIs(String roleName){
		if(StringUtils.isEmpty(roleName))return null;
		SysRoleExample srex = new SysRoleExample();
		srex.createCriteria().andRoleNameEqualTo(roleName);
		List<SysRole> list = sysRoleService.getResult(srex);
		if(list != null && !list.isEmpty()) return list.get(0);
		return null;
	}
	/**
	 * 
     * @Description 保存角色信息  
     * @Author      xd  
     * @Date        2019年10月30日 上午8:59:46  
     * @param @param sysRole
     * @param @param user
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	public ExportVo<Object> submit(SysRole sysRole,UserVo user){
		if(sysRole == null) return ExportVo.fail("操作失败");
		SysRole is = this.getSysRoleByNameIs(sysRole.getRoleName());
		if(sysRole.getRoleId() != null){
			if(is != null && !is.getRoleId().equals(sysRole.getRoleId()))return ExportVo.fail("角色名称已存在");
			SysRole up = new SysRole();
			up.setRoleId(sysRole.getRoleId());
			up.setRoleName(sysRole.getRoleName());
			up.setUpdateTime(new Date());
			up.setDescription(sysRole.getDescription());
			sysRoleService.update(up);
			return ExportVo.success(null);
		}else{
			if(is != null)return ExportVo.fail("角色名称已存在");
			sysRole.setOperator(user.getUid());
			sysRole.setCreateTime(new Date());
			sysRole.setRoleStatus(ICONST.ROLE_STATUS_0);
			sysRole.setUpdateTime(new Date());
			sysRoleService.create(sysRole);
			return ExportVo.success(null);
		}
	}
	/**
	 * 
     * @Description 配置菜单权限  
     * @Author      xd  
     * @Date        2019年10月30日 上午10:50:33  
     * @param @param roleId
     * @param @param codes
     * @param @param user
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	public ExportVo<Object> setauthority(Integer roleId,String[] codes,UserVo user){
		List<SysMenu> cList = getMenuList();
		if(cList != null && !cList.isEmpty()){
			Map<String,String> cmap = new HashMap<String,String>();
			for(String code : codes){
				cmap.put(code, code);
			}
//			int look = 0;
//			while (look != cmap.size()){
//				look = recursiveLookup(cmap, cList);
//			}
			List<SysRoleMenu> mList = new ArrayList<SysRoleMenu>();
			for(String code : cmap.keySet()){
				SysRoleMenu m = new SysRoleMenu();
				m.setRoleId(roleId);
				m.setMenuCode(code);
				m.setPermissions(getMenuPermissionsByCode(code, cList));
				m.setOperator(user.getUid());
				m.setCreateTime(new Date());
				m.setUpdateTime(new Date());
				mList.add(m);
			}
			sysRoleMenuService.setauthority(roleId, mList);
		}
		return ExportVo.success(null);
	}
	/**
	 * 
     * @Description 获取全部管理的菜单  
     * @Author      xd  
     * @Date        2019年10月30日 上午11:02:02  
     * @param @param type
     * @param @return 参数  
     * @return List<SysMenu> 返回类型   
     * @throws
	 */
	public List<SysMenu> getMenuList(){
		SysMenuExample smex = new SysMenuExample();
		return sysMenuService.getResult(smex);
	}
	/**
	 * 
     * @Description 匹配权限  
     * @Author      xd  
     * @Date        2020年6月4日 下午3:13:18  
     * @param @param code
     * @param @param cList
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	private String getMenuPermissionsByCode(String code,List<SysMenu> cList){
		if(cList != null && !cList.isEmpty()){
			for(SysMenu menu: cList){
				if(StringUtils.isNotEmpty(code) && code.equals(menu.getCode())){
					return menu.getPermissions();
				}
			}
		}
		return null;
	}
	/**
	 * 
     * @Description 递归找树儿子  
     * @Author      xd  
     * @Date        2019年10月30日 上午11:15:09  
     * @param @param cmap
     * @param @param cList
     * @param @return 参数  
     * @return Map<String,String> 返回类型   
     * @throws
	 */
	public int recursiveLookup(Map<String,String> cmap,List<SysMenu> cList){
		int size = cmap.size();
		if(cmap != null && !cmap.isEmpty()){
			Set<String> keys = JSONUtils.parse(JSONUtils.toJson(cmap.keySet()),new TypeReference<Set<String>>(){});
			for(String code:keys){
				if(cList != null && !cList.isEmpty()){
					for(SysMenu m:cList){
						if("1".equals(m.getState()) 
								|| (StringUtils.isNotEmpty(code) &&(code.equals(m.getCode()) || code.equals(m.getParentCode()))))
							cmap.put(m.getCode(), m.getCode());
					}
				}
			}
		}
		return size;
	}
	/**
	 * 
     * @Description 获取菜单权限数据  
     * @Author      xd  
     * @Date        2019年10月30日 下午2:05:44  
     * @param @param roleId
     * @param @return 参数  
     * @return List<SysRoleMenu> 返回类型   
     * @throws
	 */
	public List<SysRoleMenu> getSysRoleMenuList(Integer roleId){
		SysRoleMenuExample srmex = new SysRoleMenuExample();
		srmex.createCriteria().andRoleIdEqualTo(roleId);
		return sysRoleMenuService.getResult(srmex);
	}
}
