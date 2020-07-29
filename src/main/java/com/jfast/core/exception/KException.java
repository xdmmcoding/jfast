package com.jfast.core.exception;

/**
 * @Description 系统自定义异常  
 * @ClassName   KException  
 * @Date        2019年7月24日 上午8:32:51  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
public class KException extends RuntimeException {
  	    
	private static final long serialVersionUID = 1L;
	/**
     * 错误码
     */
    private Integer code;
    /**
     * 错误消息
     */
    private String message;

    /**
     * 使用错误码构建异常
     * @param code 错误码
     */
    public KException(Integer code) {
        this.code = code;
    }

    /**
     * 使用错误码, 错误消息构建异常
     * @param code
     * @param message
     */
    public KException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
