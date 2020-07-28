package com.jfast;

public enum E {
    //系统正常
    SUCCESS(200,"操作成功"),
    E_999(999,"未登录"),
    FAIL(400,"操作失败"),
    E_404(404,"资源不存在"),
    E_403(403,"权限不足"),
    E_500(500,"系统内部异常"),
    E_419(419,"登录超时"),
    LOGIN_USER_NOT_EXIST(-1001,"用户不存在！"),
    LOGIN_PASSWORD_INCORRECT(-1002,"密码不正确！"),
    LOGIN_USER_NOT_ACTIVATED(-1003,"账号已禁用，请联系系统管理员！"),
    LOGIN_USER_IS_LOCKED(-1004,"账号已被锁定"),
    LOGIN_VERFIY_CODE_ERROR(-1005,"验证码有误！"),
    ;

    // 状态编码
    private int code;
    // 状态编码
    private String message;
    // 构造方法
    private E(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}