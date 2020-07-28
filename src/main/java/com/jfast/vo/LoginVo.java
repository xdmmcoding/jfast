package com.jfast.vo;

public class LoginVo {
	/**用户名*/
	private String username;
	/**手机号*/
	private String phone;
	/**密码*/
    private String password;
    /**验证码*/
    private String verfiy;
    /**登录方式*/
    private String type;
    
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
	public String getVerfiy() {
		return verfiy;
	}
	public void setVerfiy(String verfiy) {
		this.verfiy = verfiy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
