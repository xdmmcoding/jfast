package com.jfast.vo;

import java.util.Date;

public class UserVo {
	
	/**用户id*/
    private Integer id;
    /**用户名*/
    private String username;
	/**手机号*/
	private String password;
	/**密码*/
    private String phone;
    /**用户类型*/
    private String type;
    /**用户id*/
    private String uid;
    /**登录ip*/
    private String ip;
    /**状态*/
    private String status;
    /**是否被锁定*/
    private Integer locked;
    /**锁定时间*/
	private Date lockedTime;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Date getLockedTime() {
		return lockedTime;
	}
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
