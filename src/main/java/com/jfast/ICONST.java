package com.jfast;

/**
 * <p> Title: 常量类 </p>
 * <p> Description: </p>
 */
public interface ICONST {
    
    /**
     * session key
     */
	//public static final String SESSION_KEY_CURRENT_USER = "_current_user_"; // 登录用户
	public static final String SESSION_KEY_UPLOAD_LOCATION = "location"; // 文件上传跟路径地址
	public static final String SESSION_KEY_UPLOAD_TYPE = "type"; // 上传方式
	public static final String SESSION_KEY_VALID_CODE = "_valid_code_"; // 验证码
	public static final String KEY_APP_ID = "appid"; // 站点ID
	public static final String LOGIN_TYPE_1 = "1"; // 短信验证码登录
	/**管理用户*/
	public static final String USER_TYPE_MANAGER = "manager";
	/**
	 * 系统常用路径
	 */
	public static final String SYS_PATH_ERROR = "/error";//异常路径

	
	/**角色是否启用*/
	/**角色是否启用--是*/
	public static final String ROLE_STATUS_0 = "0";
	/**角色是否启用--否*/
	public static final String ROLE_STATUS_F_1 = "-1";
	/**角色是否启用*/
	/**角色是否启用--是*/
	public static final String SYS_USER_STATUS_0 = "0";
	/**角色是否启用--否*/
	public static final String SYS_USER_STATUS_F_1 = "-1";
	/**菜单类型*/
	/**角色是否启用--否*/
	public static final String SYS_MENU_M_TYPE = "mType";
	public static final String SYS_MENU_U_TYPE = "utype";
	
	/**用户是否启用*/
	/**用户是否启用--是*/
	public static final String USER_STATUS_0 = "0";
	/**用户是否启用--否*/
	public static final String USER_STATUS_F_1 = "-1";
	/**用户类型--普通用户*/
	public static final Byte USER_TYPE_0 = 0;
	/**用户类型--超级管理员*/
	public static final Byte USER_TYPE_1 = 1;
	/**用户类型--普通用户*/
	public static final Byte USER_LOCKED_0 = 0;
	/**用户类型--超级管理员*/
	public static final Byte USER_LOCKED_F_1 = -1;

}
