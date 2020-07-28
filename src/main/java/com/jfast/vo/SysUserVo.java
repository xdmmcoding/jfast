package com.jfast.vo;

import com.jfast.pojo.SysUser;

public class SysUserVo extends SysUser{
	   
	private static final long serialVersionUID = 1L;
	/**角色id*/
	private Integer roleId;
	/**角色 名称*/
	private String roleName;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
