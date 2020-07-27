package com.jfast.pojo;

import java.io.Serializable;

public class SysRoleMenuKey implements Serializable {

	private Integer roleId;
	private String menuCode;
	private static final long serialVersionUID = 1L;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode == null ? null : menuCode.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", roleId=").append(roleId);
		sb.append(", menuCode=").append(menuCode);
		sb.append("]");
		return sb.toString();
	}
}