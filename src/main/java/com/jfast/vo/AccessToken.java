package com.jfast.vo;

import java.util.Date;

public class AccessToken {
	/**授权码*/
	private String token;
	/**是否有效*/
    private Boolean verify;
    /**机器序列号*/
    private String serialNo;
    /**签名时间*/
    private Date signDate;
    /**失效时间*/
    private Date expireDate;
    /**是否失效*/
    private Boolean expire;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getVerify() {
		return verify;
	}
	public void setVerify(Boolean verify) {
		this.verify = verify;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Boolean getExpire() {
		return expire;
	}
	public void setExpire(Boolean expire) {
		this.expire = expire;
	}
}
